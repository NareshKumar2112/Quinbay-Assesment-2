package com.blibli.inventory.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double cost;
    private int quantity;
}