<%@page import="java.util.List"%>
<%@page import="com.fmattaperdomo.models.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Product> shoppingCart = (List) session.getAttribute("shoppingCart");
    float total = 0f;
%>

<jsp:include page="WEB-INF/partials-dynamic/head.jsp">
    <jsp:param name="title" value="Carro" />
</jsp:include>


<jsp:include page="WEB-INF/partials-dynamic/navigation-menu.jsp">
  <jsp:param name="item" value="shoppingCart" />
</jsp:include>

<div class="l-main ed-container">
  <div class="ed-item">
      <h1>Mi Carro de Compras</h1>
      <div class=""><!-- el container de los productos -->
          <% 
              if (shoppingCart != null) {
                  for (Product product : shoppingCart) {
                      total += product.getValue();
          %>
          <div class="producto-carro"> 
              <div class="producto-carro__img"> 
                <img src="<%= product.getImage() %>" alt="Zapatos">
              </div>
              <div class="producto-carro__info">
                  <h3><%= product.getName() %></h3>
                  <p><%= product.getDescription() %></p>
              </div>
              <div class="producto-carro__talla"> 
                  <h3>Talla</h3>
                  <p><%= product.getCategory().getShort_name() %></p>
              </div>
              <div class="producto-carro__precio"> 
                  <h3>Precio</h3>
                  <p>$<%= product.getValue() %></p>
              </div>
              <div class="producto-carro__del">
                  <a href="eliminar?id=<%= product.getId_product() %>">
                  <img src="img/delete.png" alt="Eliminar" title="Eliminar">
                  </a>
              </div>
          </div>
          <%
                  }
              }
          %>
      </div>
      <div class="producto-carro__footer"> 
          <div class=""> <!-- el container de los botones -->
              <a class="boton icon-cart espacio product__page__buy" href="/StorePortalDef/productos">Seguir comprando</a>
              
          </div>
          <div class="producto-carro__total">
              <h2>Total a pagar <span class="">$<%= total %></span></h2>
          </div>
      </div>
    <form method="post" action="vender" class="ed-container web-60 contact-form">
        <div class="ed-item web-30">
            <label for="full_name">Nombres y apellidos</label>
        </div>
        <div class="ed-item web-70">
            <input type="text" name="full_name" id="full_name">
        </div>
        <div class="ed-item web-30">
            <label for="address_main">Dirección completa</label>
        </div>
        <div class="ed-item web-70">
            <input type="text" name="address_main" id="address_main">
        </div>

        <div class="ed-item web-30">
            <label for="phone_main">Telefono Principal</label>
        </div>
        <div class="ed-item web-70">
            <input type="text" name="phone_main" id="phone_main">
        </div>
        
        <div class="ed-item">
            <button class="boton icon-cart espacio product__page__buy">Confirmar Compra</button>
        </div>
    </form>
  </div>
</div>

<%@include file="WEB-INF/partials-static/footer.html" %>