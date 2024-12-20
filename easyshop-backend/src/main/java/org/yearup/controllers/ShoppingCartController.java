package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;

// convert this class to a REST controller
// only logged in users should have access to these actions
@RestController
@RequestMapping("/shopping_cart")
public class ShoppingCartController
{
    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    // each method in this controller requires a Principal object as a parameter
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart getCart(Principal principal)
    {
        try
        {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // use the shoppingcartDao to get all items in the cart and return the cart
            return null;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be added
    // Method to add a product to the user's shopping cart
    @PostMapping("/products/{productId}")
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart addProductToCart(Principal principal, @PathVariable int productId) {
        try {
            // Get the currently logged-in user's username
            String userName = principal.getName();
            // Find the user by username
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // Fetch the product by its productId
            Product product = productDao.getById(productId);

            // Add the product to the user's shopping cart
            return shoppingCartDao.addItemsToCart(userId, product);
        } catch (Exception e) {
            // Return an error response if something goes wrong
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated
    // Method to update the quantity of a product in the user's shopping cart
    @PutMapping("/products/{productId}")
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart updateProductInCart(Principal principal, @PathVariable int productId, @RequestBody ShoppingCartItem updatedItem) {
        try {
            // Get the currently logged-in user's username
            String userName = principal.getName();
            // Find the user by username
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // Update the product's quantity in the user's shopping cart
            shoppingCartDao.updateCart(userId, updatedItem, productId);

            // Return the updated shopping cart
            return shoppingCartDao.getByUserId(userId);
        } catch (Exception e) {
            // Return an error response if something goes wrong
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart
    // Method to clear all products from the user's shopping cart
    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public void clearCart(Principal principal) {
        try {
            // Get the current user's username
            String userName = principal.getName();
            // Find the user by username
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // Clears user's shopping cart
            shoppingCartDao.deleteCart(userId);
        } catch (Exception e) {
            // Return an error response if something goes wrong
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
