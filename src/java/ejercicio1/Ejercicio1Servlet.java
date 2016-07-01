/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
public class Ejercicio1Servlet extends HttpServlet {

 

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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlets - Ejercicio 1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlets -  Ejercicio 1</h1>");
            out.println("<h2>Crear un servlet que recoja un parámetro por pantalla y lo visualice en la respuesta.</h2>");
            ServletContext servletContext = this.getServletContext();
            String action = servletContext.getContextPath() + "/Ejercicio1";
            out.printf("<form method='post' action='%s'>",action);
            out.println("<label for='name'>Nombre:</label>");
            out.println("<input type='text' name='nombre' placeholder='pg. Sergio' required autocomplete='false' />");
            out.println("<input type='submit' value='Enviar' />");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
        }
        
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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlets - Ejercicio 1</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String nombre = request.getParameter("nombre");
            if(nombre != null){
               out.printf("<h2>El nombre introducido fue : %s", nombre);
            }else{
                out.println("<h2>No has introducido ningún nombre</h2>");
            }
            
            out.println("</body>");
            out.println("</html>");
            
        }
            
        
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
