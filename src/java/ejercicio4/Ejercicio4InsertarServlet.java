/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sergio
 */
public class Ejercicio4InsertarServlet extends HttpServlet {

   

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
            out.println("<title>Servlets - Ejercicio 4</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlets -  Ejercicio 4</h1>");
            out.println("<h2>Crear un servlet que recoja como parámetro un número desde un formulario, y lo guarde en la sesión. Desde otro servlet sacar ese número de la sessión y mostrarlo en pantalla.</h2>");
            ServletContext servletContext = this.getServletContext();
            String action = servletContext.getContextPath() + "/Ejercicio4Insertar";
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
            //get number from params
            Integer number = Integer.parseInt(request.getParameter("number"));
            //get http session
            HttpSession session = request.getSession();
            //save number on session
            session.setAttribute("number", number);
            out.printf("<p>Se ha guardado el número %d en la sesión</p>",number);
            ServletContext servletContext = this.getServletContext();
            String url = servletContext.getContextPath() + "/Ejercicio4Mostrar";
            out.printf("<p><a href='%s'>Ver valor</a></p>",url);
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
