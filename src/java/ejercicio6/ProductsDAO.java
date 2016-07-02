/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author sergio
 */
public class ProductsDAO {
    
    private final DataSource ds;
    
    public ProductsDAO(DataSource ds) {
        this.ds = ds;
    }
    
    public List<Product> getProducts(){
        Connection con = null;
        List<Product> products = new ArrayList<>();
        try{
            con = ds.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from products");
            
            while(rs.next()){
                Product product = new Product();
                product.setRef(rs.getInt("ref"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setAvaliable(rs.getBoolean("avaliable"));
                product.setPrice(rs.getFloat("price"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(con != null)con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return products;
    }
    
    public Product getProductByRef(Integer ref){
        Connection con = null;
        Product product = null;
        try{
            con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE ref = ?");
            ps.setInt(1, ref);
            ResultSet rs = ps.executeQuery();
            rs.next();
            product = new Product();
            product.setRef(rs.getInt("ref"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setAvaliable(rs.getBoolean("avaliable"));
            product.setPrice(rs.getFloat("price"));
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(con != null)con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return product;
    }
    
    
}
