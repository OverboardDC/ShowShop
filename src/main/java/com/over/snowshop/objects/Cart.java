package com.over.snowshop.objects;

import com.over.snowshop.entities.OrderedProduct;
import com.over.snowshop.entities.Product;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{

    private volatile static List<CartProduct> cart = new ArrayList<>();

    private static Cart instance;

    private Cart() {

    }

    public static Cart getInstance(HttpSession session) {
        if(session.getAttribute("cart")==null){
            clearCart();
        }
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public static List<CartProduct> getCart() {
        return cart;
    }

    public static void addToCart(CartProduct product){
        cart.add(product);
    }

    public static void removeFromCart(Long id){
        for(int i = 0; i < cart.size(); i++){
            if (cart.get(i).getProduct().getId().equals(id)){
                cart.remove(i);
            }
        }
    }

    public static double getTotalPrice(){
        double totalPrice = 0;
        for(CartProduct cartProduct : cart){
           totalPrice = totalPrice + cartProduct.getFullPrice();
        }
        return totalPrice;
    }

    public static boolean isInCart(Product product){
        for(CartProduct cartProduct : cart){
            if (cartProduct.getProduct().equals(product)){
                return true;
            }
        }
        return false;
    }

    public static void updateQuantity(Long id, int quantity){
        for(int i = 0; i < cart.size(); i++){
            CartProduct cartProduct = cart.get(i);
            if (cartProduct.getProduct().getId().equals(id)){
                cartProduct.setQuantity(quantity);
            }
        }
    }

    public static boolean isEmpty(){
        return cart.isEmpty();
    }

    public static void clearCart(){
        cart.clear();
    }

}
