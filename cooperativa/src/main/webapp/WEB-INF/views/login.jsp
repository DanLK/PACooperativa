<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio de sesión</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="resources/css/bulma.min.css" rel="stylesheet">
<link href="resources/css/estilo.css" rel="stylesheet">
</head>
<body>
	<div class="container">
	  	
	  	<div class="columns is-mobile">
	  		<div class="column">
	  			<h1>Administración de Cooperativas - Inicio de sesi&oacute;n</h1>
	  		</div>
	  	</div>
	  	<div class="columns is-mobile">
	  		<div class="column  is-three-quarters">
	  			<c:if test="${not empty error}">
				<div class="errorblock">${error}</div>
			</c:if>
			<p>
				<form:form method="POST" action="loginProcess" modelAttribute="formLogin">
					<div class="field">
					  <label class="label">Usuario</label>
					  <div class="control">
					    <input class="input" name="username" type="text" >
					  </div>
					</div>
					<div class="field">
					  <label class="label">Contraseña</label>
					  <div class="control">
					    <input class="input" name="password" type="password"">
					  </div>
					</div>
					<button class="button is-link" type="submit">Entrar</button>
				</form:form>
			</p>
	  		</div>
	  	</div>
	  		
	  	</div>
		
		<!--  <p>&iquest;A&uacute;n no eres miembro? <a href="usuario/registro3">Reg&iacute;strate</a></p> -->
	</div>
	
</body>
</html>