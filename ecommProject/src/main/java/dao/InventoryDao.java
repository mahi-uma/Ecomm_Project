package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entity.InventoryEntity;
import util.DbConnect;
public class InventoryDao {
	private static Connection con;
	public InventoryDao(){
		con=DbConnect.getInstance().getConnection();
	}
	public void addInventory(InventoryEntity inventoryEntity_obj) {
        String sql = "INSERT INTO inventory (p_id, pro_name, s_id, quantity, threshold) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, inventoryEntity_obj.getP_id());
            stmt.setString(2, inventoryEntity_obj.getPro_name());
            stmt.setInt(3, inventoryEntity_obj.getS_id());
            stmt.setInt(4, inventoryEntity_obj.getQuant());
            stmt.setInt(5, inventoryEntity_obj.getThreshold());
            int res_insert=stmt.executeUpdate();
            if(res_insert==1){
            	System.out.print("...........");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
