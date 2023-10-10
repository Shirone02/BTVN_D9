package com.example.btvn_d9;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("products")
    @Expose
    private List<Product> products;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("discountedTotal")
    @Expose
    private Integer discountedTotal;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("totalProducts")
    @Expose
    private Integer totalProducts;
    @SerializedName("totalQuantity")
    @Expose
    private Integer totalQuantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getDiscountedTotal() {
        return discountedTotal;
    }

    public void setDiscountedTotal(Integer discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Integer totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", products=" + products +
                ", total=" + total +
                ", discountedTotal=" + discountedTotal +
                ", userId=" + userId +
                ", totalProducts=" + totalProducts +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}