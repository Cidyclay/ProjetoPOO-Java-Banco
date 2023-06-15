package bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexao_Banco {
		
	private static final String USERNAME ="cidyclay";
	private static final String PASSWORD ="cidyclay";	
	private static final String DATABASE_URL= "jdbc:mysql://localhost:3306/test";
	
	public static Connection createConnectionToMysql() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
		public static void main(String[] args) throws Exception {
			
			Connection con = createConnectionToMysql();
			if(con!=null) {
				System.out.println("Conexão foi feita com sucesso!");
				con.close();
			}
		}
}
