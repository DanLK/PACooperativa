<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="resources/css/bulma.min.css" rel="stylesheet">
        <link href="resources/css/estilo.css" rel="stylesheet">
        <script defer src="resources/js/fontawesome.js"></script>
        <title>Inicio</title>
    </head>
    <body>
    <div class="ventanaContent">
    <div class="box boxAdds">
    <aside class="menu">
    	<p><i class="far fa-user"></i> ${usuarioFirmado.nombre}</p>
  		<p class="menu-label">
    		General
  		</p>
  		<ul class="menu-list">
  		
  		<li><a href="/cooperativa/" class="is-active"> Inicio </a></li>
  		</ul>
  		
  		<p class="menu-label">
    		Pedidos
  		</p>	
  		<ul class="menu-list">
  		<c:if test="${usuarioFirmado.rol.id == 1}">
        	<!-- <label><b>ERES ADMIN</b></label> -->
        	<li><a href="/cooperativa/spring/pedidossemana" >Pedidos Semana</a></li>
        	<li><a href="/cooperativa/spring/pedidosenviados" >Pedidos Enviados</a></li>
        	<li><a href="/cooperativa/spring/pedidospagados" >Pedidos Pagados</a></li>
        	<li><a href="/cooperativa/spring/pedidosentregados" >Pedidos Entregados</a></li>
        	<li><a href="/cooperativa/spring/allpedidos" >Todos los Pedidos</a></li>
        </c:if>
        <c:if test="${usuarioFirmado.rol.id == 2}">
        	<!-- <label><b>ERES SOCIO</b></label>  -->
        	<li><a href="/cooperativa/spring/nuevopedido">Nuevo Pedido</a></li>
        	<li><a href="/cooperativa/spring/mispedidos">Mis Pedidos</a></li>		
      	</c:if>
		</ul>
	
	<c:if test="${usuarioFirmado.rol.id == 1}">
        	
        	<p class="menu-label">
    			Usuarios
  			</p>
        	<!-- <label><b>ERES ADMIN</b></label> -->
        	<ul class="menu-list">
        		<li><a href="/cooperativa/spring/listarusuarios">Listar usuarios</a></li>
        		<li><a href="/cooperativa/spring/nuevousuario">Nuevo Usuario</a></li>
        	</ul>
        	
     	<p class="menu-label">
     		Productos
		</p>
		<ul class="menu-list">
     		<li><a href="/cooperativa/spring/listarproductos">Listar Productos</a></li>
     		<li><a href="/cooperativa/spring/nuevoproducto">Nuevo Producto</a></li>
     	</ul>
     </c:if>
	<p class="menu-label">
		Perfil
  	</p>
  	<ul class="menu-list">
  		<li><a href="/cooperativa/spring/usuarioinfo">Ver Información</a></li>
    	<li><a href="/cooperativa/spring/logout">Cerrar Sesión</a></li>
  	</ul>
	</aside>
	</div>
    <div class="pedidoContent">
    	<h1 class="title is-1">Bienvenido, ${usuarioFirmado.nombre}</h1>
    	<h3 class="subtitle is-3">Selecciona la operación que quieras realizar en el menu izquierdo</h3>
    </div>
    </div>
    </body>
</html>