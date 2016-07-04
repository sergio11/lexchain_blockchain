/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpSession;
import models.Product;
import models.ProductInCart;

/**
 *
 * @author sergio
 */
public class Carrito {
    
    private final static String PRODUCTS_IN_CART_KEY = "ProductsInCart";
    
    private HttpSession session;
    /*Almacena el carrito (ref,cantidad)*/
    private HashMap<Integer,Integer> productsInCart;

    public Carrito(HttpSession session) {
       this.session = session;
       //Recupera el carrito de la sesión
       this.productsInCart = (HashMap<Integer,Integer>)session.getAttribute(PRODUCTS_IN_CART_KEY);
       if(this.productsInCart == null){
            this.productsInCart = new HashMap<>();
        }
    }
    /*
        Guarda un producto en el carrito
    */
    public void saveProduct(Integer ref){
        Integer quantity = 1;
        if(productsInCart.containsKey(ref)){
           quantity =  productsInCart.get(ref);
           quantity += 1;
        }
        productsInCart.put(ref, quantity);
        session.setAttribute(PRODUCTS_IN_CART_KEY, productsInCart);
    }
    
    /* Elimina el producto del carrito si este existe*/
    public void removeProduct(Integer ref){
        if(productsInCart.containsKey(ref)){
            productsInCart.remove(ref);   
        }
    }
    /* Devuelve la cantidad total de productos en el carrito*/
    public Integer getTotalProducts(){
        Integer total = 0;
        if(productsInCart != null && productsInCart.size() > 0){
            Iterator it = productsInCart.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                total += (Integer)entry.getValue();
            }
        }
        return total;
    }

    /*Devuelve el contenido del carrito*/
    public HashMap<Integer, Integer> getProductsInCart() {
        return productsInCart;
    }
    
    
    
}
