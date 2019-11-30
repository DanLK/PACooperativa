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
        <script defer src="resources/js/nuevousuario.js"></script>
        <title>Nuevo Usuario</title>
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
        		<li><a href="/cooperativa/spring/listarusuarios">Listar Usuarios</a></li>
        		<li><a href="/cooperativa/spring/nuevousuario" class="is-active">Nuevo Usuario</a></li>
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
  		<li><a href="/cooperativa/spring/usuarioinfo">Ver Informaci�n</a></li>
    	<li><a href="/cooperativa/spring/logout">Cerrar Sesi�n</a></li>
  	</ul>
	</aside>
	</div>
    <div class="usuarioContent">
    	<div class="box">
    		<div style="text-align: center;"><label class="title is-3">Nuevo Usuario</label></div>
    		<div class="column is-three-quarters">
    			<form:form method="POST" action="registrarusuario" modelAttribute="formUsuarioInfo">
    			<div style="display:none;">
				  <label class="label">ID</label>
				  <div class="control">
				    <form:input path="id" class="input" name="id" type="text" value="0" />
				  </div>
				</div>
				<div class="field">
				  <label class="label">Nombre</label>
				  <div class="control">
				    <form:input path="nombre" class="input" name="nombre" type="text" />
				  </div>
				</div>
				<div class="field">
				  <label class="label">Apellidos</label>
				  <div class="control">
				    <form:input path="apellidos" class="input" name="apellidos" type="text" />
				  </div>
				</div>
				
				<div class="field">
				
				  <label class="label">Rol</label>
				  <div class="control">
				    <div class="select">
				    	<form:select path="idrol">
				    	<form:option value="1">Administrador</form:option>
				    	<form:option value="2">Socio</form:option>
				    	</form:select>
				  	</div>
				  </div>
				  
				</div>
				
				<div class="field">
				  <label class="label">Username</label>
				  <div class="control">
				    <form:input path="username" class="input" name="username" type="text"  />
				  </div>
				</div>
				<div class="field">
				  <label class="label">Correo</label>
				  <div class="control">
				    <form:input path="correo" class="input" name="correo" type="text"  />
				  </div>
				</div>
				<div class="field">
				  <label class="label">Tel�fono</label>
				  <div class="control">
				    <form:input path="telefono" class="input" name="telefono" type="text" />
				  </div>
				</div>
				
				<div class="field">
				  <label class="label">Password</label>
				  <div class="control">
				    <form:input path="password" class="input" name="password" type="text" />
				  </div>
				</div>
		
		
				<div style="text-align: center;">
	    		<button class="button is-link" id="guardarUsuarioBtn" type="submit">Agregar Nuevo Usuario</button>
	    		</div>
    			</form:form>
  
  
    		</div>
    		
    	</div>
    	
    </div>
    </div>
    	
    
    </body>
</html>