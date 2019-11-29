<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="resources/css/bulma.min.css" rel="stylesheet">
<link href="resources/css/estilo.css" rel="stylesheet">
<script defer src="resources/js/fontawesome.js"></script>
<script defer src="resources/js/listarproductos.js"></script>
<title>Lista Productos</title>
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
  		
  		<li><a href="/cooperativa/"> Inicio </a>
  		<c:if test="${usuarioFirmado.rol.id == 1}">
        	<!-- <label><b>ERES ADMIN</b></label> -->
        </c:if>
        <c:if test="${usuarioFirmado.rol.id == 2}">
        	<!-- <label><b>ERES SOCIO</b></label>  -->
        	<li><a href="/cooperativa/spring/nuevopedido">Nuevo Pedido</a></li>
        	<li><a href="/cooperativa/spring/mispedidos">Mis Pedidos</a></li>	
      	</c:if>
	</ul>
	<c:if test="${usuarioFirmado.rol.id == 1}">
     	<!-- <label><b>ERES ADMIN</b></label> -->
     	<p class="menu-label">
     		Productos
		</p>
		<ul class="menu-list">
     		<li><a href="/cooperativa/spring/listarproductos" class="is-active">Listar Productos</a></li>
     		<li><a href="/cooperativa/spring/nuevoproducto">Nuevo Producto</a></li>
     	</ul>
     </c:if>
	<p class="menu-label">
		Perfil
  	</p>
  	<ul class="menu-list">
    	<li><a href="/cooperativa/spring/logout">Cerrar Sesión</a></li>
  	</ul>
	</aside>
	</div>
    <div class="pedidoContent">
    	<div class="box">
    		<div>
    			<label  class="title is-3">Productos</label>
    		</div>
    		<div class="listaPrFll">
    			<table class="table tablePr">
				  <thead>
				    <tr>
				      <th>ID</th>
				      <th>Nombre</th>
				      <th>Contenido</th>
				      <th>Precio</th>
				      <th>Departamento</th>
				      <th></th>
				  	</tr>
				  </thead>
				  <tbody>
				  	<c:forEach items = "${listaProductos}" var = "producto">
         				<tr>
				    		<th>${producto.id}</th>
				      		<td>${producto.nombre}</td>
				      		<td>${producto.contenido}</td>
				      		<td>${producto.precio}</td>
				      		<td>${producto.departamento}</td>
				      		<td>
								<button class="button is-link" id="${producto.id}">Modificar</button>
				      			<button class="button is-danger" id="rmvBtn_${producto.id}">Remover</button>
							</td>
				    	</tr>
				    </c:forEach>
				  </tbody>
				</table>
    		</div>
    </div>
    </div>
    
    	
</body>
</html>