package dao;
import java.sql.*;
import entity.SellerEntity;
import dto.SellerDto;
import util.DbConnect;
public class SellerDao {
	private static Connection con;
	public SellerDao(){
		con=DbConnect.getInstance().getConnection();
	}
	public void addSeller(SellerEntity seller) throws SQLException {
        String sql = "INSERT INTO sellers (name, email, password, phno, bus_name,bus_type,bus_address) VALUES (?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, seller.getName());
            stmt.setString(2, seller.getEmail());
            stmt.setString(3, seller.getPassword());
            stmt.setString(4, seller.getPhno());
            stmt.setString(5, seller.getBus_name());
            stmt.setString(6, seller.getBus_type());
            stmt.setString(7,seller.getBus_address());
            int res_insert=stmt.executeUpdate();
            if(res_insert==1){
            	System.out.println("Successfully Registered...Now Login");
            }
        }
    }
	public int getSellerIdByEmail(String email) throws SQLException {
		String sql="Select s_id from sellers where email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	return rs.getInt(1);
            }
		}
		return 0;
	}
	public SellerEntity getSellerByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM sellers WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	return new SellerEntity(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }

        }
        return null;
	}
}