package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetail;
import com.example.demo.domain.User;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    public List<Order> findAll(){
        return this.orderRepository.findAll();
    }

    public Order getOrderById(long id){
        return this.orderRepository.findById(id);
    }
    public void saveOrder(Order order){
        this.orderRepository.save(order);
    }
    public void deleteAOrder(Order order){
        Order currentOrder=this.orderRepository.findById(order.getId());
        List<OrderDetail>orderDetails=currentOrder.getOrderDetails();
        for(OrderDetail x : orderDetails){
            this.orderDetailRepository.deleteById(x.getId());
        }
        this.orderRepository.deleteById(currentOrder.getId());
    }

    public List<Order> fetchOrderByUser(User user){
        return this.orderRepository.findByPerson(user);
    }
}
