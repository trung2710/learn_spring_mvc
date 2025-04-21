package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetail;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.OrderDto;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {

    private final DaoAuthenticationProvider authProvider;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;


    public ProductService(ProductRepository productRepository,
    CartRepository cartRepository,
    CartDetailRepository cartDetailRepository,
    UserService userService, DaoAuthenticationProvider authProvider,
    OrderRepository orderRepository,
    OrderDetailRepository orderDetailRepository){
        this.productRepository=productRepository;
        this.cartRepository=cartRepository;
        this.cartDetailRepository=cartDetailRepository;
        this.userService=userService;
        this.authProvider = authProvider;
        this.orderDetailRepository=orderDetailRepository;
        this.orderRepository=orderRepository;
    }

    public Product saveProduct(Product item){
        Product pro=this.productRepository.save(item);
        return pro;
    }

    public List<Product> findAll(){
        return this.productRepository.findAll();
    }

    //tim kiem 1 san pham rooi in ra
    //Optional se tra ve 2 trang thai:co gtri vs khong co gtri.Neu co gtri thi ben
    //controller dung them ham get()
    public Optional<Product> findProductById(long id){
        return this.productRepository.findById(id);
    }

    //xoa di 1 san pham theo id
    
    public void deleteById(long n){
        this.productRepository.deleteById(n);
    }

    // Thao tac , logic luu san pham vao gio hang
    public void handleAddProductToCart(String email, long productId, HttpSession session, long quantity){
        //check xem user da co gio hang hay chua.Neu chua co thi tao moi
        User user=this.userService.getUserByEmail1(email);
        if(user!=null){
            //check xem user da co gio hang hay chua.Neu chua co thi tao moi
            Cart cart=this.cartRepository.findByUser(user);
            
            if(cart==null){
                //tao moi cart
                Cart otherCart=new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);
                cart=otherCart;
                this.cartRepository.save(cart);
            }
            //save cart_detail
            //Tim product
            Product product=this.productRepository.findTop1ById(productId);
            CartDetail oldDetail=this.cartDetailRepository.findByCartAndProduct(cart, product);

            if(oldDetail==null){
                CartDetail cd=new CartDetail();
                cd.setProduct(product);
                cd.setCart(cart);
                cd.setQuantity(quantity);
                cd.setPrice(product.getPrice()*cd.getQuantity());
                this.cartDetailRepository.save(cd);

                //update so luong cartDetail trong gio hang
                long s=cart.getSum()+1;
                cart.setSum(s);
                this.cartRepository.save(cart); 
                session.setAttribute("sum", s);
            }
            else{
                oldDetail.setQuantity(oldDetail.getQuantity()+quantity);
                double total=oldDetail.getProduct().getPrice()*oldDetail.getQuantity();
                oldDetail.setPrice(total);
                this.cartDetailRepository.save(oldDetail);
            }
            
        }
    }
    public Cart fetchByUser(User user){
        return this.cartRepository.findByUser(user);
    } 

    public void HandleRemoveCartDetail(long id, HttpSession session){
        Optional<CartDetail> cd=this.cartDetailRepository.findById(id);
        if(cd.isPresent()){
            CartDetail cartDetail=cd.get();

            Cart currentCart=cartDetail.getCart();

            this.cartDetailRepository.deleteById(id);

            //update cart, session
            long s=currentCart.getSum()-1;
            currentCart.setSum(s);
            session.setAttribute("sum", s);
            this.cartRepository.save(currentCart);
        }
        
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails){
        for(CartDetail x : cartDetails){
            Optional<CartDetail> cd=this.cartDetailRepository.findById(x.getId());
            if(cd.isPresent()){
                CartDetail currentCartDetail=cd.get();
                currentCartDetail.setQuantity(x.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, OrderDto orderDto) {
        
        
        //create order detail
        //step 1 : get cart by user
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            if (cartDetails != null && !cartDetails.isEmpty()) {
                 //create order
                Order order = new Order();
                order.setPerson(user);
                order.setReceiverName(orderDto.getName());
                order.setReceiverAddress(orderDto.getAddress());
                order.setReceiverPhone(orderDto.getPhone());
                order.setReceiverEmail(orderDto.getEmail());
                order.setReceiverDesc(orderDto.getDesc());
                order.setStatus("PENDING");
                double sum=0;
                for(CartDetail x : cartDetails){
                    sum+=x.getPrice();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);

                // Tạo OrderDetail từ các CartDetail
                for (CartDetail cartDetail : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cartDetail.getProduct());
                    orderDetail.setPrice(cartDetail.getPrice());
                    orderDetail.setQuantity(cartDetail.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }
                
                // QUAN TRỌNG: Xóa liên kết giữa cart và cartDetails
                cart.setCartDetails(new ArrayList<>());
                cart.setSum(0);
                this.cartRepository.save(cart);
                
                // Sau đó xóa các CartDetail
                for (CartDetail cartDetail : new ArrayList<>(cartDetails)) {
                    this.cartDetailRepository.delete(cartDetail); // Sử dụng delete() thay vì deleteById()
                }
                
                // Cập nhật session
                session.setAttribute("sum", 0L);
            }
        }
    }
}
