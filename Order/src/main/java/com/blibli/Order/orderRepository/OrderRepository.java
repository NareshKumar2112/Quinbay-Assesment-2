package com.blibli.Order.orderRepository;

import com.blibli.Order.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {


//    @Query("{ }")
//    List<Order> findAll();
}
