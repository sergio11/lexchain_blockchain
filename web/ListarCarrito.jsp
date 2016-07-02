<%-- 
    Document   : carrito
    Created on : 02-jul-2016, 13:39:25
    Author     : sergio
--%>

<%@page import="models.ProductInCart"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<!DOCTYPE html>
<html>
    <head>
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">
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
            
            <%
                Integer ref = (Integer)request.getAttribute("productDeleted");
                if(ref != null){
            %>
            <div class="alert alert-dismissible alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Producto eliminado!</strong> <%= ref %> fue eliminado del carrito.
            </div>
            <% } %>
            
            <% if(products != null){ %>
            <h2>Su cesta contiene <%= products.size() %> productos</h2>
            <% } %>
            <table class="table table-striped text-center">
                <thead>
                  <tr>
                    <th class="text-center">Ref</th>
                    <th class="text-center">Nombre</th>
                    <th class="text-center">Precio Unitario</th>
                    <th class="text-center">Cantidad</th>
                    <th class="text-center">Total</th>
                    <th class="text-center">Acciones</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                        if(products != null && products.size() > 0){
                            Float total = 0.0f;
                            for(ProductInCart product : products){
                                Float totalProduct = (product.getPrice() * product.getQuantity());
                                total += totalProduct;
                    %>
                    <tr>
                        <td><%= product.getRef()%></td>
                        <td><%= product.getName()%></td>
                        <td><%= product.getPrice()%></td>
                        <td><%= product.getQuantity()%></td>
                        <td><%= totalProduct %> €</td>
                        <td><a href="ListarCarrito?drop=<%= product.getRef()%>" class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="right" title="" data-original-title="Eliminar <%= product.getName() %> de la cesta"><span class="fa fa-trash"></span></a></td>
                    </tr>
                    <% } %>
                    <tr>
                        <td colspan="5" class="text-right"><strong>Total Productos IVA incl.</strong>&nbsp;<%= total %> €</td>
                    </tr>
                    <%
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
        <script>
            $(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>        
    </body>
</html>
