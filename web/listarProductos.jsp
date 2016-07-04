<%-- 
    Document   : header
    Created on : 02-jul-2016, 10:36:30
    Author     : sergio
--%>

<%@page import="models.Product"%>
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
        <title>Listar Productos</title>
        
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Listado de Productos</h1>
            
            <%
            
                String productAdded = (String)request.getAttribute("productAdded");
                if(productAdded != null){
                
            %>
            
            <div class="alert alert-dismissible alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Producto añadido!</strong> <%= productAdded %> fue añadido al carrito.
            </div>
            
            <% 
            
                }
            %>
            
            <table class="table table-striped">
                <thead>
                  <tr>
                    <th>Ref</th>
                    <th>Nombre</th>
                    <th>Disponible</th>
                    <th>Precio Unitario</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                        List<Product> products = (List<Product>)request.getAttribute("products");
                        if(products.size() > 0){
                            for(Product product : products){
                    %>
                    <tr>
                        <td><%= product.getRef()%></td>
                        <td>
                            <%= product.getName()%> 
                            <% if(product.getDescription() != null && product.getDescription().length() > 0){ %>
                            <a class="label label-info" data-container="body" data-toggle="popover" data-placement="right" data-content="<%= product.getDescription() %>"><span class="fa fa-eye"></span></a>
                            <% } %>
                        </td>
                        <td><% if(product.isAvaliable()) { %> <span class="label label-success"><span class="fa fa-check" aria-hidden="true"></span></span> <% }else{ %> <span class="label label-danger"><span class="fa fa-ban" aria-hidden="true"></span></span><% }%></td>
                        <td><%= product.getPrice()%></td>
                        <td><a class="btn btn-default btn-sm  <% if(!product.isAvaliable()) { %> disabled <% } %>" href="ListarProductos?addToCart=<%= product.getRef() %>" data-toggle="tooltip" data-placement="right" title="" data-original-title="Añadir <%= product.getName() %> al carrito"><span class="fa fa-cart-plus"></span></a></td>
                    </tr>
                    <% 
                            }
                        }else{
                    %> 
                    <tr class="warning text-center">
                        <td colspan="5"><strong>Ningún Producto encontrado</strong></td>
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
                        $('[data-toggle="popover"]').popover();
                    });
                </script>
    </body>
</html>
