package service;

import java.sql.SQLException;

import dao.PayDao;

public class PayService {
	private static PayDao payDao_obj=new PayDao();
	public void insertPay(int i, String string, String attribute) throws SQLException {
		payDao_obj.insertPay(i,string,attribute);
	}
}
