<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="../../css/bulma.min.css" rel="stylesheet">
        <script defer src="../../js/fontawesome.js"></script>
        <title>Inicio</title>
    </head>
    <body>
        <div class="container">
            <div class="columns">
                <div class="column">
                    <h1>Hola, ${usuarioFirmado.username}</h1>
                </div>
            </div>
            <div class="columns">
            	<a href="/cooperativa/spring/prueba">MOSTRAR PRUEBA</a>
            	<br>
            	<a href="/cooperativa/spring/logout">Cerrar Sesión</a>
            </div>
        </div>

    </body>
</html>