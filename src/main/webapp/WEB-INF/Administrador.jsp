<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet" type="text/css" href="./css/admin.css" />
</head>
<body>

	<header>
		<img src="./img/admin/admin.png" class="tit">
		<a href='${pageContext.request.contextPath}/Logout.jsp' title=''><img
		src="./img/Principal/salida.png" alt="" class="sal"></a>
	</header>

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

	<div id="bienvenido">
		<p>
			Esta es la página del
			<%=session.getAttribute("tipo")%></p>
	</div>

	<div class="contenedor">

		<div class="card">
			<img src="./img/usuario.png" alt="" class="u">
			<div class="abajo">
				<h4>Crear y Añadir Usuarios</h4>
				<p>Página que contiene el formulario para añadir usuarios</p>
				<div class="boton">
					<a href="${pageContext.request.contextPath}/InsertarUsuario.jsp" title="Entrada">Añadir Usuario</a>
				</div>
			</div>
		</div>
		<div class="card">
			<img src="./img/usuarios.png" alt="" >
			<h4>Gestionar Usuarios</h4>
			<p>Aquí puedes modificar o borrar los usuarios de la base de
				datos</p>
			<div class="boton">
				<form id="form7"
					action="${pageContext.request.contextPath}/UsuarioController"
					method="post">
					<a href="javascript:;"
						onclick="document.getElementById('form7').submit();">Gestión
						usuarios</a> <input type="hidden" name="action" value="verUsuarios" />
				</form>
			</div>
		</div>
		<div class="card">
			<img src="./img/subir.png" alt="">
			<h4>Subir Rutas</h4>
			<p>Página para poder ver subir nuevas rutas a la página web</p>

			<div class="boton">
				<a href="${pageContext.request.contextPath}/InsertarRuta.jsp" title="Entrada">Ir a añadir rutas</a>
			</div>

		</div>

	</div>
	<%@include file="/Footer.jsp"%>
</body>
</html>