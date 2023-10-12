package com.example.btvn_d9.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.btvn_d9.models.Product;
import com.example.btvn_d9.R;
import com.example.btvn_d9.retrofit.RetrofitClient;
import com.example.btvn_d9.interfaces.ProductServices;
import com.example.btvn_d9.responds.ProductsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tvTitle, tvPrice;
    private ProductServices productServices;
    private List<Product> mListProducts;



    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    public static ProductDetailsFragment newInstance(String param1, String param2) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
        initView(view);

    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.tvTitle);
        tvPrice = view.findViewById(R.id.tvPrice);
    }

    private void initData() {
        String productID = getArguments().getString("id");

        if(productID != null) {
            productServices = RetrofitClient.create(ProductServices.class);
            productServices.getProducts().enqueue(new Callback<ProductsResponse>() {
                @Override
                public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                    ProductsResponse productsResponse = response.body();
                    mListProducts = new ArrayList<>();
                    mListProducts = productsResponse.getProducts();
                    Product product = getProductById(mListProducts, Integer.parseInt(productID));

                    tvTitle.setText(product.getTitle());
                    tvPrice.setText("$" + product.getPrice());
                }

                @Override
                public void onFailure(Call<ProductsResponse> call, Throwable t) {

                }
            });
        }

    }

    private Product getProductById(List<Product> listProducts, int id) {
        return listProducts.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}