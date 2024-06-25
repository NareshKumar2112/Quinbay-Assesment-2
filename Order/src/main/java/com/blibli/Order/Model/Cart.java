package com.blibli.Order.Model;

import lombok.Data;

@Data
public class Cart {

    private int cartId;
    private int orderedProductId;
    private int orderedProductQuantity;
    private String orderedProductName;
    private double costPerProduct;
    private double totalProductCost;

}
