package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

// TODO add the annotations to make this a REST controller
// TODO add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
// TODO add annotation to allow cross site origin requests
@RestController
@RequestMapping(path = "/categories")
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    // create an Autowired controller to inject the categoryDao and ProductDao ~DONE
    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao){
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }
    // TODO add the appropriate annotation for a get action
    public List<Category> getAll()
    {
        // TODO find and return all categories
        return null;
    }

    // TODO add the appropriate annotation for a get action
    public Category getById(@PathVariable int id)
    {
        // TODO get the category by id
        return null;
    }

    // TODO the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // TODO get a list of product by categoryId
        return null;
    }

    // TODO add annotation to call this method for a POST action
    // TODO add annotation to ensure that only an ADMIN can call this function
    public Category addCategory(@RequestBody Category category)
    {
        // TODO insert the category
        return null;
    }

    // TODO add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // TODO add annotation to ensure that only an ADMIN can call this function
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        // TODO update the category by id
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId ~DONE
    // add annotation to ensure that only an ADMIN can call this function ~ DONE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id)
    {
        categoryDao.delete(id);
        // delete the category by id ~DONE
    }
}
