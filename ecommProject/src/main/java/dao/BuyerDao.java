package dao;

import util.DbConnect;
import java.sql.*;

import dto.BuyerDto;
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
            while(rs.next()) {
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
	public void updateBuyerPwdById(int b_id, BuyerEntity buyerEntity_obj) throws SQLException {
		String sql="update buyers set name=?,email=?,password=?,phno=?,address=? where b_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, buyerEntity_obj.getName());
			stmt.setString(2, buyerEntity_obj.getEmail());
			stmt.setString(3, buyerEntity_obj.getPassword());
			stmt.setString(4, buyerEntity_obj.getPhno());
			stmt.setString(5, buyerEntity_obj.getAddress());
			stmt.setInt(6, b_id);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Updated....");
			}
		}
	}
	public void updateBuyerById(BuyerDto bdo) throws SQLException {
		String sql="update buyers set name=?,email=?,phno=?,address=? where b_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, bdo.getName());
			stmt.setString(2, bdo.getEmail());
			stmt.setString(3, bdo.getPhno());
			stmt.setString(4, bdo.getAddress());
			stmt.setInt(5, bdo.getB_id());
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Updated....");
			}
		}
	}
}

