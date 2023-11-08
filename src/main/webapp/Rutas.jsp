<%@page import="es.unex.cum.tw.ef2.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RutasExtremadura Histórico</title>
<link rel="stylesheet" type="text/css" href="css/rutas.css" />
</head>
<body>

	<header>
		<img src="img/Ruta/historico.png" class="tit"> <a
			href="${pageContext.request.contextPath}/VolverInicio.jsp"><img
			src="img/inicio.png" alt="" class="back"></a>
	</header>

	<div id="contenedor">

		<%
		ArrayList listaRutas = (ArrayList) request.getAttribute("listaRutaNoLogin");
		ArrayList media = (ArrayList) request.getAttribute("medias");
		%>


		<%
		for (int i = 0; i < listaRutas.size(); i++) {

			Ruta ruta = null;
			try {
				ruta = (Ruta) listaRutas.get(i);
				ValoracionMedia aux = (ValoracionMedia) media.get(i);
		%>

		<div class="recubrir">
			<div class="carta">

				<div class="carta-imagen">
					<img
						src="${pageContext.request.contextPath}/img/<%=ruta.getPathImagen()%>" />
				</div>
				<div class="carta-datos">
					<div class="carta-header"><%=ruta.getNombreRuta()%>
						(<%=(i + 1)%>)
					</div>
					<div class="carta-body2">
						<div class="fila">
							<div class="columna">
								<p>
									<strong>Distancia: </strong><%=ruta.getMetros()%>
									km
								</p>
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								<p>
									<strong>Fecha de Incorporación: </strong><%=ruta.getFechaIncorporacion()%></p>
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								<p>
									<strong>Máximo de Usuarios: </strong><%=ruta.getMaximoUsuario()%></p>
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								<p>
									<strong>Dificultad: </strong><%=ruta.getDificultad()%></p>
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								<p>


									<strong>Valoración Media: </strong><%=aux.getMediaV()%></p>
							</div>
						</div>
						

					</div>
				</div>
			</div>
		</div>

		<%
		} catch (IndexOutOfBoundsException e) {
		%>

			<div class="recubrir">
			<div class="carta">

				<div class="carta-imagen">
					<img
						src="${pageContext.request.contextPath}/img/<%=ruta.getPathImagen()%>" />
				</div>
				<div class="carta-datos">
					<div class="carta-header"><%=ruta.getNombreRuta()%>
						(<%=(i + 1)%>)
					</div>
					<div class="carta-body2">
						<div class="fila">
							<div class="columna">
								<p>
									<strong>Distancia: </strong><%=ruta.getMetros()%>
									km
								</p>
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								<p>
									<strong>Fecha de Incorporación: </strong><%=ruta.getFechaIncorporacion()%></p>
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								<p>
									<strong>Máximo de Usuarios: </strong><%=ruta.getMaximoUsuario()%></p>
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								<p>
									<strong>Dificultad: </strong><%=ruta.getDificultad()%></p>
							</div>
						</div>
						<div class="fila">
							<div class="columna">
								<p>


									<strong>Valoración Media: </strong>No existen valoraciones
								</p>
							</div>
						</div>
						

					</div>
				</div>
			</div>
		</div>




		<%
		}
		}
		%>
	</div>

	<%@include file="Footer.jsp"%>



</body>
</html>