package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.Database;
import model.Contato;

public class ContatoDAO {

	private Database database;

	public void cadastrarContato(Contato contato) {
		String sql = "INSERT INTO tb_contatos " + "(nome, telefone, email) VALUES (?,?,?)";
		try {
			Connection con = database.conection();
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());

			pst.executeUpdate();
			con.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public ArrayList<Contato> listaContato() {

		ArrayList<Contato> listaContato = new ArrayList<>();

		String sql = "SELECT * FROM tb_contatos ORDER BY nome";
		try {
			Connection con = database.conection();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// O laço será executado enquanto houver contatos
			while (rs.next()) {
				// Variáveis de apoio que recebem os dados do banco
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);

				listaContato.add(new Contato(id, nome, telefone, email));
			}
			con.close();
			return listaContato;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void selecionarContato(Contato contato) {
		String sql = "SELECT * FROM tb_contatos WHERE id = ?";
		try {
			Connection con = database.conection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contato.getId());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				contato.setId(rs.getInt(1));
				contato.setNome(rs.getString(2));
				contato.setTelefone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Erro -> " + e);
		}
	}
	
	public void atualizarContato(Contato contato) {
		String sql =  "UPDATE tb_contatos SET"
					+ " nome = ?, "
					+ " telefone = ? ,"
					+ " email = ? "
					+ "WHERE id = ?";
		
		try {
			Connection con = database.conection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, contato.getId());
			
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deletarContato(Contato contato) {
		String sql = "DELETE FROM tb_contatos WHERE id = ?";
		
		try {
			Connection con =  database.conection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, contato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
