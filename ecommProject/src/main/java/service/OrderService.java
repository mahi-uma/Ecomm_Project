package service;

import entity.OrderEntity;

import java.sql.SQLException;

import dao.OrderDao;
import dto.OrderDto;
public class OrderService {
	private static OrderDao orderDao_obj=new OrderDao();
	public void insertOrder(OrderEntity orderEntity) throws SQLException {
		orderDao_obj.insertOrder(orderEntity);
	}
	public OrderDto display(int b_id, int p_id) throws SQLException {
		// TODO Auto-generated method stub
		return orderDao_obj.display(b_id,p_id);
	}

}
