package com.example.btvn_d9;

import com.example.btvn_d9.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductServices {
    @GET("products?limit=0")
    Call<ProductsResponse> getProducts();
}