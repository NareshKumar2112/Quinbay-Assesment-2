package com.blibli.Order.Service;

import com.blibli.Order.Model.Cart;
import com.blibli.Order.Model.Order;
import com.blibli.Order.Model.Product;
import com.blibli.Order.orderRepository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderRepository order_repository;

    public String addOrders(List<Cart> ordered_list,int id) {
        List<Cart> confirm_list=new ArrayList<>();
        Order order=new Order();
        double total_amount=0;
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String>entity=new HttpEntity<String>(headers);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("productId",1);
        for(int i=0;i<ordered_list.size();i++)
        {
            long productId=ordered_list.get(i).getOrderedProductId();
            int quantity=ordered_list.get(i).getOrderedProductQuantity();
            Product product=restTemplate.exchange("http://localhost:8080/product/get/"+productId, HttpMethod.GET, entity, Product.class,hashMap).getBody();
            if(product!=null)
            {
                if(quantity>=0 ) {
                    if(quantity<=product.getQuantity()) {
                        total_amount = total_amount + (quantity * product.getCost());
                        ordered_list.get(i).setOrderedProductName(product.getName());
                        ordered_list.get(i).setCostPerProduct(product.getCost());
                        ordered_list.get(i).setTotalProductCost(product.getCost()*quantity);
                        confirm_list.add(ordered_list.get(i));
                        product.setId(product.getId());
                        product.setQuantity(product.getQuantity() - quantity);
                        restTemplate.put("http://localhost:8080/product/update",product, new HashMap<>());
                    }
                    else {
                        return "out of stock";
                    }
                }
                else {
                    return "invalid input";
                }
            }
            else
            {
                return "product is not available";
            }
        }
        order.setOrderedList(confirm_list);
        order.setTotalAmount(total_amount);
        order_repository.save(order);
        return "placed successfully";
    }
    public List<Order> getOrders()
    {
        return order_repository.findAll();
    }
    public List<Product> getAllProducts()
    {
        Product[] productList=restTemplate.getForObject("http://localhost:8080/product/get",Product[].class );
        return Arrays.asList(productList);
    }
}
