package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dto.BuyerDto;
import dto.SellerDto;
import exception.InvalidInputException;
import exception.UserNotFoundException;
import service.BuyerService;
import service.SellerService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static BuyerService buyerService_obj;
	public static SellerService sellerService_obj;
    public LoginServlet() {
        super();
    }
    public void init(ServletConfig config) throws ServletException{
    	buyerService_obj=new BuyerService();
    	sellerService_obj=new SellerService();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        PrintWriter out = response.getWriter();
        if ("buyer".equals(userType)) {
        	try {
        		BuyerDto buyerDto_obj=buyerService_obj.login(username,password);
				HttpSession session = request.getSession();
				session.setAttribute(userType,buyerDto_obj);
				response.sendRedirect("/ecommProject/ServerController?userType=buyer");
			} catch (InvalidInputException e) {
				request.setAttribute("errorMessage", e.getMessage());
				request.getRequestDispatcher("/").forward(request, response);
			}
        }
        else if ("seller".equals(userType)) {
            try {
				SellerDto sellerDto_obj=sellerService_obj.login(username,password);
				HttpSession session = request.getSession();
				session.setAttribute(userType,sellerDto_obj);
				response.sendRedirect("/ecommProject/ServerController?userType=seller");
			} catch (UserNotFoundException | SQLException e) {
				request.setAttribute("errorMessage", e.getMessage());
				request.getRequestDispatcher("/").forward(request, response);
			}
        }
    }
		
}

