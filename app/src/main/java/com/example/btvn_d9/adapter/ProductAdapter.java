package com.example.btvn_d9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btvn_d9.R;
import com.example.btvn_d9.fragments.HomeFragment;
import com.example.btvn_d9.interfaces.IClickListener;
import com.example.btvn_d9.models.Product;

import java.util.List;
import java.util.Random;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private List<Product> mListProducts;
    private IClickListener iClickListener;

    public ProductAdapter(List<Product> mListProducts, IClickListener iClickListener) {
        this.mListProducts = mListProducts;
        this.iClickListener = iClickListener;
    }

    public ProductAdapter(HomeFragment homeFragment, List<Product> mListProducts) {
        this.mListProducts = mListProducts;
    }

    public ProductAdapter(List<Product> mListProducts) {
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
        ConstraintLayout clProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.imgImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);

            clProduct = itemView.findViewById(R.id.clProduct);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition(); // vị trí của sản phẩm đã bấm vào
                    if(position != RecyclerView.NO_POSITION){
                        int productID = mListProducts.get(position).getId(); // lấy ID của sản phẩm tại vị trí
                        iClickListener.onItemClick(productID);
                    }
                }
            });

        }
    }
}
