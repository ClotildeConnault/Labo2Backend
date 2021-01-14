<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail du film</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>

	<table class="table">
		<thead>
			<th>Nom</th>
			<th>Date de sortie</th>
			<th>Type</th>
			<th>Synopsis</th>
	
		</thead>
		
		<tbody>
			
			<tr>
				<td>${film.name}</td>
				<td>${film.dds}</td>
				<td>${film.typeName}</td>
				<td>${film.synopsis}</td>
				<td><button class="btn btn-primary" onClick="window.location='/labo2/films';">Retour aux films</button></td>
			</tr>
		
		</tbody>
	
	</table>
	
</body>
</html>