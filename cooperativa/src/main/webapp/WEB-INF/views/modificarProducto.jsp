<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="${rutaResources}resources/css/bulma.min.css" rel="stylesheet">
        <link href="${rutaResources}resources/css/estilo.css" rel="stylesheet">
        <script defer src="${rutaResources}resources/js/fontawesome.js"></script>
        <script defer src="${rutaResources}resources/js/modificarproducto.js"></script>
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
  		
  		<li><a href="/cooperativa/"> Inicio </a></li>
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
	
	<div class="usuarioContent">
    	<div class="box">
    	<div style="text-align: center;"><label class="title is-3">Modificar Producto: ${productoAModificar.id}</label></div>
		<div style="text-align: center;"><label class="subtitle is-3">La información actual es:</label></div>
   
   		<div class="column is-three-quarters">
   		
   		
   		<form:form method="POST" action="${rutaResources}modificarProducto" modelAttribute="productoForm">
			
			<div style="display:none;">
				<form:label class="label" path="id">ID</form:label>
	            <div class="control">
	            	<form:input class="input" path="id" value="${productoAModificar.id}" disabled="true"/>
	            </div>
	        </div>
			
			<div class="field">
				<form:label class="label" path="nombre">Nombre</form:label>
	            <div class="control">
	            	<form:input class="input" path="nombre" value="${productoAModificar.nombre}" disabled="true"/>
	            </div>
	            <div class="control">
				  	<form:errors path="nombre" cssClass="tag is-danger is-light" />
				  </div>
	        </div>
	           
	        <div class="field"> 
				<form:label class="label" path="contenido">Contenido</form:label>
	            <div class="control">
	            <form:input class="input" path="contenido" value="${productoAModificar.contenido}" disabled="true"/>
	            </div>
	            <div class="control">
					  	<form:errors path="contenido" cssClass="tag is-danger is-light" />
					  </div>
	        </div>
	        
	        <div class="field">
	            <form:label class="label" path="precio">Precio</form:label>
	            <div class="control">
	            <form:input class="input" path="precio" value="${productoAModificar.precio}" disabled="true"/>
	            </div>
	            <div class="control">
					  	<form:errors path="precio" cssClass="tag is-danger is-light" />
					  </div>
	        </div>
	        
	        <div class="field">  
	            <form:label class="label" path="departamento">Departamento</form:label>
	            <div class="control">
	            <form:input class="input" path="departamento" value="${productoAModificar.departamento}" disabled="true"/>
	            </div>
	            <div class="control">
					  	<form:errors path="departamento" cssClass="tag is-danger is-light" />
					  </div>
	        </div>
	            
	        <div style="text-align: center;">
	         	<input class="button is-link" type="submit" value="Guardar Cambios"/>
	        </div>
	        
		</form:form>
   		
    </div>
    </div>
    </div>
    </div>
</body>
</html>