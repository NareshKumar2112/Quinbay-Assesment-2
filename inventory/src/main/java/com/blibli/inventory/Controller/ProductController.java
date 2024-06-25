package com.blibli.inventory.Controller;

import com.blibli.inventory.Model.Product;
import com.blibli.inventory.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService product_service;

    @GetMapping("/get")
    public List<Product> getProductName()
    {
        return product_service.getProducts();
    }
    @GetMapping("/get/{id}")
    public Optional<Product> getProductByid(@PathVariable long id)
    {
        return product_service.getProductById(id);
    }
    @PostMapping("/post")
    public String postProduct(@RequestBody Product product)
    {
        return product_service.addProduct(product);
    }
    @PutMapping("/update")
    public String updateProduct(@RequestBody Product product)
    {
        return product_service.updateProduct(product);

    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id)
    {
            return product_service.deleteProduct(id);
    }
}