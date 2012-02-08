package org.agoncal.application.petstore.web;

import org.agoncal.application.petstore.domain.*;
import org.agoncal.application.petstore.service.CatalogService;
import org.agoncal.application.petstore.service.OrderService;
import org.agoncal.application.petstore.web.LoggedIn;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Named
@ConversationScoped
public class ShoppingCartController extends Controller implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private CatalogService catalogBean;
    @Inject
    private OrderService orderBean;
    
    @Inject
    private Conversation conversation;

	private List<CartItem> cartItems;

    private CreditCard creditCard = new CreditCard();

    @Inject @LoggedIn
    private Instance<Customer> customerInstances;

    private Order order;

    // ======================================
    // =              Public Methods        =
    // ======================================

    public String addItemToCart() {
        String navigateTo = null;
        try {
            Item item = catalogBean.findItem(getParamId("itemId"));

            // Start conversation
            if (conversation.isTransient()) {
                cartItems = new ArrayList<CartItem>();
                //conversation.begin();
            }

            boolean itemFound = false;
            for (CartItem cartItem : cartItems) {
                // If item already exists in the shopping cart we just change the quantity
                if (cartItem.getItem().equals(item)) {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    itemFound = true;
                }
            }
            if (!itemFound)
                // Otherwise it's added to the shopping cart
                cartItems.add(new CartItem(item, 1));

            navigateTo = "showcart.xhtml";
        } catch (Exception e) {
            addMessage(this.getClass().getName(), "addItemToCart", e);
        }
        return navigateTo;
    }

    public String removeItemFromCart() {
        String navigateTo = null;

        try {
            Item item = catalogBean.findItem(getParamId("itemId"));

            for (CartItem cartItem : cartItems) {
                if (cartItem.getItem().equals(item)) {
                    cartItems.remove(cartItem);
                    return null;
                }
            }

        } catch (Exception e) {
            addMessage(this.getClass().getName(), "removeItemFromCart", e);
        }
        return navigateTo;
    }

    public String updateQuantity() {
        return null;
    }

    public String checkout() {
        return "confirmorder.xhtml";
    }

    public String confirmOrder() {
        String navigateTo = null;

        try {
            order = orderBean.createOrder(getCustomer(), creditCard, getCartItems());
            cartItems.clear();

            // Stop conversation
            if (!conversation.isTransient()) {
                conversation.end();
            }

            navigateTo = "orderconfirmed.xhtml";
        } catch (Exception e) {
            addMessage(this.getClass().getName(), "confirmOrder", e);
        }
        return navigateTo;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public boolean shoppingCartIsEmpty() {
        return getCartItems() == null || getCartItems().size() == 0;
    }


    public Float getTotal() {

        if (cartItems == null || cartItems.isEmpty())
            return 0f;

        Float total = 0f;

        // Sum up the quantities
        for (CartItem cartItem : cartItems) {
            total += (cartItem.getSubTotal());
        }
        return total;
    }

    public void initConversation() {
        if (conversation.isTransient()) {
            cartItems = new ArrayList<CartItem>();
            conversation.begin();
        }
    }

    // ======================================
    // =         Getters & setters          =
    // ======================================

    public Customer getCustomer() {
    	Customer res = customerInstances.get();
       return res;
    }


    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public CreditCardType[] getCreditCardTypes() {
        return CreditCardType.values();
    }
}