<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listagem de Livros</title>
</head>
<body>


	<jsp:useBean id="livroDAO" class="br.com.biblioteca.db.LivroDAO">
		<jsp:setProperty name="livroDAO" property="idLivro"
			value="${param.id}" />
	</jsp:useBean>

	<c:if test="${empty p}">
		<c:set var="p" value="${livroDAO.livro}" />
	</c:if>



	


	<form method="post" action="SalvarLivro">

		<input type="hidden" name="id" value="${p.id}" />
		<table id="tabela">
			<tr>
				<td width="200px"><strong>Nome</strong></td>
				<td><input name="nome" type="text" value="${p.nome}" /></td>
			</tr>

			<tr>
				<td><strong>Promo&ccedil;&atilde;o</strong></td>
				<td><input type="checkbox" name="promocao"
					<c:if test="${p.disponibilidade == true}" >
												checked="checked"
									</c:if> />
				</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"><h3>Autor</h3></td>
			</tr>
			<tr>
				<td><strong>Autor</strong></td>
				<td><input name="autor" type="text" value="${p.autor}" size="4" /></td>
			</tr>


		</table>
		<br /> <input type="hidden" name="acao" value="salvar" /> <input
			type="submit" name="salvar" value="Salvar">&nbsp;
		<c:if test="${not empty p.id}">
			<input type="button" value="Excluir" onclick="confirmarExclusao()">
		</c:if>
	</form>





</body>
</html>