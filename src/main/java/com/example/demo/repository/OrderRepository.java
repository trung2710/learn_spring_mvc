package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;

@Repository
public interface  OrderRepository extends JpaRepository<Order, Long>{
    Order save(Order order);
    Order findById(long id);
    void deleteById(long id);
    List<Order> findByPerson(User user);
}
