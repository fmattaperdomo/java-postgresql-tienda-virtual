<%@page import="com.fmattaperdomo.models.Invoice_Detail"%>
<%@page import="com.fmattaperdomo.models.Invoice_Header"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Invoice_Header invoice = (Invoice_Header) request.getAttribute("invoice");
    float total = 0f;
%>
<jsp:include page="WEB-INF/partials-dynamic/head.jsp">
    <jsp:param name="title" value="Factura de Venta" />
</jsp:include>


<jsp:include page="WEB-INF/partials-dynamic/navigation-menu.jsp">
  <jsp:param name="item" value="shoppingCart" />
</jsp:include>

<div class="l-main ed-container">
  <div class="ed-item">
      <div class="ed-container web-80">
        <div class="ed-item main-center">
            <h1>Factura de Venta</h1>
        </div>
      </div>
      <div class="ed-container offset-25 web-50 contact-form">
          <div class="ed-item web-30">
            <label for="id_invoice">Factura Número:</label>
          </div>
          <div class="ed-item web-70">
            <input type="text" id="id_invoice" readonly value="<%= invoice.getId_invoice() %>">
          </div>
          <div class="ed-item web-30">
            <label for="invoice_date">Fecha:</label>
          </div>
          <div class="ed-item web-70">
            <input type="text" id="invoice_date" readonly value="<%= invoice.getInvoice_date() %>">
          </div>
          <div class="ed-item web-30">
            <label for="full_name">Nombre:</label>
          </div>
          <div class="ed-item web-70">
            <input type="text" id="full_name" readonly value="<%= invoice.getFull_name() %>">
          </div>
          <div class="ed-item web-30">
            <label for="address_main">Dirección:</label>
          </div>
          <div class="ed-item web-70">
            <input type="text" id="address_main" readonly value="<%= invoice.getAddress_main() %>">
          </div>

          <div class="ed-item web-30">
            <label for="phone_main">Teléfono:</label>
          </div>
          <div class="ed-item web-70">
            <input type="text" id="phone_main" readonly value="<%= invoice.getPhone_main() %>">
          </div>
      </div>
      <div class="ed-item offset-20 web-60">
          <table class="listado">
              <thead>
                  <tr>
                      <th>Producto</th>
                      <th>Categoría</th>
                      <th>Valor</th>
                  </tr>
              </thead>
              <tbody>
                  <%
                      for (Invoice_Detail invoiceDetail : invoice.getInvoice_detail()) {
                          total += invoiceDetail.getProduct().getValue();
                  %>
                  <tr>
                      <td><%= invoiceDetail.getProduct().getName() %></td>
                      <td><%= invoiceDetail.getProduct().getCategory().getName() %></td>
                      <td><%= invoiceDetail.getProduct().getValue() %></td>
                  </tr>
                  <%
                      }
                  %>
              </tbody>
              <tfoot>
                  <tr>
                      <td colspan="2" class="suma-label">Total</td>
                      <td class="suma"><%= total %></td>
                  </tr>
              </tfoot>
          </table>
      </div>
        <div class="ed-item offset-25 web-50">
            <form class="ed-item main-center" name="_xclick" target="paypal" action="https://www.paypal.com/cgi-bin/webscr" method="post">
            <!-- <form class="ed-item main-center" name="_xclick" target="paypal" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post"> -->
                <input type="hidden" name="cmd" value="_xclick">
                <input type="hidden" name="business" value="edstyles-colombia@outlook.com">
                <input type="hidden" name="currency_code" value="USD">
                <input type="hidden" name="item_name" value="Factura: <%= invoice.getId_invoice() %>">
                <input type="hidden" name="amount" value="<%= total %>">
                <!-- <input type="image" src="http://www.paypal.com/es_ES/i/btn/sc-but-01.gif" border="0" name="submit" alt="Make payments with PayPal - it's fast, free and secure!"> -->
                <input type="hidden" name="add" value="1">
                <input type="hidden" name="charset" value="utf-8">
                <input type="hidden" name="on0" value="Comprador">
                <input type="hidden" name="os0" value="<%= invoice.getFull_name() %>">
                <input type="hidden" name="on1" value="Direccion">
                <input type="hidden" name="os1" value="<%= invoice.getAddress_main() %>">
                <input type="hidden" name="on2" value="Telefono">
                <input type="hidden" name="os2" value="<%= invoice.getPhone_main() %>">
                <button type="submit" class="boton icon-cart espacio product__page__buy">
                    Pagar por Paypal
                </button>
            </form>
        </div>
  </div>
</div>

<%@include file="WEB-INF/partials-static/footer.html" %>