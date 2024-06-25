package com.blibli.Order.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "order")
public class Order {

    @Id
    private String id;
    private List<Cart> OrderedList;
    private double totalAmount;

}
