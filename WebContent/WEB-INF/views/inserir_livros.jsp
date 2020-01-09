<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserir Livros</title>
</head>
<body>

	<form action="inserirLivro" method="post">
		Nome:<input type="text" name="nome"><br> Autor:<input
			type="text" name="autor"><br> Número de Páginas:<input
			type="number" name="paginas"><br> Disponibilidade:<input
			type="checkbox" name="disp"><br> Descrição:<input
			type="text" name="descricao"><br> <input type="submit"
			name="Adicionar">

	</form>
</body>
</html>