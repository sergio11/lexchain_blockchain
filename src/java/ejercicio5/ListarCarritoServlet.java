/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class ListarCarritoServlet extends HttpServlet {

    private ProductsDAO productsDao;

    @Override
    public void init() throws ServletException {
        productsDao = new ProductsDAO();
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
        
        HttpSession session = request.getSession();
         //recuperamos mapa con los productos
        HashMap<Integer,Integer> productsInCart = (HashMap<Integer,Integer>)session.getAttribute("ProductsInCart");
        
        //comprobamos si existe el parámetro drop con la ref del producto a eliminar del carro.
        String refParam = request.getParameter("drop");
        if(refParam != null){
            Integer ref = Integer.parseInt(refParam);
            if(productsInCart.containsKey(ref)){
                productsInCart.remove(ref);
                request.setAttribute("productDeleted", ref);
            }
        }
        
        List<ProductInCart> products = new ArrayList<>();
        if(productsInCart != null && productsInCart.size() > 0){
            //iteramos sobre el map para obtener una lista de ProductInCart que se presentarán en la tabla
            Iterator it = productsInCart.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                //Obtenemos información de producto a partir de su ref.
                Product product = productsDao.getProductByRef((Integer)entry.getKey());
                products.add(new ProductInCart(product.getRef(),product.getName(),product.getPrice(), (Integer) entry.getValue()));
            }
            //establecemos la lista en el request scope
            request.setAttribute("products", products);
        }
        //redirigimos al servlet de presentación
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/carrito.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
