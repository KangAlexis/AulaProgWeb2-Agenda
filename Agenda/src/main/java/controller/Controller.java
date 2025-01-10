package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContatoDAO;
import database.Database;
import model.Contato;

@WebServlet(urlPatterns = { "/Controller", "/main", "/cadastrar", "/selecionar", "/atualizar", "/deletar" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Contato contato;
	private ContatoDAO contatoDAO;

	public Controller() {
		super();
		this.contato = new Contato();
		this.contatoDAO = new ContatoDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		if (action.equals("/main")) {
			listaContato(request, response);
		} else if (action.equals("/cadastrar")) {
			novoContato(request, response);
		} else if(action.equals("/selecionar")){
			selecionarContato(request, response);
		} else if(action.equals("/atualizar")) {
			atualizarContato(request, response);
		}else if(action.equals("/deletar")) {
			deletarContato(request, response);
		}
		else {
			response.sendRedirect("index.html");
		}

	}
	
	protected void listaContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados Contact
		ArrayList<Contato> lista = contatoDAO.listaContato();

		// teste de recebimento da lista
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).toString());
		}

		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Imprime no terminal as variáveis
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("telefone"));
		System.out.println(request.getParameter("email"));

		// Armazena as variáveis no objeto Contato
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));

		System.out.println(contato.toString());
		
		// Chamar método que irá fazer a inserção do objeto no banco
		contatoDAO.cadastrarContato(contato);

		// Redireciona para a pagina Main
		response.sendRedirect("main");
	}
	protected void selecionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		contato.setId(id);
		
		contatoDAO.selecionarContato(contato);
		
		request.setAttribute("id", contato.getId());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("telefone", contato.getTelefone());
		request.setAttribute("email", contato.getEmail());
		
		RequestDispatcher rd =  request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	protected void atualizarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		contato.setId(Integer.parseInt(request.getParameter("id")));
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		
		contatoDAO.atualizarContato(contato);
		
		response.sendRedirect("main");
	}
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		contato.setId(Integer.parseInt(request.getParameter("id")));
		
		contatoDAO.deletarContato(contato);
		
		response.sendRedirect("main");
	}
}
