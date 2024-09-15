package controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BuyerDto;
import entity.BuyerEntity;
import service.BuyerService;
import service.ProductService;
import util.PasswordUtils;

@WebServlet("/BuyerController")
public class BuyerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static BuyerService buyerService_obj;
    public static ProductService productService_obj;
    public BuyerController() {
        super();
    }
    public void init(ServletConfig config) throws ServletException{
    	buyerService_obj=new BuyerService();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phno = request.getParameter("phno");
        String address = request.getParameter("address");
        String password="";
		try {
			password = PasswordUtils.hashPassword(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        BuyerEntity buyerEntity_obj=new BuyerEntity(name, email, password, phno, address);
        try {
			buyerService_obj.register(buyerEntity_obj);
		} catch (SQLException e) {
			 request.setAttribute("errorMessage", e.getMessage());
			 request.getRequestDispatcher(request.getContextPath()+"/SellerController?userType=buyer_register").forward(request, response);
		}
        request.getRequestDispatcher(request.getContextPath()+"/ServerController?userType=buyer").forward(request, response);
	}
}
