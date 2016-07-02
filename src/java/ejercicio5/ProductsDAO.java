/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergio
 */
public class ProductsDAO {
    
    List<Product> products;

    public ProductsDAO() {
        products = new ArrayList<>();
        ///Integer ref, String description, Boolean avaliable, Float price
        products.add(new Product(2020420,"Pantalón Clásico","Pantalón muy bonito, perfecto para el verano",true,33.89f));
        products.add(new Product(4010210,"Camiseta Deporte", "Camiseta perfecta para hacer deporte este verano",true,33.89f));
        products.add(new Product(2041116,"Gorra Deporte", "Una gorra",true,21.18f));
    }
    
    public List<Product> getProducts(){
        return products;
    }
    
    public Product getProductByRef(Integer ref){
        Product result = null;
        for(Product product : products){
            if(product.getRef().equals(ref)){
                result = product;
                break;
            }  
        }
        return result;
    }
    
    
}
