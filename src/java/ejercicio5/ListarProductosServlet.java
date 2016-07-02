/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import models.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sergio
 */
public class ListarProductosServlet extends HttpServlet {
    
    public final static String PRODUCTS_IN_CART_KEY = "ProductsInCart";
    
    private ProductsDAO productsDao;

    @Override
    public void init() throws ServletException {
        productsDao = new ProductsDAO();
    }
    
    
    private void saveProduct(HttpSession session, Integer ref){
        HashMap<Integer,Integer> productsInCart = (HashMap<Integer,Integer>)session.getAttribute(PRODUCTS_IN_CART_KEY);
        if(productsInCart == null){
            productsInCart = new HashMap<>();
        }
        Integer quantity = 1;
        if(productsInCart.containsKey(ref)){
           quantity =  productsInCart.get(ref);
           quantity += 1;
        }
        productsInCart.put(ref, quantity);
        session.setAttribute(PRODUCTS_IN_CART_KEY, productsInCart);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ref = request.getParameter("addToCart");
        if(ref != null){
            HttpSession session = request.getSession();
            saveProduct(session,Integer.parseInt(ref));
            //save the product name to request scope
            request.setAttribute("productAdded", ref);
        }
        
        //set products on request scope
        List<Product> products = productsDao.getProducts();
        request.setAttribute("products", products);
       
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/listarProductos.jsp");
        rd.forward(request, response);
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
