package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DbConnect;

public class ReviewDao {
	private static Connection con;
	public ReviewDao() {
		con=DbConnect.getInstance().getConnection();
	}
	public void addToReview(int b_id, int p_id, double rating, String review) throws SQLException {
		String sql="insert into reviews(b_id,p_id,rating,comments) values(?,?,?,?)";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			stmt.setInt(2, p_id);
			stmt.setDouble(3, rating);
			stmt.setString(4, review);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Added Review");
			}
		}
	}
}
