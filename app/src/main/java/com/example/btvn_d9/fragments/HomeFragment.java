package com.example.btvn_d9.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.btvn_d9.interfaces.IClickListener;
import com.example.btvn_d9.models.Product;
import com.example.btvn_d9.adapter.ProductAdapter;
import com.example.btvn_d9.interfaces.ProductServices;
import com.example.btvn_d9.responds.ProductsResponse;
import com.example.btvn_d9.R;
import com.example.btvn_d9.retrofit.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements IClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rvHotDeals, rvMostPopular;
    private ProductAdapter mHotDealsAdapter, mPopularAdapter;
    private List<Product> mListProducts;
    private ProductServices productServices;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        productServices = RetrofitClient.create(ProductServices.class);

        productServices.getProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                ProductsResponse productsResponse = response.body();
                mListProducts = new ArrayList<>();
                mListProducts = productsResponse.getProducts();
                mHotDealsAdapter = new ProductAdapter(mListProducts, HomeFragment.this);
                rvHotDeals.setAdapter(mHotDealsAdapter);

                getProducts(mListProducts);
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {
        rvHotDeals = view.findViewById(R.id.rvHotDeals);
        rvMostPopular = view.findViewById(R.id.rvMostPopular);
    }

    public void getProducts(List<Product> mListProducts){
        List<Product> listProducts = mListProducts.stream()
                .filter(product -> product.getCategory().equals("smartphones"))
                .collect(Collectors.toList());

        mPopularAdapter = new ProductAdapter(listProducts, HomeFragment.this);
        rvMostPopular.setAdapter(mPopularAdapter);
    }

    @Override
    public void onItemClick(int productID) {
        // Sử dụng NavController để navigate đến ProductDetailsFragment
        Bundle bundle = new Bundle();
        bundle.putString("id", String.valueOf(productID));

        NavController navController = NavHostFragment.findNavController(HomeFragment.this);
        navController.navigate(R.id.action_homeFragment_to_productFragment, bundle);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Hiển thị BottomNavigationView và clAppbar khi quay lại HomeFragment
        BottomNavigationView bottom_nav = requireActivity().findViewById(R.id.bottom_nav);
        ConstraintLayout clAppbar = requireActivity().findViewById(R.id.clAppbar);

        bottom_nav.setVisibility(View.VISIBLE);
        clAppbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Ẩn BottomNavigationView và clAppbar khi chuyển sang ProductDetailsFragment
        BottomNavigationView bottom_nav = requireActivity().findViewById(R.id.bottom_nav);
        ConstraintLayout clAppbar = requireActivity().findViewById(R.id.clAppbar);

        bottom_nav.setVisibility(View.GONE);
        clAppbar.setVisibility(View.GONE);
    }

}