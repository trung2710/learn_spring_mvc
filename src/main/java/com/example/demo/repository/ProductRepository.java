package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;


@Repository
public interface  ProductRepository extends JpaRepository<Product, Long>{
    Product save(Product pro);
    Product findTop1ById(long id);
    void deleteById(long id);
}
