package com.example.demo.controller.client;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.dto.OrderDto;








@Controller
public class ItemController {
    private final ProductService productService;
    private final UserService userService;

    public ItemController(ProductService productService,UserService userService){
        this.productService=productService;
        this.userService=userService;
    }

    @GetMapping("/product/{id}")
    public String getMethodName(Model model, @PathVariable long id,
    HttpServletRequest request) { 
        //lay thong tin ve email duoc luu trong session
        HttpSession session=request.getSession(false); 
        String email=(String)session.getAttribute("email");
        Product item=this.productService.findProductById(id).get();
        model.addAttribute("item", item);
        model.addAttribute("id", id);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCard(@PathVariable long id,
    HttpServletRequest request) {

        //lay thong tin ve email duoc luu trong session
        HttpSession session=request.getSession(false);
        String email=(String)session.getAttribute("email");
        //lay thong tin ve product duoc nguoi dung an vao "Add to cart"
        long productId=id; 
        //Ham su li su kien them Product vao Cart o ProductService
        this.productService.handleAddProductToCart(email, productId,session,1);

        return "redirect:/homepage";
    }
    
    //trang xem chi tiet gio hang
    @GetMapping("/cart")
    public String getCartPage(Model model,HttpServletRequest request ) {
        HttpSession session=request.getSession(false);
        String email=(String)session.getAttribute("email");

        //lay thong tin ve User
        User user=this.userService.getUserByEmail1(email);
        Cart cart=this.productService.fetchByUser(user);

        List<CartDetail> cartDetails=cart==null ? new ArrayList<CartDetail>() :cart.getCartDetails();
        double totalPrice=0;
        for(CartDetail x : cartDetails){
            totalPrice+=x.getPrice();
        }
        if(cart==null || cartDetails.size()==0){
            String mess="Khong co san pham nao trong gio hang";
            model.addAttribute("mess",mess);
        }
        model.addAttribute("cart", cart);
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        return "client/cart/show";
    }

    //xoa 1 CartDetail khoi gio hang
    @PostMapping("/delete-cart-product/{id}")
    public String postDeleteCartDetail(@PathVariable long id,
    HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        long cartDetailId=id;
        this.productService.HandleRemoveCartDetail(id, session);
        return "redirect:/cart";
    }


    
    @GetMapping("/checkout")
    public String getCheckoutPage(Model model, HttpServletRequest request) {
        User currentUser=new User();
        HttpSession session=request.getSession(false);
        long id=(long)session.getAttribute("id");
        currentUser.setId(id);
        Cart cart=this.productService.fetchByUser(currentUser);
        List<CartDetail> cartDetails=cart==null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        double totalPrice=0;
        for(CartDetail x : cartDetails){
            totalPrice+=x.getPrice();
        }
        model.addAttribute("newOrderDto", new OrderDto());
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartDetails", cartDetails);

        return "client/cart/checkout";
    }
    
    @PostMapping("/confirm-checkout")
    public String postConfirmCheckout(@ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails=cart==null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        this.productService.handleUpdateCartBeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }

    @GetMapping("/thankyou")
    public String getThankPage(Model model) {
        return "client/cart/thank";
    }
    

    @PostMapping("/place-order")
    public String postPlaceOrder(Model model,
    HttpServletRequest request,
    @ModelAttribute("newOrderDto") OrderDto orderDto) {
        HttpSession session=request.getSession(false);
        User currentUser=new User();
        long id=(long) session.getAttribute("id");
        currentUser.setId(id);

        this.productService.handlePlaceOrder(currentUser, session, orderDto);
        return "redirect:/thankyou";
    }
    
    @PostMapping("/add-product-to-cart-from-view-detail")
    public String postAddProductFromDetailPage(@RequestParam("id") long id,
    @RequestParam("quantity") long quantity,
    HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        String email=(String)session.getAttribute("email");
        this.productService.handleAddProductToCart(email, id, session,quantity);
       return "redirect:/cart";
    }
    
    
}
