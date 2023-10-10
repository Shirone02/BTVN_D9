package com.example.btvn_d9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private List<Product> mListProducts;

    public ProductAdapter(HomeFragment homeFragment, List<Product> mListProducts) {
        this.mListProducts = mListProducts;
    }

    public ProductAdapter(Context mContext, List<Product> mListProducts) {
        this.mContext = mContext;
        this.mListProducts = mListProducts;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder , int position) {
        Product product = mListProducts.get(position);
        Random random = new Random();
        Glide.with(mContext).load(product.getImages().get(random.nextInt(product.getImages().size()))).into(holder.imageView);
        holder.tvTitle.setText(product.getTitle());
        holder.tvPrice.setText(product.getPrice() + "");
        holder.tvRating.setText(product.getRating() + "");
    }

    @Override
    public int getItemCount() {
        return mListProducts == null? 0 : mListProducts.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle, tvPrice, tvRating;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.imgImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);

        }
    }
}
