package com.blibli.inventory.Service;

import com.blibli.inventory.Model.Product;
import com.blibli.inventory.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository product_repository;
    public String addProduct(Product product)
    {
        if(product.getName()!=null&&product.getName().length()>2&&product.getCost()>0&&product.getQuantity()>0) {
            Product product_added = product_repository.save(product);
            if (product_added == null) {
                return "product is not added";
            }
            return "added successfully";
        }
        else
        {
            return "invalid inputs";
        }
    }
    public List<Product> getProducts()
    {
        List<Product> all_products =product_repository.findAll();
        if(all_products==null)
        {
            return new ArrayList<>();
        }
        return all_products;
    }
    public String updateProduct(Product product)
    {
        if(product.getName()!=null&&product.getName().length()>2&&product.getCost()>0&&product.getQuantity()>0) {
            Product update_product = product_repository.saveAndFlush(product);
            if (update_product == null) {
                return "update failed";
            }
            return "updated successfully";
        }
        else {
            return "invalid input";
        }
    }
    public String deleteProduct(long id)
    {
        try {
            Optional<Product> product=product_repository.findById(id);
            System.out.println(product);
            if(product.isEmpty())
            {
                return "product is not available";
            }
            product_repository.deleteById(id);
        }catch(Exception e)
        {
            return "deletion failed";
        }
        return "deleted successfully";
    }
    public Optional<Product> getProductById(long id) {
        return product_repository.findById(id);
    }
}