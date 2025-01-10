<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Contato"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Contato> lista = (ArrayList<Contato>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<link rel="icon" href="imagens/pencil.png">
<link rel="stylesheet" href="style.css">
<title>Agenda de contatos</title>
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novo.html" class="Botao1">Novo contato</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getTelefone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td>
					<a href="selecionar?id=<%=lista.get(i).getId()%>" class="Botao1">Editar</a></a>
					<a href="javascript:confirmarExclusao(<%=lista.get(i).getId() %>)" class="Botao2">Deletar</>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script type="text/javascript" src = "scripts/Confirmacao.js"></script>
</body>
</html>