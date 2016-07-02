<%-- 
    Document   : header
    Created on : 02-jul-2016, 13:41:24
    Author     : sergio
--%>
<%@page import="java.util.HashMap"%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Shop</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="ListarProductos">Productos</a></li>
                <% HashMap<String,Integer> cart = (HashMap<String,Integer>)session.getAttribute("ProductsInCart");%>
                <li><a href="ListarCarrito">Carrito <span class="fa fa-shopping-cart"></span>&nbsp;<% if(cart != null ){ %> (<%= cart.size() %>) <% }%></a></li>
            </ul>
        </div>
    </div>
</nav>
