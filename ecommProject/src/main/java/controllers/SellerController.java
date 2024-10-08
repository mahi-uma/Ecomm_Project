package controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SellerEntity;
import service.SellerService;
import util.PasswordUtils;

@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static SellerService sellerService_obj;
	public void init(ServletConfig config) throws ServletException{
		sellerService_obj=new SellerService();
	}
    public SellerController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phno = request.getParameter("phno");
        String bus_name=request.getParameter("bus_name");
        String bus_type=request.getParameter("bus_type");
        String bus_address=request.getParameter("bus_address");
        String password="";
		try {
			password = PasswordUtils.hashPassword(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        SellerEntity sellerEntity_obj=new SellerEntity(name,email,password,phno,bus_name,bus_type,bus_address);
        try {
			sellerService_obj.register(sellerEntity_obj);
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher(request.getContextPath()+"/ServerController?userType=seller_register").forward(request, response);
		}
        request.getRequestDispatcher(request.getContextPath()+"/ServerController?userType=seller").forward(request, response);
	}

}
