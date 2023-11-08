<%@page import="es.unex.cum.tw.ef2.model.Reserva"%>
<%@page import="es.unex.cum.tw.ef2.model.Ruta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Reserva</title>
<link rel="stylesheet" type="text/css" href="./css/res.css" />
</head>
<body>

	<%
	Reserva aux2 = (Reserva) request.getAttribute("reserva_usuario");
	Ruta aux3 =(Ruta) request.getAttribute("rutaaux");
	%>

	<header>

		<img src="./img/Reserva/reserva.png" class="tit"> <a
			href="${pageContext.request.contextPath}/VolverPrincipal.jsp"><img
			src="./img/inicio.png" alt="" class="back"></a> Usuario:
		<%=session.getAttribute("nombre")%>
		con ruta:
		<%=aux3.getIdRuta()%>
	</header>

	<div class="card">
		<img
			src="${pageContext.request.contextPath}/img/<%=aux3.getPathImagen()%>"
			alt="Imagen" class="card-image">
		<div class="card-content">
			<h2 class="card-title"><%=aux3.getNombreRuta()%></h2>
			<label class="card-label"><strong>Fecha de Reserva: </strong> <%=aux2.getFechaReserva() %></label> <label
				class="card-label"><strong>Número de Personas: </strong> <%=aux2.getNumeroPersonas() %></label>
		</div>
	</div>


	
	<%@include file="/Footer.jsp"%>

</body>
</html>