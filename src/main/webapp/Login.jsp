<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=utf8" />
<title>Página de Login</title>
<link rel="stylesheet" type="text/css" href="css/login.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
</head>

<body>

	<div class="mensaje">

		<%
		String mensaje = (String) request.getAttribute("mensaje");

		if (mensaje == null) {

		} else {
		%>

		<input type="checkbox" id="cerrar"> <label for="cerrar"
			id="boton_cerrar">X</label>
		<div class="modal">

			<div class="contenido">

				<p id="texto"><%=mensaje%></p>

			</div>

		</div>

		<%
		}
		%>
	</div>
	<%
	if (session.getAttribute("nombre") != null) {
		response.sendRedirect("WEB-INF/Principal.jsp");
	} else {
	%>
	<div id="header">
		<img src="img/Login/login.png" class="tit"> <a
			href="${pageContext.request.contextPath}/VolverInicio.jsp"><img
			src="img/atras.png" alt="" class="back"></a>
	</div>
	<div id="contenedor">


		<%
		if (request.getParameter("mensaje") != null) {
			out.println(request.getParameter("error"));
		}
		%>
		<div id="Content">
			<form action="UsuarioController" method="post">
				<label><i class="fas fa-user"></i> Usuario:</label> <input
					type="text" name="user" id="user" /> <br /> <label><i
					class="fas fa-lock"></i> Password:</label> <input type="password"
					name="password" id="password" /> <br /> <input type="submit"
					value="Validar" /> <input type="hidden" name="action"
					value="UsuarioLogin" />
				<p>
					Si no estas dado de alta, pincha <a href="Registro.html">aqui</a>
				</p>
			</form>
		</div>
	</div>
	<%
	}
	%>
	<%@include file="Footer.jsp"%>
</body>

</html>