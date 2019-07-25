<%
    String item = request.getParameter("item");
%>
<header class="l-header">
    <div class="ed-container">
        <div class="ed-item">
            <a href="/"><img src="img/logo.png" class="logo"/></a>
        </div>
        <div class="ed-item">
            <div id="open-menu" class="hasta-web icon-menu"></div>
            <nav id="menu" class="menu-container">
                <div id="close-menu" class="icon-close hasta-web"></div>
                <ul class="ed-menu web-horizontal">
                    <li><a href="/StorePortalDef/" class="<% out.print(item.equals("home")?"selected":""); %>">inicio</a></li>
                    <li><a href="/StorePortalDef/us.jsp" class="<% out.print(item.equals("us")?"selected":""); %>">nosotros</a></li>
                    <li><a href="/StorePortalDef/productos" class="<% out.print(item.equals("products")?"selected":""); %>">productos</a></li>
                    <li><a href="/StorePortalDef/shoppingCart.jsp" class="<% out.print(item.equals("shoppingCart")?"selected":""); %>">mi carro</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
