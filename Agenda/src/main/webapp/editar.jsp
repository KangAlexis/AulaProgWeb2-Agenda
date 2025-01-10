<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/telefone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Edit contact</h1>
	<form name="frmContact" action="atualizar">
		<table>
			<tr>
				<td><input type="text" name="id" id="Caixa3" readonly="readonly" value =<% out.print(request.getAttribute("id")); %>></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1" value =<% out.print(request.getAttribute("nome")); %>></td>
			</tr>
			<tr>
				<td><input type="text" name="telefone" class="Caixa2" value =<% out.print(request.getAttribute("telefone")); %>></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="Caixa1" value =<% out.print(request.getAttribute("email")); %>></td>
			</tr>
		</table>
		<input type="submit" value="Salvar" class="Botao1" onclick="validator()">
	</form>
	<script type="text/javascript" src="scripts/Validation.js"></script>
</body>
</html>