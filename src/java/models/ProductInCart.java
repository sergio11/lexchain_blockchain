package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sergio
 */
public class ProductInCart {
    
    private Integer ref;
    private String name;
    private Float price;
    private Integer quantity;

    public ProductInCart(Integer ref, String name, Float price, Integer quantity) {
        this.ref = ref;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    

   

    
}
