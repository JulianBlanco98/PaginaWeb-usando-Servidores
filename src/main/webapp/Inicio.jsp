<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Página de Inicio</title>
<link rel="stylesheet" type="text/css" href="css/inicio.css" />
</head>
<body>
	<header>
		<img src="img/Inicio/titulo.png" class="tit"></img>
	</header>
	<div id="bienvenido">
		<p>Bienvenido a la web para ver las rutas de Extremadura</p>


	</div>
	<div class="contenedor">
		<div class="card">
			<img src="./img/Principal/camino.jpg" alt="">
			<div class="abajo">
				<h4>Login</h4>
				<p>Necesitas loguearte para ver todas las funciones de la página web</p>
				<div class="boton">
					<a href="Login.jsp" title="Entrada">Login</a>
				</div>
			</div>
		</div>
		<div class="card">
			<img src="./img/rutas.jpg" alt="">
			<div class="abajo">
				<h4>Rutas</h4>
				<p>Estas son todas las rutas que hay en la provincia de Extremadura</p>
				<div class="boton">

					<form id="form1"
						action="${pageContext.request.contextPath}/RutaController"
						method="post">
						<a href="javascript:;"
							onclick="document.getElementById('form1').submit();">Ver
							todas las rutas </a> <input type="hidden" name="action"
							value="VerRutasNoLogin" />
					</form>
				</div>
			</div>
		</div>



	</div>
	<%@include file="Footer.jsp"%>

</body>
</html>