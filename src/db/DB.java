package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB { // aqui eu configuro a conexão ou fecho
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null) {
			try { // estabelecendo conexão
				Properties props = loadProperties();
				String url = props.getProperty("dburl"); // url do banco de dados
				conn = DriverManager.getConnection(url, props); // conectando com o banco de dados
				
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;	
	}
	
	public static void closeConnection() { // fechar conexão
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// carregando dados do db.properties (usuario, link, senha)
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	
	
}
