package com.teamfive.inventory.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = Inventory.TABLE_NAME)
public class Inventory {
public static final String TABLE_NAME="Inventory";
    @Id
    private String uId;
    private Integer quantityLeft;
    private Integer quantitySold;
    private String price;




    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Integer getQuantityLeft() {
        return quantityLeft;
    }

    public void setQuantityLeft(Integer quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
