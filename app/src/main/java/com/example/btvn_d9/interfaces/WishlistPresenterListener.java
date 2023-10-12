package com.example.btvn_d9.interfaces;

import com.example.btvn_d9.models.Product;

public interface WishlistPresenterListener {
    void addToWishlish(Product product);
    void removeFromWishlist(Product product);

}
