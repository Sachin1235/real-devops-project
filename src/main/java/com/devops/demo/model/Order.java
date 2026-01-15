package com.devops.demo.model;
import jakarta.persistence.*;
@Entity
@Table(name="orders")
public class Order {
 @Id @GeneratedValue
 private Long id;
 private String product;
 private int price;
 public Long getId(){return id;}
 public void setId(Long id){this.id=id;}
 public String getProduct(){return product;}
 public void setProduct(String product){this.product=product;}
 public int getPrice(){return price;}
 public void setPrice(int price){this.price=price;}
}
