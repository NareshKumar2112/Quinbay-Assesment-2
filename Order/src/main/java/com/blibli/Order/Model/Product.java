package com.blibli.Order.Model;

import lombok.Data;


@Data
public class Product {

    private long id;
    private String name;
    private double cost;
    private int quantity;

}

