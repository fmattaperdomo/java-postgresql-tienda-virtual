<%@page import="java.util.List"%>
<%@page import="com.fmattaperdomo.models.Product"%>
<%@page import="com.fmattaperdomo.models.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Product product = (Product) request.getAttribute("product");
    
    List<Category> categories = (List<Category>) request.getAttribute("listCategories");
%>

<jsp:include page="WEB-INF/partials-dynamic/head.jsp">
    <jsp:param name="title" value="<%= product.getName() %>" />
</jsp:include>

<jsp:include page="WEB-INF/partials-dynamic/navigation-menu.jsp">
  <jsp:param name="item" value="products" />
</jsp:include>

<div class="l-main ed-container">
  <div class="ed-item">
    <h1><%= product.getName() %></h1>
    <div class="ed-container product__page">
      <div class="ed-item tablet-50">
          <img src="/StorePortalDef/<%= product.getImage() %>" class="product__page__img"/>
      </div>
      <div class="ed-item tablet-50 product__page__description">
          <p><%= product.getDescription() %> </p>

          <form method="post" action="agregar-producto">
            <input type="hidden" name="idproduct" value="<%= product.getId_product() %>">
            <div class="ed-container product__page__data">
              <div class="ed-item main-center">
                  <h3 class="product__page__size">
                      <label for="category">Categoría:</label>
                      <select id="category" name="category">
                        <% 
                            if (categories != null) {
                                for(Category category : categories) {
                        %>
                        <option value="<%= category.getId_category() %>"><%= category.getName() %></option>
                        <%            
                                }
                            } 
                        %>
                      </select>
                  </h3>
              </div>
              <div class="ed-item main-center">
                <h3 class="product__page__color">Valor: $<%= product.getValue() %></h3>
              </div>
              <div class="ed-item main-center">
                  <button class="boton icon-cart espacio product__page__buy">Añadir al Carro</button>
              </div>
              <div class="ed-item main-center product__page__share">
                <div class="sociales"><a href="http://facebook.com" class="icon-facebook"></a><a href="http://twitter.com" class="icon-twitter"></a><a href="http://instagram.com" class="icon-instagram"></a><a href="http://pinterest.com" class="icon-pinterest"></a></div>
              </div>
            </div>
              
          </form>  
            
      </div>
    </div>
  </div>
</div>

<%@include file="WEB-INF/partials-static/footer.html" %>