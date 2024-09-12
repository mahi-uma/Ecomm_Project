package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServerController")
public class ServerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServerController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s=request.getContextPath();
		String t=request.getParameter("userType");
		String final_page="";
		if(s.equals("/ecommProject") && t.equals("buyer")) {
			final_page="/views/BuyerDashboard.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("seller")) {
			final_page="/views/SellerDashboard.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("product_add")) {
			final_page="/views/addProduct.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("seller_register")) {
			final_page="/views/SellerRegister.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("buyer_register")) {
			final_page="/views/BuyerRegister.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(final_page);
        dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
