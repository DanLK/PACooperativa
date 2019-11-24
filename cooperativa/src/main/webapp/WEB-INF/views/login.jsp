<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio de sesión</title>
<link href="../../css/estilo.css" rel="stylesheet">
</head>
<body>
	<h2>Inicio de sesi&oacute;n</h2>
	<c:if test="${not empty error}">
		<div class="errorblock">${error}</div>
	</c:if>
	<p>
		<form:form method="POST" action="loginProcess" modelAttribute="formLogin">
			<table>
				<tr>
	            	<td><form:label path="username">Username</form:label></td>
	                <td><form:input path="username"/></td>
	                <td><form:errors path="username" cssClass="error" /></td>
	             </tr>
	             <tr>
	            	<td><form:label path="password">Contraseña</form:label></td>
	                <td><form:password path="password"/></td>
	                <td><form:errors path="password" cssClass="error"/></td>
	             </tr>
	             <tr>
	             	<td colspan="2"><input type="submit" value="Iniciar"/></td>
	             </tr>
	        </table>
		</form:form>
	</p>
	<!--  <p>&iquest;A&uacute;n no eres miembro? <a href="usuario/registro3">Reg&iacute;strate</a></p> -->
</body>
</html>