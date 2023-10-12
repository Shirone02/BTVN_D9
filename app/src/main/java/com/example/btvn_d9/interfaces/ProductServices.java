package com.example.btvn_d9.interfaces;

import com.example.btvn_d9.responds.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductServices {
    @GET("products?limit=0")
    Call<ProductsResponse> getProducts();
}