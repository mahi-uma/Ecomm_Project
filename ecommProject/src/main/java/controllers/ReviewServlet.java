package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BuyerDto;
import service.ReviewService;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static ReviewService reviewService_obj; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init(ServletConfig config) {
    	reviewService_obj=new ReviewService();
    }
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double rating=Double.parseDouble(request.getParameter("rating"));
		String review=request.getParameter("review");
		int p_id=Integer.parseInt(request.getParameter("productId"));
		HttpSession session =request.getSession();
		BuyerDto buyer = (BuyerDto) session.getAttribute("buyer");
		try {
			reviewService_obj.addToReview(buyer.getB_id(),p_id,rating,review);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/ecommProject/ServerController?userType=buyer");
	}

}
