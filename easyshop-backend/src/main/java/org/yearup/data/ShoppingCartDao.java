package org.yearup.data;

import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    ShoppingCart addProductToCart(int userId, Product product);
    void updateProductQuantityInCart(int userId, ShoppingCartItem item, int productId);
    void deleteCart(int userId);
}
