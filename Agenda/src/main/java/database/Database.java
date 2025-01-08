package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	// Parâmetros para conexão
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/db_agenda?useTimezone=true&serverTimezone=UTC";
	private static String USER = "root";
	private static String PASSWORD = "root";

	// Método de conexão
	public static Connection conection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

		}catch (SQLException e) {
			System.out.println("Usuário ou senha incorreta");
			System.out.println("erro -> "+ e);
		} 
		
		catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
		return con;
	}

	// Teste de conexão
	public void testConnection() {
		try {
			Connection con = conection();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
