<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script defer src="resources/js/listarusuarios.js"></script>
<title>Lista de Usuarios</title>
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
        	<li><a href="/cooperativa/spring/listarusuarios" class="is-active">Listar usuarios</a></li>
        </c:if>
        <c:if test="${usuarioFirmado.rol.id == 2}">
        	<!-- <label><b>ERES SOCIO</b></label>  -->
        	<li><a href="/cooperativa/spring/nuevopedido">Nuevo Pedido</a></li>
        	<li><a href="/cooperativa/spring/mispedidos" class="is-active">Mis Pedidos</a></li>	
      	</c:if>
	</ul>
	<p class="menu-label">
		Perfil
  	</p>
  	<ul class="menu-list">
    	<li><a href="/cooperativa/spring/logout">Cerrar Sesión</a></li>
  	</ul>
	</aside>
	</div>
	
    <div class="pedidosListDiv">
    	<div class="box">
    	
    		<c:if test="${usuarioFirmado.rol.id == 1}">
        		<div><label class="title is-3">Usuarios</label></div>
	    		<div>
		    		<table class="table tableUsuarioListDiv">
					  <thead>
					    <tr>
					      <th>ID</th>
					      <th>Nombre</th>
					      <th>Acción</th>
					  	</tr>
					  </thead>
					  <tbody>
		   				<c:forEach items = "${listaUsuarios}" var = "usuario">	
						  	<tr>
					    		<th>${usuario.id}</th>
					      		<td>${usuario.nombre} ${usuario.apellidos} </td>
					      		<td>
					      		<button class="button is-link" id="${usuario.id}">Modificar</button>
				      			<button class="button is-danger" id="rmvBtn_${usuario.id}">Remover</button>
					      		</td>
						    </tr>
		   				</c:forEach>
		   			 </tbody>
					</table>
				</div>
	        </c:if>
	        <c:if test="${usuarioFirmado.rol.id != 1}">
	        	<p>Error!</p>
	      	</c:if>
    		
    	</div>
    </div>


</body>
</html>