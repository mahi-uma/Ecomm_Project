package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BuyerDto;
import dto.ProductDto;
import service.CartService;
/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static CartService cartService_obj;
	public void init(ServletConfig config) {
		cartService_obj=new CartService();
	}
    public CartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		List<ProductDto> products = (List<ProductDto>) session.getAttribute("products");
		List<Boolean> cart=(List<Boolean>)session.getAttribute("cart");
		BuyerDto buyer=(BuyerDto)session.getAttribute("buyer");
		System.out.println(request.getParameter("productId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
        String action = request.getParameter("action");
        if (products == null || cart == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if necessary session attributes are missing
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getP_id() == productId) {
                if ("addcart".equals(action)) {
                    cart.set(i, true);
                    try {
                    	cartService_obj.addToCart(buyer.getB_id(), productId,1,products.get(i).getPpp());
            		} catch (SQLException e) {
            			System.out.println(e.getMessage());
            		}
            		response.sendRedirect(request.getContextPath() + "/ServerController?userType=buyer");
                } else if ("removecart".equals(action)) {
                    cart.set(i, false);
                    try {
                    	cartService_obj.removeFromCart(buyer.getB_id(), productId);
            		} catch (SQLException e) {
            			System.out.println(e.getMessage());
            		}
            		response.sendRedirect(request.getContextPath() + "/ServerController?userType=buyer");
                }
                break;
            }
        }
	}

}
