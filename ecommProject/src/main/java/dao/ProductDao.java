package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.ProductEntity;
import util.DbConnect;
public class ProductDao {
	private static Connection con;
	public ProductDao(){
		con=DbConnect.getInstance().getConnection();
	}
	public void addProduct(ProductEntity productEntity_obj) throws SQLException {
		String sql="insert into products(s_id,pro_name,pro_desc,image,cname,quantity,threshold,price_per_piece,discount_per_piece) values(?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, productEntity_obj.getS_id());
			stmt.setString(2,productEntity_obj.getProduct_name());
			stmt.setString(3,productEntity_obj.getPdesc());
			stmt.setString(4,productEntity_obj.getImage());
			stmt.setString(5,productEntity_obj.getCname());
			stmt.setInt(6, productEntity_obj.getQuant());
			stmt.setInt(7, productEntity_obj.getThreshold());
			stmt.setDouble(8, productEntity_obj.getPrice_per_piece());
			stmt.setDouble(9, productEntity_obj.getDiscount_per_piece());
			int res_insert=stmt.executeUpdate();
			if(res_insert==1) {
				System.out.println("Successfully Added.....");
			}
		} 
	}
	public int getProductIdByImage(String name) {
		String sql="select p_id from products where image = ?";
		int dup=0;
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setString(1, name);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				dup=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dup;
	}
}
