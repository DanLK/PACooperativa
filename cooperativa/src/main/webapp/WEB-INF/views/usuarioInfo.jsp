<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="resources/css/bulma.min.css" rel="stylesheet">
        <link href="resources/css/estilo.css" rel="stylesheet">
        <script defer src="resources/js/fontawesome.js"></script>
        <script defer src="resources/js/usuarioinfo.js"></script>
        <title>Información</title>
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
        	<li><a href="/cooperativa/spring/pedidossemana" >Pedidos Semana</a></li>
        	<li><a href="/cooperativa/spring/pedidosenviados" >Pedidos Enviados</a></li>
        	<li><a href="/cooperativa/spring/pedidospagados" >Pedidos Pagados</a></li>
        	<li><a href="/cooperativa/spring/pedidosentregados" >Pedidos Entregdos</a></li>
        	<li><a href="/cooperativa/spring/allpedidos" >Todos los Pedidos</a></li>
        </c:if>
        <c:if test="${usuarioFirmado.rol.id == 2}">
        	<!-- <label><b>ERES SOCIO</b></label>  -->
        	<li><a href="/cooperativa/spring/nuevopedido">Nuevo Pedido</a></li>
        	<li><a href="/cooperativa/spring/mispedidos" >Mis Pedidos</a></li>	
      	</c:if>
	</ul>
	<p class="menu-label">
		Perfil
  	</p>
  	<ul class="menu-list">
  		<li><a href="/cooperativa/spring/usuarioinfo" class="is-active">Ver/Editar Información</a></li>
    	<li><a href="/cooperativa/spring/logout">Cerrar Sesión</a></li>
  	</ul>
	</aside>
	</div>
	<div class="infoContent">
    	<h1 class="title is-1">${usuarioFirmado.nombre},</h1>
    	<h3 class="subtitle is-3">Tu información es:</h3>
    	<hr>Da click en modificar para modificar algún campo.
    	<hr>Luego guarda los cambios con el botón "Guardar cambios".
    </div>
    <div class="infoContent">
    	<form:form method="POST" action="editarInfo" modelAttribute="formUsuarioInfo">
			<table class="box tableInfo">
				<tr>
	            	<td><form:label path="username">Username</form:label></td>
	                <td><form:input path="username" value="${usuarioFirmado.username}" disabled="true"/></td>
	                
	             </tr>
	             <tr>
	            	<td><form:label path="password">Contraseña</form:label></td>
	                <td><form:input path="password" value="${usuarioFirmado.password}" disabled="true"/></td>
	                
	             </tr>
	             <tr>
	            	<td><form:label path="nombre">Nombre</form:label></td>
	                <td><form:input path="nombre" value="${usuarioFirmado.nombre}" disabled="true"/></td>
	                
	             </tr>
	             <tr>
	            	<td><form:label path="apellidos">Apellidos</form:label></td>
	                <td><form:input path="apellidos" value="${usuarioFirmado.apellidos}" disabled="true"/></td>
	                
	             </tr>
	              <tr>
	            	<td><form:label path="correo">Correo</form:label></td>
	                <td><form:input path="correo" value="${usuarioFirmado.correo}" disabled="true"/></td>
	                
	             </tr>
	             <tr>
	            	<td><form:label path="telefono">Telefono</form:label></td>
	                <td><form:input path="telefono" value="${usuarioFirmado.telefono}" disabled="true"/></td>
	                
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