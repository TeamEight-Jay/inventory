package com.teamfive.inventory.dto;


public class MerchantDTO {
    private String merchantName;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    public String toString() {
        return "MerchantDTO{" +
                ", merchantName='" + merchantName + '\'' +
                '}';
    }
}