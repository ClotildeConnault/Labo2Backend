<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes films et séries</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>

	<c:if test="${message != null}">
		<p>${message}</p>
	</c:if>
	
	<table class="table">
		<thead>
			<th>Nom</th>
			<th>Date de sortie</th>
			<th>Type</th>
	
		</thead>
		
		<tbody>
			<c:forEach var="film" items="${films}">
			<tr>
				<td>${film.name}</td>
				<td>${film.dds}</td>
				<td>${film.typeName}</td>
				<td><button class="btn btn-primary" onClick="window.location='/labo2/films/detailfilm/${film.id}';">Détails</button>
				<button class="btn btn-danger" onClick="window.location='/labo2/films/delete/${film.id}';">Supprimer</button></td>
			</tr>
		</c:forEach>
		
		</tbody>
	
	</table>
	<button class="btn btn-primary" onClick="window.location='/labo2/films/creationfilm';">Ajouter</button>
	

	

</body>
</html>