package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    private DataSource dataSource;

    // The constructor
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    // get all categories ~DONE
    @Override
    public List<Category> getAllCategories()
    {
        List<Category> categories = new ArrayList<>();

        String query = """
                select * from categories
                """;
        try(Connection connection = getConnection()){

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Category category = mapRow(resultSet);
                categories.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // get all categories
        return categories;
    }
    // get category by Id ~DONE
    @Override
    public Category getById(int categoryId)
    {
        // query
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        Category category = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    category = mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return category;
    }
    // create method ~DONE
    @Override
    public Category create(Category category)
    {
        // query to create category
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // create a new category
        return category;
    }
    // TODO update method
    @Override
    public void update(int categoryId, Category category)
    {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    UPDATE categories
                    SET category_id = ?, name = ?, description = ?
                    WHERE category_id = ?
                    """);
            statement.setInt(1, category.getCategoryId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.setInt(4, categoryId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } // update category
    }
    // TODO delete method
    @Override
    public void delete(int categoryId)
    {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM categories
                    WHERE category_id = ?;
                    """);
            statement.setInt(1, categoryId);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }  // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category();

            category.setCategoryId(categoryId);
            category.setName(name);
            category.setDescription(description);


        return category;
    }

}
