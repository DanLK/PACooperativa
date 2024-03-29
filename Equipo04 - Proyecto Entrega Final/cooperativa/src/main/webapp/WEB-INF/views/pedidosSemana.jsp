<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="resources/css/bulma.min.css" rel="stylesheet">
<link href="resources/css/estilo.css" rel="stylesheet">
<script defer src="resources/js/fontawesome.js"></script>
<script defer src="resources/js/pedidosSemana.js"></script>
<title>Pedidos Semana</title>
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
  		
  		<li><a href="/cooperativa/"> Inicio </a></li>
  		</ul>
  		
  		<p class="menu-label">
    		Pedidos
  		</p>	
  		<ul class="menu-list">
  		<c:if test="${usuarioFirmado.rol.id == 1}">
        	<!-- <label><b>ERES ADMIN</b></label> -->
        	<li><a href="/cooperativa/spring/pedidossemana" class="is-active">Pedidos Semana</a></li>
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

    <div class="pedidosListDiv">
    	<div class="box">
    		<div><label class="title is-3">Pedidos</label></div>
    		<div>
    			
    			<c:forEach items = "${listaPedidos}" var = "pedido">	
    			<div>
    			<table class="table tablePedidoListDiv">
				  <thead>
				    <tr>
				      <th>ID Pedido</th>
				      <th>ID Usuario</th>
				      <th>Usuario nombre</th>
				      <th>Usuario apellidos</th>
				      <th>Fecha de Registro</th>
				      <th>Total</th>
				      <th>Status</th>
				      <th></th>
				  	</tr>
				  </thead>
				  <tbody>
				  	<tr>
				    		<th>${pedido.id}</th>
				    		<td>${pedido.usuario.id}</td>
				    		<td>${pedido.usuario.nombre}</td>
				    		<td>${pedido.usuario.apellidos}</td>
				      		<td><fmt:formatDate type = "date" value = "${pedido.fechaRegistro}" timeZone="GMT-6" /></td>
				      		<td>${pedido.total}</td>
				      		<td>${pedido.pedidoStatus.descripcion}</td>
				      		<td>
				      		
				      		<button class="button is-link" id="${pedido.id}">Modificar</button>
				      		<button class="button is-danger" id="rmvBtn_${pedido.id}">Remover</button>
				      		
				      		</td>
				    	</tr>
				  </tbody>
				</table>
				<div class="dropdown">
				  <div class="dropdown-trigger">
				    <button class="button" aria-haspopup="true" aria-controls="dropdown-menu${pedido.id}">
				      <span>Mostrar Productos en el Pedido</span>
				      <span class="icon is-small">
				        <i class="fas fa-angle-down" aria-hidden="true"></i>
				      </span>
				    </button>
				  </div>
				  <div class="dropdown-menu" id="dropdown-menu${pedido.id}" role="menu">
    				<div class="dropdown-content">
    				
    				<table class="table">
				  <thead>
				    <tr>
				      <th>Nombre</th>
				      <th>Contenido</th>
				      <th>Precio Unitario</th>
				      <th>Cantidad</th>
				      <th>Subtotal</th>
				  	</tr>
				  </thead>
				  <tbody>
				  
				  <c:forEach items = "${productosEnPedidos.get(pedido.id)}" var = "productoPedido">
				  
				  	<tr>
				    		<td>${productoPedido.producto.nombre}</td>
				      		<td>${productoPedido.producto.contenido}</td>
				      		<td>${productoPedido.producto.precio}</td>
				      		<td>${productoPedido.cantidad}</td>
				      		<td>${productoPedido.cantidad * productoPedido.producto.precio}</td>
				    </tr>
				    
				    </c:forEach>
				    
				  </tbody>
				</table>
    				
    			    </div>
    			  </div>
    			</div>
    			
    			</div>
    			</c:forEach>
    			
    			
    		</div>
    	</div>
    </div>
</div>


</body>
</html>