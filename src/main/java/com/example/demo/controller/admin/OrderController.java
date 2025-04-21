package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;






@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/admin/order")
    public String getOrderPage(Model model){
        List<Order>orders=this.orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id) {
        Order order=this.orderService.getOrderById(id);
        model.addAttribute("id",id);
        model.addAttribute("orderDetails", order.getOrderDetails());
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdatePage(Model model, @PathVariable long id) {
        Order order=this.orderService.getOrderById(id);
        model.addAttribute("newOrder", order);
        return "admin/order/update";
    }
    
    @PostMapping("/admin/order/update")
    public String postMethodName(Model model, @ModelAttribute("newOrder") Order order) {
        Order or=this.orderService.getOrderById(order.getId());
        or.setStatus(order.getStatus());
        this.orderService.saveOrder(order);
        return "redirect:/admin/order";
    }
    
    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage(Model model, @PathVariable long id) {
        Order order=this.orderService.getOrderById(id);
        model.addAttribute("newOrder", order);
        model.addAttribute("id", id);
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postDeleteOrder(Model model, @ModelAttribute("newOrder") Order order) {
        this.orderService.deleteAOrder(order);
        return "redirect:/admin/order";
    }
    
    
}
