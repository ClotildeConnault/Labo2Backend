<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/labo2/films/updatefilm/${film.id}" method="post">
		<label for="name">
		<input type="text" value="${film.name}" id="name" name="name">		
		</label>
		
		<label for="dds">
		<input type="date" id="dds" name="dds" value="${film.dds}">
		</label>
		
		<input name="id" hidden value="${film.id}">
		
		<label for="type">
		<select name="type" id="type">
			<option value="5" 
			<c:if test="${film.type == 5}">selected</c:if>
			>Film</option>
			<option value="6"
			<c:if test="${film.type == 6}">selected</c:if>
			>Série</option>
		</select>
		</label>
		
		<label for="synopsis">
		<input type="text" id="synopsis" name="synopsis" value="${film.synopsis}">
		</label>
		<button type="submit">Envoyer</button>
		
	</form>
	
</body>
</html>