package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.domain.Category;
import org.agoncal.application.petstore.domain.Customer;
import org.agoncal.application.petstore.domain.Item;
import org.agoncal.application.petstore.domain.Product;
import org.agoncal.application.petstore.exception.ValidationException;
import org.agoncal.application.petstore.util.Loggable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Stateless
@Loggable
public class CatalogService implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private EntityManager em;

    // ======================================
    // =              Public Methods        =
    // ======================================

    public Category findCategory(Long categoryId) {
        if (categoryId == null)
            throw new ValidationException("Invalid id");

        return em.find(Category.class, categoryId);
    }

    public Category findCategory(String categoryName) {
        if (categoryName == null)
            throw new ValidationException("Invalid name");

        TypedQuery<Category> typedQuery = em.createNamedQuery(Category.FIND_BY_NAME, Category.class);
        typedQuery.setParameter("pname", categoryName);
        return typedQuery.getSingleResult();
    }

    public List<Category> findAllCategories() {
        TypedQuery<Category> typedQuery = em.createNamedQuery(Category.FIND_ALL, Category.class);
        return typedQuery.getResultList();
    }

    public Category createCategory(Category category) {
        if (category == null)
            throw new ValidationException("Category object is null");
        
        em.persist(category);
        return category;
    }

    public Category updateCategory(Category category) {
        if (category == null)
            throw new ValidationException("Category object is null");
        
        em.merge(category);
        return category;
    }

    public Category removeCategory(Category category) {
        if (category == null)
            throw new ValidationException("Category object is null");
        
        em.remove(em.merge(category));
        return category;
    }

    public List<Product> findProducts(String categoryName) {
        TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_BY_CATEGORY_NAME, Product.class);
        typedQuery.setParameter("pname", categoryName);
        return typedQuery.getResultList();
    }

    public Product findProduct(Long productId) {
        if (productId == null)
            throw new ValidationException("Invalid id");

        return em.find(Product.class, productId);
    }

    public Product createProduct(Product product) {
        if (product == null)
            throw new ValidationException("Product object is null");
        
        em.persist(product);
        return product;
    }

    public List<Item> findItems(Long productId) {

        TypedQuery<Item> typedQuery = em.createNamedQuery(Item.FIND_BY_PRODUCT_ID, Item.class);
        typedQuery.setParameter("productId", productId);
        return typedQuery.getResultList();
    }

    public Item findItem(final Long itemId) {
        if (itemId == null)
            throw new ValidationException("Invalid id");

        return em.find(Item.class, itemId);
    }

    public List<Item> searchItems(String keyword) {

        TypedQuery<Item> typedQuery = em.createNamedQuery(Item.SEARCH, Item.class);
        typedQuery.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
        return typedQuery.getResultList();
    }

    public Item createItem(Item item) {
        if (item == null)
            throw new ValidationException("Item object is null");

        em.persist(item);
        return item;
    }
}
