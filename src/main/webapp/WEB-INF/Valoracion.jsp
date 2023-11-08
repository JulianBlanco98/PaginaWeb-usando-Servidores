<%@page import="es.unex.cum.tw.ef2.model.ValoracionMedia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.unex.cum.tw.ef2.model.Valoracion"%>
<%@page import="es.unex.cum.tw.ef2.model.Ruta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Valoración</title>
<link rel="stylesheet" type="text/css" href="./css/v.css" />

</head>
<body>

	<%
	Valoracion aux2 = (Valoracion) request.getAttribute("v_usuario");
	Ruta aux3 = (Ruta) request.getAttribute("rutaaux");
	ArrayList <ValoracionMedia> mediaLista =(ArrayList) request.getAttribute("mediaLista");
	%>
	<header>

		

			<img src="./img/Valoracion/v.png" class="tit"> <a
				href="${pageContext.request.contextPath}/VolverPrincipal.jsp"><img
				src="./img/inicio.png" alt="" class="back"></a> 

	</header>


	<% try {
	
		ValoracionMedia v = mediaLista.get(aux3.getIdRuta()-1);
	
	%>
		
	<div class="card">
		<img
			src="${pageContext.request.contextPath}/img/<%=aux3.getPathImagen()%>"
			alt="Imagen" class="card-image">
		<div class="card-content">
			<h2 class="card-title"><%=aux3.getNombreRuta()%></h2>
			<label class="card-label"><strong>Comentario: </strong> <%=aux2.getComentario()%></label> 
			<label class="card-label"><strong>Valoración: </strong> <%=aux2.getValoracion()%></label>
			<label class="card-label"><strong>Valoración media de la ruta: </strong> <%=v.getMediaV()%></label>
		</div>
	</div>
		
		
		<% 
	}
	catch(IndexOutOfBoundsException e){%>
		
	<div class="card">
		<img
			src="${pageContext.request.contextPath}/img/<%=aux3.getPathImagen()%>"
			alt="Imagen" class="card-image">
		<div class="card-content">
			<h2 class="card-title"><%=aux3.getNombreRuta()%></h2>
			<label class="card-label"><strong>Comentario: </strong> <%=aux2.getComentario()%></label> 
			<label class="card-label"><strong>Valoración: </strong> <%=aux2.getValoracion()%></label>
			<label class="card-label"><strong>Valoración media de la ruta: </strong> No existe valoraciones de esta ruta</label>
		</div>
	</div>
		<% 
	}
%>

	<%@include file="/Footer.jsp"%>

</body>
</html>