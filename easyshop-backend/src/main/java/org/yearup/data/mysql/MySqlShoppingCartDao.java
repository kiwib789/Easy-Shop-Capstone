package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    // Constructor
    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    // Method to get shopping cart by user ID
    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart shoppingCart = new ShoppingCart();

        try (Connection connection = getConnection()) {
            // SQL query to get all products in a users shopping cart
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM shopping_cart
                    JOIN products ON products.product_id = shoppingCart.product_id
                    WHERE user_id = ?
                    """);
            statement.setInt(1, userId);
            // Executes query and gets result set
            ResultSet resultSet = statement.executeQuery();
            // Iterate through each row in the result set
            while (resultSet.next()) {
                // Creates new shopping cart item for each row in the result set
                ShoppingCartItem item = new ShoppingCartItem();
                Product product = new Product();

                resultSet.getInt("shopping_cart_product_id");
                resultSet.getString("name");
                resultSet.getBigDecimal("price");
                resultSet.getInt("category_id");
                resultSet.getString("description");
                resultSet.getString("color");
                resultSet.getString("image_url");
                resultSet.getInt("stock");
                int usersId = resultSet.getInt("user_id");
                int id = resultSet.getInt("id");
                int quantity = resultSet.getInt("quantity");

                item.setProduct(product);
                item.setQuantity(resultSet.getInt(quantity));
                // Adds the product to the shoppingCart
                shoppingCart.add(item);

            }
            // returns new shopping cart
            return new ShoppingCart();
        } catch (SQLException e) {
            // Catch SQL exceptions and throw a runtime exception
            throw new RuntimeException(e);
        }

    }
    // Method to update the quantity of product in the users shopping cart
    @Override
    public void updateProductQuantityInCart(int userId, ShoppingCartItem item, int productId) {

        try (Connection connection = getConnection()) {
            // SQL query to update product
            PreparedStatement statement = connection.prepareStatement("""
                    UPDATE shopping_cart
                    SET quantity = ?
                    WHERE user_id = ? AND product_id =?
                    """);
            // Sets variables in prepared statements
            statement.setInt(1, item.getQuantity());
            statement.setInt(1, userId);
            statement.setInt(1, productId);

            // Execute query
            statement.executeUpdate();

        } catch (SQLException e) {
            // Catch SQL exceptions and throw a runtime exception
            throw new RuntimeException(e);
        }

    }

    // Method to delete all products in users shopping cart
    @Override
    public void deleteCart(int userId) {

        try (Connection connection = getConnection()) {
            // SQL query that deletes items from shopping cart by specific user
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM shopping_cart
                    WHERE user_id = ?
                    """);
            // Sets userId in prepared statement
            statement.setInt(1, userId);
            //Executes query
            statement.executeUpdate();

        } catch (SQLException e) {
            // Catch SQL exceptions and throw a runtime exception
            throw new RuntimeException(e);
        }

    }

    //Method to add items to users shopping cart
    @Override
    public ShoppingCart addProductToCart(int userId, Product product) {
        // Gets shopping cart from specific user
        ShoppingCart shoppingCart = getByUserId(userId);

        try (Connection connection = getConnection()) {
            // SQL query to insert a new product into a specific users shopping cart
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO shopping_cart (user_id, product)
                    VALUE (?, ?);
                    """, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set the userId and productId in the prepared statement
            statement.setInt(1, userId);
            statement.setInt(2, product.getProductId());
            // Executes query
            ResultSet resultSet = statement.executeQuery();

            // Creates a new item and adds it to users shopping cart
            ShoppingCartItem item = new ShoppingCartItem();
            item.setProduct(product);
            shoppingCart.add(item);

            // returns shopping cart with new item added
            return shoppingCart;
        } catch (SQLException e) {
            // Catch SQL exceptions and throw a runtime exception
            throw new RuntimeException(e);
        }
    }



}
