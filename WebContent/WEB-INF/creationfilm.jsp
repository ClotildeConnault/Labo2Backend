<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création d'un film</title>
</head>
<body>

<div class="row">
	
</div>

<form action="/labo2/films/creationfilm" method="post">
		<label for="name">
		<input type="text" placeholder="nom" id="name" name="name">		
		</label>
		
		<label for="dds">
		<input type="date" id="dds" name="dds">
		</label>
		
		<label for="type">
		<select name="type" id="type">
			<option value="5">Film</option>
			<option value="6">Série</option>
		</select>
		</label>
		
		<label for="synopsis">
		<input type="text" id="synopsis" name="synopsis">
		</label>
		<button type="submit">Envoyer</button>
		
	</form>

</body>
</html>