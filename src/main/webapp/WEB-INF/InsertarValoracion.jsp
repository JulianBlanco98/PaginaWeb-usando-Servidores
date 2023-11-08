<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Insertar valoración</title>
    <meta charset="UTF-8">
    <meta name="autor" content="Julián Blanco González">
    <link rel="stylesheet" href="./css/addVa.css" type="text/css">
</head>
<body>


<header>

	<img src="./img/Valoracion/ins.png" class="tit"> <a
			href="${pageContext.request.contextPath}/VolverPrincipal.jsp">
			<img
			src="./img/inicio.png" alt="" class="back"></a>
    

</header>




<div id="contenedor">

    <form action="ValoracionController" method="post">
        <label for="comment">Comentario:</label>
        <input type="text" id="comment" name="comentario" maxlength="255" required="required">
        <br>
        <br>
        <label for="rating">Valoración:</label>
        <input type="number" id="rating" name="valoracion" min="1" max="5" required="required">
        <br>
        <input type="submit" value="Enviar">
        <input type="reset" value="Reset">
        <input type="hidden" name="action" value="addValor"/>
        <input type="hidden" name="idRutaV" value="<%=request.getAttribute("idRutaV")%>"/>
      </form>

</div>
<%@include file="/Footer.jsp" %>
</body>
</html>