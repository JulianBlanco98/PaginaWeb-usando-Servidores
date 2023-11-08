<%@page import="es.unex.cum.tw.ef2.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Modificar Usuario</title>
<meta name="autor" content="Julián Blanco González">
<link rel="stylesheet" href="./css/modUsuario.css" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

</head>
<body>

	<header>
		<img src="./img/admin/modU.png" class="tit">
		<a
			href="${pageContext.request.contextPath}/VolverAdmin.jsp"><img
			src="./img/inicio.png" alt="" class="back"></a>
	</header>
	
	<% Usuario aux =(Usuario) request.getAttribute("modisuario"); %>
	
	<div id="contendero">
		
		<div id="content">
		
			<form action="UsuarioController" method="post">

				<label><i class="fas fa-user"></i>Nombre: </label>
				<input type="text" name="nombre" placeholder="<%=aux.getNombre()%>" required="required"/>
				<br>
				<label><i class="fas fa-users-cog"></i>Primer y Segundo Apellido: </label> 
				<input type="text" name="apellidos" placeholder="<%=aux.getApellidos()%>" required="required"/> 
				<br>
				<label><i class="fas fa-envelope"></i>E-mail:</label>
				<input type="email" name="email" placeholder="<%=aux.getEmail()%>" required="required"/> 
				<br>
				<label><i class="fas fa-gamepad"></i>Nombre de Usuario:</label>
				<input type="text" name="username" placeholder="<%=aux.getNick()%>" required="required"/>
				<br>
				<label><i class="fas fa-lock"></i>Contraseña: </label>
				<input type="password" name="password" required="required"/> 
				<br>
				<label><i class="fas fa-lock"></i>Verificar Contraseña: </label>
				<input type="password" name="password2" required="required"/> 
				<input type="submit" value="Enviar" /> <input type="reset" value="Borrar" />
				<input type="hidden" name="action" value="UpdateUsuario" />
				<input type="hidden" name="idUser" value="<%=aux.getId() %>" />



			</form>
		
		</div>
		
	</div>

</body>
</html>