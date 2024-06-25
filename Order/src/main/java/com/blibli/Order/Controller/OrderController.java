package com.blibli.Order.Controller;

import com.blibli.Order.Model.Cart;
import com.blibli.Order.Model.Order;
import com.blibli.Order.Model.Product;
import com.blibli.Order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService order_service;

    @PostMapping("/add")
    public String addOrders(@RequestBody List<Cart> ordered_list) {
        return order_service.addOrders(ordered_list,1);
    }
    @GetMapping("/get")
    public List<Order> getOrders() {
        return order_service.getOrders();
    }
    @GetMapping("/get/products")
    public List<Product> getAllProducts()
    {
        return order_service.getAllProducts();
    }
}