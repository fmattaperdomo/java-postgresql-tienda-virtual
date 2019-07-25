<%@page import="java.util.List"%>
<%@page import="com.fmattaperdomo.models.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Product> listProducts = (List) request.getAttribute("listProducts");
%>

<jsp:include page="WEB-INF/partials-dynamic/head.jsp">
    <jsp:param name="title" value="Productos" />
</jsp:include>


<jsp:include page="WEB-INF/partials-dynamic/navigation-menu.jsp">
  <jsp:param name="item" value="products" />
</jsp:include>

<div class="l-main ed-container">
  <div class="ed-item">
    <h2 class="productos__title">Productos destacados</h2>
    <div class="productos-container">
      <div class="productos">
<%
    if (listProducts != null) {
        for(Product product : listProducts) {
%>
        <div class="producto">
            <h3 class="producto__title"><%= product.getName() %></h3>
            <a href="producto-detalle?id=<%= product.getId_product() %>">
                <img src="<%= product.getImage() %>" title="Zapatos" class="producto__img">
            </a>
            <p class="producto__price icon-cart">$<%= product.getValue() %></p>
        </div>
<%
        }
    }
%>
      </div>
    </div>
  </div>
</div>

<%@include file="WEB-INF/partials-static/footer.html" %>