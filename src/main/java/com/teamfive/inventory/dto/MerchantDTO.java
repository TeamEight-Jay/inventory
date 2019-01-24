package com.teamfive.inventory.dto;

public class MerchantDTO {

    private String merchantId;
    private String merchantName;
    private String dateOfJoining;

    private float merchantRating;

    public float getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(float merchantRating) {
        this.merchantRating = merchantRating;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                ", merchantRating=" + merchantRating +
                '}';
    }
}
