<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertar ruta</title>
<link rel="stylesheet" type="text/css" href="./css/insertarRuta.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
</head>
<body>

	<header>
		<img src="./img/Ruta/addRuta.png" class="tit"> <a
			href="${pageContext.request.contextPath}/VolverAdmin.jsp"><img
			src="./img/inicio.png" alt="" class="back"></a>
	</header>
	
	<div id="content">
	
		<form action="RutaController" method="post" enctype="multipart/form-data">


				
				<label><i class="fas fa-signature"></i>Nombre de la Ruta: </label>
				<input type="text" name="nombreRuta" required="required"/> 
				<br>
				<label><i class="fas fa-calendar-alt"></i>Fecha de Incorporación: </label>
				<input type="date" name="fecha" required="required"/>
				<br>
				<label><i class="fas fa-users"></i>Máximo de Usuarios </label> 
				<input type="number" name="maxUsuarios" required="required"/> 
				<br>
				<label><i class="fas fa-sort-numeric-up-alt"></i>Dificultad:</label>
				<input type="number" name="dificultad" min="1" max="5" required="required" /> 
				<br>
				<label><i class="fas fa-exchange-alt"></i>Distancia (m):</label>
				<input type="number" name="distancia" required="required"/>
				<br>
				<label><i class="fas fa-camera"></i>Imagen: </label>
				<input type="file" name="imagen" required="required"/>
				<br>
				<br>
				<input type="submit" value="Enviar" /> 
				<input type="reset" value="Borrar" />
				<input type="hidden" name="action" value="CrearRuta" />
				<input type="hidden" name="formulario" value="ruta" />
				
			</form>
	
	</div>


</body>
</html>