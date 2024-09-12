package dao;

import util.DbConnect;
import java.sql.*;
import entity.BuyerEntity;

public class BuyerDao {
	private static Connection con;
	public BuyerDao() {
		con=DbConnect.getInstance().getConnection();
	}
	public void addBuyer(BuyerEntity buyer)throws SQLException {
        String sql = "INSERT INTO buyers (name, email, password, phno, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, buyer.getName());
            stmt.setString(2, buyer.getEmail());
            stmt.setString(3, buyer.getPassword());
            stmt.setString(4, buyer.getPhno());
            stmt.setString(5, buyer.getAddress());
            int res_insert=stmt.executeUpdate();
            if(res_insert==1){
            	System.out.println("Successfully Registered...Now Login");
            }
        }
    }
	public BuyerEntity getBuyerByEmail(String email) {
        String sql = "SELECT * FROM buyers WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	BuyerEntity buyerEntity_obj=new BuyerEntity(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                return buyerEntity_obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	public  int getBuyerIdByEmail(String email) {
		String sql="Select b_id from buyers where email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	return rs.getInt(1);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

