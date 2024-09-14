package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import entity.BuyerEntity;
import service.BuyerService;
import service.ProductService;
import service.WishListService;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BuyerService buyerService_obj;
	private static WishListService wishListService_obj;
	private static ProductService productService_obj;
    public UpdateServlet() {
        super();
    }
    public void init(ServletConfig config) {
    	buyerService_obj=new BuyerService();
    	wishListService_obj=new WishListService();
    	productService_obj=new ProductService();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		BuyerDto buyer=(BuyerDto)session.getAttribute("buyer");
		if(buyer==null) {
			response.sendRedirect("/ecommProject/");
			return;
		}
		String s=request.getContextPath();
		String t=request.getParameter("action");
		if(s.equals("/ecommProject") && t.equals("display")) {
			try {
				List<Integer> p_id = wishListService_obj.display(buyer.getB_id());
				List<ProductDto> pwish = new ArrayList<>();
				for (Integer pId : p_id) {
	                int productId = pId.intValue();
	                ProductDto product = productService_obj.getProductById(productId);
	                System.out.println(product);
	                if (product != null) {
	                	pwish.add(product);
	                }
	            }
				request.setAttribute("products", pwish);
				request.getRequestDispatcher("/ServerController?userType=displayWish").forward(request, response);
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String type=request.getParameter("userType");
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phno = request.getParameter("phno");
        String address = request.getParameter("address");
        boolean flag = request.getParameter("updatePassword") != null;
		if(type.equals("buyerUpdate")) {
			BuyerDto buyer=(BuyerDto)session.getAttribute("buyer");
			if(buyer==null) {
				request.getRequestDispatcher("/ecommProject/").forward(request, response);
				return;
			}
			else {
				if(flag) {
					String newPassword = request.getParameter("newPassword");
					BuyerEntity buyerEntity_obj=new BuyerEntity(name,email,newPassword,phno,address);
					try {
						buyerService_obj.updateBuyerPwdById(buyer.getB_id(),buyerEntity_obj);
		
						session.setAttribute("updateSuccess", "Profile updated successfully!");
						response.sendRedirect("/ecommProject/ServerController?userType=buyer");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					BuyerDto bdo=new BuyerDto(buyer.getB_id(),name,email,phno,address);
					try {
						buyerService_obj.updateBuyerById(bdo);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					session.setAttribute("updateSuccess", "Profile updated successfully!");
					response.sendRedirect("/ecommProject/ServerController?userType=buyer");
				}
			}
		}
	}

}
