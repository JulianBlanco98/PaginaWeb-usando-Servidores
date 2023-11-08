<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("nombre") == null) {
	response.sendRedirect("../Login.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rutas Extremadura</title>
<meta charset="UTF-8">
<meta name="autor" content="Julián Blanco González">

<link rel="stylesheet" href="./css/Principal.css" type="text/css">
</head>
<body>

	<img src="./img/Principal/titulo.png" alt="" class="tit">
	<a href='${pageContext.request.contextPath}/Logout.jsp' title=''><img
		src="./img/Principal/salida.png" alt="" class="sal"></a>
	<div class="nombreUsuario">
		<p>Bienvenido: <%=session.getAttribute("nombre")%></p>
	</div>
	<div class="mensaje">
		
		<% String mensaje =(String) request.getAttribute("mensaje");
		
		if(mensaje == null){
			
		}
		else{%>
			
			<input type="checkbox" id="cerrar">
			<label for="cerrar" id="boton_cerrar">X</label>
			<div class="modal">
			
				<div class="contenido">
					
					<p id="texto"><%=mensaje%></p>
					
				</div>
			
			</div> 
			
		<% 	
		}
		%>
	</div>
		
	<div class="contenedor">
		<div class="card">
			<img src="./img/Principal/camino.jpg" alt="">
			<div class="abajo">
				<h4>Rutas</h4>
				<p>Las diferentes rutas de la provincia de Extremadura</p>
				<div class="boton">
					<form id="form1"
						action="${pageContext.request.contextPath}/RutaController"
						method="post">
						<a href="javascript:;"
							onclick="document.getElementById('form1').submit();">Ir a
							histórico</a> <input type="hidden" name="action" value="VerRutas" />
					</form>
				</div>
			</div>
		</div>
		<div class="card">
			<img src="./img/Principal/novedad.jpg" alt="">
			<h4>Novedades</h4>
			<p>Rutas nuevas que han aparecido en Extremadura</p>
			<div class="boton">
				<form id="form2"
						action="${pageContext.request.contextPath}/RutaController"
						method="post">
						<a href="javascript:;"
							onclick="document.getElementById('form2').submit();">Ir a
							novedades</a> <input type="hidden" name="action" value="Novedades" />
					</form>
			</div>
		</div>
		<div class="card">
			<img src="./img/Principal/reserva.jpg" alt="">
			<h4>Reserva</h4>
			<p>Página para poder ver todas tus reservas</p>

			<div class="boton">
				<form id="form3"
						action="${pageContext.request.contextPath}/ReservaController"
						method="post">
						<a href="javascript:;"
							onclick="document.getElementById('form3').submit();">Ir a
							reservas</a> <input type="hidden" name="action" value="VerTodas" />
					</form>
			</div>

		</div>
		<div class="card">
			<img src="./img/Principal/distancia.jpg" alt="" class="ran">
			<h4>Rutas más largas</h4>
			<p>Aquí están las rutas más largas de Extremadura</p>
			

			<div class="boton">
				<form id="form4"
						action="${pageContext.request.contextPath}/RutaController"
						method="post">
						<a href="javascript:;"
							onclick="document.getElementById('form4').submit();">Ir a
							distancia</a> <input type="hidden" name="action" value="VerDis" />
					</form>
			</div>

		</div>
		<div class="card">
			<img src="./img/Principal/dificultad.jpg" alt="">
			<h4>Rutas más difíciles</h4>
			<p>Aquí están las rutas más difíciles de Extremadura</p>
			

			<div class="boton">
				<form id="form5"
						action="${pageContext.request.contextPath}/RutaController"
						method="post">
						<a href="javascript:;"
							onclick="document.getElementById('form5').submit();">Ir a
							dificultad</a> <input type="hidden" name="action" value="VerDif" />
					</form>
			</div>


		</div>
	</div>
	<%@include file="/Footer.jsp" %>

</body>
</html>