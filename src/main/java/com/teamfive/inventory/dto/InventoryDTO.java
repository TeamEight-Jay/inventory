package com.teamfive.inventory.dto;

public class InventoryDTO {

    private String productId;
    private String merchantId;
    private Integer quantityLeft;
    private Integer quantitySold;
    private Integer price;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "productId='" + productId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", quantityLeft=" + quantityLeft +
                ", quantitySold=" + quantitySold +
                ", price=" + price +
                '}';
    }
}
