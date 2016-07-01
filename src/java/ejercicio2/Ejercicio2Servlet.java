/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.ws.rs.core.UriBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
public class Ejercicio2Servlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<title>Servlet Ejercicio2Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlets - Ejercicio 2</h1>");
            out.println("<h2>Crear un servlet que visualice en pantalla todos los parámetros que recibe. Tener en cuenta que es posible recibir varias veces el mismo parámetro.</h2>");
            
            //show all params
            Enumeration<String> params = request.getParameterNames();
            if(params.hasMoreElements()){
                out.println("<h2>Parámetros Obtenidos</h2>");
                out.println("<ul>");
                while(params.hasMoreElements()){
                    out.println("<li>");
                    String param = params.nextElement();
                    out.print(param);
                    String[] values = request.getParameterValues(param);
                    //parameter list
                    if(values.length > 0){
                        out.println("<ol>");
                        for(String value: values){
                            out.printf("<li> %s </li>",value);
                        }
                        out.println("</ol>");
                    }
                    out.println("</li>");
                }
                out.println("</ul>");
            }else{
                out.println("<h2>No se han encontrado parámetros</h2>");
                ServletContext servletContext = this.getServletContext();
                String path = servletContext.getContextPath() + "/Ejercicio2?nombre=sergio&apellido="+  URLEncoder.encode("sánchez", "UTF-8") + "&languages=java&languages=php";
                out.printf("<p><a href='%s'>Probar Ejemplo</a></p>",path);
            }
            
           
            out.println("</body>");
            out.println("</html>");
        }
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
