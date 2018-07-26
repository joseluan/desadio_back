package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	protected Connection conn;
	private String URL = "jdbc:mysql://localhost:3306/teste?useTimezone=true&serverTimezone=UTC";
    private String USR = "root";
    private String PAS = "admin";   
    
   
    public void criarConexao(){
    	 try {
    		 Class.forName("com.mysql.cj.jdbc.Driver");
    		 conn = DriverManager.getConnection(URL, USR, PAS);
             System.out.println("Conexão com o banco feita!");
         } catch (ClassNotFoundException | SQLException e) {
             System.out.println("Erro: "+e.getMessage());
         }
    }
    
    public void closeAll() throws SQLException{
        conn.close();
    }

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}