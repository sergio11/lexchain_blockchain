/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

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
public class Ejercicio3Servlet extends HttpServlet {

   
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
            out.println("<title>Servlets - Ejercicio 3</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlets -  Ejercicio 3</h1>");
            out.println("<h2>Crear un servlet que a partir de un formulario recoja un número, y sea capaz de mostrar su tabla de multiplicar.</h2>");
            ServletContext servletContext = this.getServletContext();
            String action = servletContext.getContextPath() + "/Ejercicio3";
            out.printf("<form method='post' action='%s'>",action);
            out.println("<label for='number'>Número:</label>");
            out.println("<input type='number' name='number' min='0' max='100' placeholder='pg. 8' required autocomplete='false' />");
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
            out.println("<title>Servlets - Ejercicio 3</title>");            
            out.println("</head>");
            out.println("<body>");
            Integer number = Integer.parseInt(request.getParameter("number"));
            if(number >= 0 && number <= 100){
                out.println("<table>");
                out.printf("<caption>Tabla del %d</caption>",number);
                for(int i = 1; i <= 10; i++){
                    out.println("<tr>");
                    out.printf("<td>%d</td>",number);
                    out.print("<td>X</td>");
                    out.printf("<td>%d</td>",i);
                    out.print("<td>=</td>");
                    out.printf("<td>%d</td>",i * number);
                    out.println("</tr>");
                }
                out.println("</table>");
            }else{
                 out.println("<p>Debes Proporcionar un número comprendido entre 0 y 100");
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
