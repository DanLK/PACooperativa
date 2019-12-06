<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
        <script defer src="${rutaResources}resources/js/modificausuario.js"></script>
        <title>Modificar Usuario</title>
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
    		<div style="text-align: center;"><label class="title is-3">Modificar Usuario ${usuarioID}</label></div>
    		<div class="column is-three-quarters">
    			<form:form method="POST" action="${rutaResources}modificarUsuario" id="modificarUsuarioForm" modelAttribute="formUsuarioInfo">
    			<div style="display:none;">
				  <form:label path="id" class="label">ID</form:label>
				  <div class="control">
				    <form:input path="id" class="input" name="id" type="text" value="${usuarioID}" />
				  </div>
				  <div class="control">
				  	<form:errors path="id" cssClass="tag is-danger is-light" />
				  </div>
				</div>
				<div class="field">
				  <form:label path="nombre" class="label">Nombre</form:label>
				  <div class="control">
				    <form:input path="nombre" class="input" name="nombre" type="text" value="${usuario.getNombre()}" />
				  </div>
				  <div class="control">
				  	<form:errors path="nombre" cssClass="tag is-danger is-light" />
				  </div>
				</div>
				<div class="field">
				  <form:label path="apellidos" class="label">Apellidos</form:label>
				  <div class="control">
				    <form:input path="apellidos" class="input" name="apellidos" type="text" value="${usuario.getApellidos()}" />
				  </div>
				  <div class="control">
				  	<form:errors path="apellidos" cssClass="tag is-danger is-light" />
				  </div>
				</div>
				<div class="field">
				  <form:label path="username" class="label">Username</form:label>
				  <c:if test="${not empty error}">
						<div class="control">
					  		<label class="tag is-danger is-light">${error}</label>
					  	</div>
					</c:if>
				  <div class="control">
				    <form:input path="username" class="input" name="username" type="text" value="${usuario.getUsername()}" />
				  </div>
				  <div class="control">
				  	<form:errors path="username" cssClass="tag is-danger is-light" />
				  </div>
				</div>
				<div class="field">
				  <form:label path="correo" class="label">Correo</form:label>
				  <div class="control">
				    <form:input path="correo" class="input" name="correo" type="text" value="${usuario.getCorreo()}" />
				  </div>
				  <div class="control">
				  	<form:errors path="correo" cssClass="tag is-danger is-light" />
				  </div>
				</div>
				<div class="field">
				  <form:label path="telefono" class="label">Teléfono</form:label>
				  <div class="control">
				    <form:input path="telefono" class="input" name="telefono" type="text" value="${usuario.getTelefono()}" />
				  </div>
				  <div class="control">
				  	<form:errors path="telefono" cssClass="tag is-danger is-light" />
				  </div>
				</div>
				
				<div class="field">
				  <form:label path="password" class="label">Password</form:label>
				  <div class="control">
				    <form:input path="password" class="input" name="password" type="text" value="${usuario.getPassword()}" />
				  </div>
				  <div class="control">
				  	<form:errors path="password" cssClass="tag is-danger is-light" />
				  </div>
				</div>
		
		
				<div style="text-align: center;">
	    		<button class="button is-link" id="guardarUsuarioBtn" type="submit">Guardar Cambios de Usuario</button>
	    		</div>
    			</form:form>
  
  
    		</div>
    		
    	</div>
    	
    </div>
    </div>
    	
    
    </body>
</html>