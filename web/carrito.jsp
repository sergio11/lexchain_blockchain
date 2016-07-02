<%-- 
    Document   : carrito
    Created on : 02-jul-2016, 13:39:25
    Author     : sergio
--%>

<%@page import="ejercicio5.ProductInCart"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href='css/bootstrap.min.css' />
        <link rel="stylesheet" href='css/font-awesome.min.css' />
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <title>Ejercicio 5 - Mostrar Carrito</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <% List<ProductInCart> products = (List<ProductInCart>)request.getAttribute("products");%>
        <div class="container">
            <h1>Resumen de la cesta de la compra</h1>
            <h2>Su cesta contiene <%= products.size() %> productos</h2>
            
            <table class="table table-striped">
                <thead>
                  <tr>
                    <th>Ref</th>
                    <th>Nombre</th>
                    <th>Precio Unitario</th>
                    <th>Cantidad</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                        if(products.size() > 0){
                            for(ProductInCart product : products){
                    %>
                    <tr>
                        <td><%= product.getRef()%></td>
                        <td><%= product.getName()%></td>
                        <td><%= product.getPrice()%></td>
                        <td><%= product.getQuantity()%></td>
                        <td></td>
                    </tr>
                    <% 
                            }
                        }else{
                    %> 
                    <tr class="warning text-center">
                        <td colspan="5"><strong>El carrito está vacío</strong></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    
    </body>
</html>
