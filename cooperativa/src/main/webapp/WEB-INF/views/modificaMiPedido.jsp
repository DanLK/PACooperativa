<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="../../resources/css/bulma.min.css" rel="stylesheet">
        <link href="../../resources/css/estilo.css" rel="stylesheet">
        <script defer src="../../resources/js/fontawesome.js"></script>
        <script defer src="../../resources/js/modificamipedido.js"></script>
        <title>Modificar Pedido</title>
    </head>
    <body>
    <div class="modal">
				  <div class="modal-background"></div>
				  <div class="modal-content">
				  <div class="box">
				    <article class="media">
  						
  						<div class="media-content">
    						<div class="content">
      							<p>
        							<strong id="messageModalImp">El producto ya se encuentra en el pedido</strong>
      							</p>
    						</div>
  						</div>
					</article>
					</div>
				  </div>
				  <button class="modal-close is-large" aria-label="close"></button>
				</div>
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
	
    <div class="pedidoContent">
    	<div class="box">
    		<div><label class="title is-3">Modificar Pedido ${pedidoID}</label></div>
    		<div>
    			<div class="listaPd">
    			<c:if test="${usuarioFirmado.rol.id == 1}">
        			
        			<div class="select">
				    	<select>
				    		<option value="1">Enviado (en espera de pago)</option>
				    		<option value="2">Pagado</option>
				    		<option value="3">Entregado</option>
				    	</select>
				  	</div>
        		</c:if>
    			
    			
    			
    			
    			<table class="table tablePd">
				  <thead>
				    <tr>
				      <th>ID</th>
				      <th>Nombre</th>
				      <th>Contenido</th>
				      <th>Departamento</th>
				      <th>Precio Unitario</th>
				      <th>Cantidad</th>
				      <th>Subtotal</th>
				      <th></th>
				  	</tr>
				  </thead>
				  <tbody id="tBodyPedido">
			    	<!--  
				    	<tr>
				    		<th>4</th>
				      		<td>Producto de prueba</td>
				      		<td>15ml</td>
				      		<td>Casa y Jardin</td>
				      		<td>15.00</td>
				      		<td><input class="input inputPd" type="text" value="1"></td>
				      		<td>45.00</td>
				      		<td><button class="button is-danger">Remover</button></td>
				    	</tr>
			    	-->
				  </tbody>
				</table>
    			</div>
    		</div>
    		<div>
    		<div style="display:none;" class="tag is-danger is-light" id="errorFoundTag"></div>
    		<div>
    			<label  class="title is-6">Total: $<label class="title is-6" id="totalPedidoLabel">0.00</label></label> 
    		</div>
    		<button class="button is-link" id="guardarPedidoBtn">Guardar Cambios de Pedido</button>
    		</div>
    	</div>
    	<div class="box">
    		<div><label  class="title is-3">Productos</label></div>
    		<div class="listaPr">
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
				      		<td><button class="button is-success">Agregar</button></td>
				    	</tr>
				    </c:forEach>
				  </tbody>
				</table>
    		</div>
    </div>
    </div>
    </div>
    
    	<form:form method="POST" action="${rutaAction}" modelAttribute="pedidoCodificado" class="ghostForm" id="modificarPedidoForm">
			<table>
				<tr>
	            	<td><form:label path="pedidoCodigo">pedidoCodigo</form:label></td>
	                <td><form:input id="registroFormCodigo" path="pedidoCodigo"/></td>
	             </tr>
	             <tr>
	            	<td><form:label path="pedidoTotal">pedidoTotal</form:label></td>
	                <td><form:input id="registroFormPedidoTotal" path="pedidoTotal"/></td>
	             </tr>
	             <tr>
	             	<td colspan="2"><input type="submit" value="Iniciar"/></td>
	             </tr>
	        </table>
		</form:form>
    	
    	<div class="divPedidoCodeGhost" id="codeGhostPedidoItems">${productosEnPedido}</div>
    	<div class="divPedidoCodeGhost" id="codeGhostPedidoID">${pedidoID}</div>
    
    </body>
</html>