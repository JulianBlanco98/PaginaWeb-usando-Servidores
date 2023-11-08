<%@page import="es.unex.cum.tw.ef2.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver las Reservas</title>
<link rel="stylesheet" type="text/css" href="./css/reservas.css" />
</head>
<body>

	<%
	if (session.getAttribute("nombre") == null) {
		response.sendRedirect("./Login.jsp");
	}
	%>

	<header>

		<img src="./img/Reserva/listado.png" class="tit"> <a
			href="${pageContext.request.contextPath}/VolverPrincipal.jsp"><img
			src="./img/inicio.png" alt="" class="back"></a>

	</header>

	<div id="tabla">

		<%
		ArrayList listaReservas = (ArrayList) request.getAttribute("listaReservas");
		%>

		<table>

			<thead>
				<th>Imagen</th>
				<th>Nombre Ruta</th>
				<th>Fecha de Reserva</th>
				<th>Número de Personas</th>
				<th>Modificar Reserva</th>
				<th>Eliminar Reserva</th>
			</thead>
			<%
			for (int i = 0; i < listaReservas.size(); i++) {
				Reservas reservas = (Reservas) listaReservas.get(i);
			%>

			<tr>
				<td><img
					src="${pageContext.request.contextPath}/img/<%=reservas.getPathImagen()%>" /></td>
				<td><%=reservas.getNombreRuta()%></td>
				<td><%=reservas.getFechaReserva()%></td>
				<td><%=reservas.getNumPersonas()%></td>
				<td><button class="green"
						onclick="location.href='ReservaController?action=ModificarReserva&idRuta=<%=reservas.getIdRuta()%>'">Modificar</button></td>
				<td><button class="red"
						onclick="location.href='ReservaController?action=BorrarReserva&idRuta=<%=reservas.getIdRuta()%>'">Borrar</button></td>

				<%
				}
				%>
			</tr>

		</table>


	</div>
	<%@include file="/Footer.jsp"%>

</body>
</html>