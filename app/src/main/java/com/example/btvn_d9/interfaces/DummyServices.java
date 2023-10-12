package com.example.btvn_d9.interfaces;

import com.example.btvn_d9.models.Product;
import com.example.btvn_d9.responds.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DummyServices {

    @GET("products")
    Call<ProductsResponse> getProducts();

    @GET("product/{idProduct}")
    Call<Product> getProductById(@Path("idProduct") String idProduct);

    @GET("products/search")
    Call<ProductsResponse> searchByProductName(@Query("q") String productName);

//    @Headers("Content-Type: application/json")
//    @POST("product/add")
//    Call<JsonObject> addProduct(@Body AddProductRequest addProductRequest);

}
