package principal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerAccountDAO {
	private Banco banco;
	protected PreparedStatement pstmt;
	protected Statement stmt;

	public CustomerAccountDAO(Banco b) {
		banco = b;
	}

	public void persist(int id_customer, int cpf_cnpj, String nm_customer, boolean is_active, double vl_total)
			throws SQLException {
		String sql = "insert into tb_customer_account " + " (id_customer, cpf_cnpj, nm_customer, is_active, vl_total) "
				+ " values (?,?,?,?,?)";
		pstmt = banco.getConn().prepareStatement(sql);
		pstmt.setInt(1, id_customer);
		pstmt.setInt(2, cpf_cnpj);
		pstmt.setString(3, nm_customer);
		pstmt.setBoolean(4, is_active);
		pstmt.setDouble(5, vl_total);

		pstmt.execute();

	}

	public double findAVG() throws SQLException {
		String sql = "select avg(vl_total) as avg_total from tb_customer_account " + "where vl_total > 560 and "
				+ "id_customer BETWEEN 1500 and 2700";
		stmt = banco.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		return rs.getDouble("avg_total");

	}

	public ResultSet findPeopleByVL() throws SQLException {
		String sql = "select * from tb_customer_account " + "where vl_total > 560 and "
				+ "id_customer BETWEEN 1500 and 2700 " + "order by vl_total DESC";
		stmt = banco.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
}
