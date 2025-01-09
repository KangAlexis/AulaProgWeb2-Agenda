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

@WebServlet(urlPatterns = { "/Controller", "/main", "/cadastrar", "/select" })
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
		}  else if(action.equals("/select")){
			selecionarContato(request, response);
		}else {
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
	
		System.out.println("recebi");
	}

}
