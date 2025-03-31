package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
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
}
