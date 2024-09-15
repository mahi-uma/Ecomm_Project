package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DbConnect;

public class PayDao {
	private static Connection con;
	public PayDao() {
		con=DbConnect.getInstance().getConnection();
	}
	public void insertPay(int i, String string, String attribute) throws SQLException {
		String sql="insert into pay_summary(o_id,payment_status,payment_method) values(?,?,?)";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, i);
			stmt.setString(2, string);
			stmt.setString(3, attribute);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Pay Inserted");
			}
		}
	}

}
