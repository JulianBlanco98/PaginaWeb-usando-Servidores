<%@page import="es.unex.cum.tw.ef2.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuarios</title>
<link rel="stylesheet" type="text/css" href="./css/usuarios.css" />
</head>
<body>

	<header>
		<img src="./img/admin/gestion.png" class="tit"> <a
			href="${pageContext.request.contextPath}/VolverAdmin.jsp"><img
			src="./img/inicio.png" alt="" class="back"></a>
	</header>
	
	<div id="tabla">

		<%
		ArrayList listaUsuarios = (ArrayList) request.getAttribute("listaUsuario");
		%>

		<table>

			<thead>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Email</th>
				<th>Username</th>
				<th>Modificar Usuario</th>
				<th>Eliminar Usuario</th>
			</thead>
			<%
			for (int i = 0; i < listaUsuarios.size(); i++) {
				Usuario users = (Usuario) listaUsuarios.get(i);
			%>

			<tr>
				<td><%=users.getNombre()%></td>
				<td><%=users.getApellidos()%></td>
				<td><%=users.getEmail()%></td>
				<td><%=users.getNick()%></td>
				<td><button class="green"
						onclick="location.href='UsuarioController?action=ModificarUsuario&idUser=<%=users.getId()%>'">Modificar</button></td>
				<td><button class="red"
						onclick="location.href='UsuarioController?action=BorrarUsuario&idUser=<%=users.getId()%>'">Borrar</button></td>

				<%
				}
				%>
			</tr>

		</table>


	</div>
	<%@include file="/Footer.jsp"%>
	
	
</body>
</html>