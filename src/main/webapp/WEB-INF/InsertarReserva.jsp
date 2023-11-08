<%@page import="es.unex.cum.tw.ef2.model.Ruta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hacer Reserva</title>
<meta name="autor" content="Julián Blanco González">
<link rel="stylesheet" href="./css/addRe.css" type="text/css">
</head>
<body>


	<% Ruta aux =(Ruta) request.getAttribute("ruta"); %>

	<header>
		<img src="./img/Reserva/ins.png" class="tit"> <a
			href="${pageContext.request.contextPath}/VolverPrincipal.jsp"> <img
			src="./img/inicio.png" alt="" class="back"></a>
	</header>
	
	<div class="nombreUsuario">
		<p>Reserva la ruta: <%=aux.getNombreRuta()%></p>
	</div>

	<div id="contenedor">
		<form action="ReservaController" method="post">

			<label for="fecha">Fecha de Reserva:</label> <input type="date"
				id="fecha" name="fecha" required="required"> <br> <label for="numPersonas">Número
				de personas</label> <input type="number" id="numPersonas" name="numPersonas"
				min="1" max="10" required="required"> <br> <input type="submit"
				value="Enviar"> <input type="reset" value="Reset"> <input
				type="hidden" name="action" value="addReserva" /> <input
				type="hidden" name="idRutaV"
				value="<%=aux.getIdRuta()%>" />
		</form>
	</div>
	<%@include file="/Footer.jsp"%>
</body>
</html>