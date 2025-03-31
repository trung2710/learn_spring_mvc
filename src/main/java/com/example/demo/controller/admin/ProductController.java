package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.UploadService;

import jakarta.validation.Valid;





@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService){
        this.productService=productService;
        this.uploadService=uploadService;
    }


    @RequestMapping("/admin/product")
    public String getProductPage(Model model){
        List<Product>products=this.productService.findAll();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/{id}")
    public String getDetailProduct(Model model, @PathVariable long id) {
        Product pro=this.productService.findProductById(id).get();
        model.addAttribute("id", id);
        model.addAttribute("item", pro);
        return "/admin/product/detail";
    }
    

    @GetMapping("/admin/product/create")
    public String getCreateAProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "/admin/product/create";
    }
    
    @PostMapping("/admin/product/create")
    public String postCreateAProduct(Model model , @ModelAttribute("newProduct") @Valid Product product,
    BindingResult newProductBindingResult,
    @RequestParam("imagefile") MultipartFile file
    ) {
        //validate
        if(newProductBindingResult.hasErrors()){
            return "/admin/product/create";
        }

        //upload file
        String image=this.uploadService.handleSaveUploadFile(file, "product");
        product.setImage(image);
        this.productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    //xoa 1 sam pham
    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductpage(Model model, @PathVariable long id) {
        Product pro=new Product();
        pro.setId(id);
        model.addAttribute("id",id);
        model.addAttribute("newProduct", pro);
        return "/admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteAProduct(Model model, @ModelAttribute("newProduct") Product pro) {
        this.productService.deleteById(pro.getId());
        return "redirect:/admin/product";
    }
    
    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductpage(Model model, @PathVariable long id) {
        Product sp=this.productService.findProductById(id).get();
        model.addAttribute("newProduct", sp);
        return "/admin/product/update";
    }
    

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(Model model,
        @ModelAttribute("newProduct") @Valid Product product,
        BindingResult newProductBindingResult,
        @RequestParam("imagefile") MultipartFile file) {

        if(newProductBindingResult.hasErrors()){
            return "/admin/product/update";
        }
        Product currentProduct=this.productService.findProductById(product.getId()).get();
        if(currentProduct!=null){
            //update new image
            if(!file.isEmpty()){
                String img=this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(img);
            }
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setTarget(product.getTarget());
            currentProduct.setFactory(product.getFactory());
            this.productService.saveProduct(currentProduct);
        }
        return "redirect:/admin/product";

    }
    
    
}
