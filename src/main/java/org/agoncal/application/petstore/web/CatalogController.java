package org.agoncal.application.petstore.web;

import org.agoncal.application.petstore.domain.Category;
import org.agoncal.application.petstore.domain.Item;
import org.agoncal.application.petstore.domain.Product;
import org.agoncal.application.petstore.service.CatalogService;
import org.agoncal.application.petstore.util.Loggable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Named
//@RequestScoped
@Loggable
@SessionScoped
public class CatalogController extends Controller implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private CatalogService catalogService;

    private String categoryName;
    private Long productId;
    private Long itemId;

    private String keyword;
    private Category category;
    private Product product;
    private Item item;
    private List<Product> products;
    private List<Item> items;

    // ======================================
    // =              Public Methods        =
    // ======================================

    public String doFindProducts() {
        String navigateTo = null;
        try {

            category = catalogService.findCategory(categoryName);
            products = category.getProducts();
            navigateTo = "showproducts.faces";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return navigateTo;
    }

    public String doFindItems() {
        String navigateTo = null;

        try {
            product = catalogService.findProduct(productId);
            items = product.getItems();
            navigateTo = "showitems.faces";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return navigateTo;
    }

    public String doFindItem() {
        String navigateTo = null;

        try {
            item = catalogService.findItem(getParamId("itemId"));
            navigateTo = "showitem.faces";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return navigateTo;
    }

    public String doSearch() {
        String navigateTo = null;

        try {
            items = catalogService.searchItems(keyword);
            navigateTo = "searchresult.faces";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return navigateTo;
    }

    // ======================================
    // =         Getters & setters          =
    // ======================================

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public List<Product> getProducts() {
        return products;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}