package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {

    private final DaoAuthenticationProvider authProvider;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;


    public ProductService(ProductRepository productRepository,
    CartRepository cartRepository,
    CartDetailRepository cartDetailRepository,
    UserService userService, DaoAuthenticationProvider authProvider){
        this.productRepository=productRepository;
        this.cartRepository=cartRepository;
        this.cartDetailRepository=cartDetailRepository;
        this.userService=userService;
        this.authProvider = authProvider;
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
    public void handleAddProductToCart(String email, long productId, HttpSession session){
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
                cd.setPrice(product.getPrice());
                cd.setQuantity(1);
                this.cartDetailRepository.save(cd);

                //update so luong cartDetail trong gio hang
                long s=cart.getSum()+1;
                cart.setSum(s);
                this.cartRepository.save(cart); 
                session.setAttribute("sum", s);
            }
            else{
                oldDetail.setQuantity(oldDetail.getQuantity()+1);
                double total=oldDetail.getProduct().getPrice()*oldDetail.getQuantity();
                oldDetail.setPrice(total);
                this.cartDetailRepository.save(oldDetail);
            }
            
        }
    }
    public Cart fetchByUser(User user){
        return this.cartRepository.findByUser(user);
    } 
}
