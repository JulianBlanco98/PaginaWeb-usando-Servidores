<%@page import="es.unex.cum.tw.ef2.model.Reserva"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Reserva</title>
<meta name="autor" content="Julián Blanco González">
<link rel="stylesheet" href="./css/addRe.css" type="text/css">
</head>
<body>

<%
	Reserva reserva = (Reserva) request.getAttribute("reserva");
	%>

<header>

	<img src="./img/Reserva/modificar.png" class="tit"> <a
			href="${pageContext.request.contextPath}/VolverPrincipal.jsp">
			<img
			src="./img/inicio.png" alt="" class="back"></a>
</header>


<div id="contenedor">
		<form action="ReservaController" method="post">

			<label for="fecha">Fecha de Reserva:</label> <input type="date"
				id="fecha" name="fecha" placeholder="<%=reserva.getFechaReserva() %>" required="required"> <br> <label for="numPersonas">Número
				de personas</label> <input type="number" id="numPersonas" name="numPersonas"
				min="1" max="10" placeholder="<%=reserva.getNumeroPersonas() %>" required="required"> <br> <input type="submit"
				value="Enviar"> 
				<input type="reset" value="Reset">
				<input type="hidden" name="action" value="updateReserva" /> 
				<input type="hidden" name="idRutaV" value="<%=reserva.getIdRuta()%>" />
		</form>
	</div>
</body>
</html>