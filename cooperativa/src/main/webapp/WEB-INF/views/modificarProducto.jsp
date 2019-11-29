<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="../../resources/css/bulma.min.css" rel="stylesheet">
        <link href="../../resources/css/estilo.css" rel="stylesheet">
        <script defer src="../../resources/js/fontawesome.js"></script>
        <script defer src="../../resources/js/modificarproducto.js"></script>
        <title>Modificar Producto</title>
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
        	<li><a href="/cooperativa/spring/mispedidos" >Mis Pedidos</a></li>	
      	</c:if>
	</ul>
	<c:if test="${usuarioFirmado.rol.id == 1}">
     	<!-- <label><b>ERES ADMIN</b></label> -->
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
  		<li><a href="/cooperativa/spring/logout">Cerrar Sesión</a></li>
  	</ul>
	</aside>
	</div>
	<div class="infoContent">
    	<h1 class="title is-1">Modificar Producto: ${productoAModificar.id}</h1>
    	<h3 class="subtitle is-3">La información actual es:</h3>
    	<hr>Da click en modificar para modificar algún campo.
    	<hr>Luego guarda los cambios con el botón "Guardar cambios".
    </div>
    <div class="infoContent">
    	<form:form method="POST" action="../../modificarProducto" modelAttribute="productoForm">
			<table class="box tableInfo">
				<tr style="display:none;">
	            	<td><form:label path="id">ID</form:label></td>
	                <td><form:input path="id" value="${productoAModificar.id}" disabled="true"/></td>
	             </tr>
	             <tr>
	            	<td><form:label path="nombre">Nombre</form:label></td>
	                <td><form:input path="nombre" value="${productoAModificar.nombre}" disabled="true"/></td>
	                
	             </tr>
	             <tr>
	            	<td><form:label path="contenido">Contenido</form:label></td>
	                <td><form:input path="contenido" value="${productoAModificar.contenido}" disabled="true"/></td>
	                
	             </tr>
	             <tr>
	            	<td><form:label path="precio">Precio</form:label></td>
	                <td><form:input path="precio" value="${productoAModificar.precio}" disabled="true"/></td>
	                
	             </tr>
	              <tr>
	            	<td><form:label path="departamento">Departamento</form:label></td>
	                <td><form:input path="departamento" value="${productoAModificar.departamento}" disabled="true"/></td>
	             </tr>
	             <tr>
	             	<td colspan="2"><input type="submit" value="Guardar Cambios"/></td>
	             </tr>
	        </table>
		</form:form>
    </div>
    </div>
</body>
</html>