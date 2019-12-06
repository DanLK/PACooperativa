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
				<div class="control">
			  		<label class="tag is-danger is-light">${error}</label>
			  	</div>
			</c:if>
			<p>
				<form:form method="POST" action="loginProcess" modelAttribute="formLogin">
					<div class="field">
					  <form:label class="label" path="username">Usuario</form:label>
					  <div class="control">
					    <form:input class="input" path="username" name="username" type="text" />
					  </div>
					  <div class="control">
					  	<form:errors path="username" cssClass="tag is-danger is-light" />
					  </div>
					</div>
					<div class="field">
					  <form:label path="password" class="label">Contraseña</form:label>
					  <div class="control">
					    <form:input path="password" class="input" name="password" type="password"/>
					  </div>
					  <div class="control">
					  	<form:errors path="password" cssClass="tag is-danger is-light" />
					  </div>
					</div>
					<button class="button is-link" type="submit">Entrar</button>
				</form:form>
			</p>
	  		</div>
	  	</div>
	  		
	  	</div>
	
</body>
</html>