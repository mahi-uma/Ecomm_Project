package controllers;
import service.OrderService;
import service.PayService;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BuyerDto;
import dto.OrderDto;
import dto.ProductDto;
import entity.OrderEntity;
import service.ProductService;
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProductService productService_obj;
	private static OrderService orderService_obj;
	private static PayService payService_obj;
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) {
    	productService_obj=new ProductService();
    	orderService_obj=new OrderService();
    	payService_obj=new PayService();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p=request.getParameter("process");
		if ("buyer".equals(p)) {
            response.sendRedirect("/views/BuyerDashboard.jsp");
        }
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String p=request.getParameter("process");
		if(p.equals("buyNow")) {
			int productId = Integer.parseInt(request.getParameter("productId"));
	        int quantity = Integer.parseInt(request.getParameter("quantity"));
	        try {
				ProductDto pdo=productService_obj.getProductById(productId);
				if(pdo.getQuantity()<quantity) {
					response.sendRedirect("/ecommProject/ServerController?userType=buyer&error=Insufficient+stock");
				}
				else {
					session.setAttribute("quantity", quantity);
					Double price=quantity*pdo.getPpp();
					session.setAttribute("productId", productId);
                    session.setAttribute("price", price);
                    Double discountPrice=quantity*pdo.getDpp();
                    session.setAttribute("discountPrice", discountPrice);
                    Double total_price=price-discountPrice;
                    session.setAttribute("Total_Price", total_price);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/item.jsp");
                    dispatcher.forward(request, response); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if ("completePayment".equals(p)) {
            boolean paymentSuccess = true; 
            String payMethod=request.getParameter("paymentMethod");
            System.out.println(payMethod);
            int productId=0;
            if (paymentSuccess) {
            	Object productIdObj = session.getAttribute("productId");
            	if (productIdObj instanceof Integer) {
            	    productId = (Integer) productIdObj;
            	} else if (productIdObj instanceof String) {
            	    productId = Integer.parseInt((String) productIdObj);
            	}
            	BuyerDto bdo=(BuyerDto)session.getAttribute("buyer");
				try {
					ProductDto pdo1 = productService_obj.getProductById(productId);
					int sessionQuantity = 0;
					Object sessionQuantityObj = session.getAttribute("quantity");

					if (sessionQuantityObj instanceof Integer) {
					    sessionQuantity = (Integer) sessionQuantityObj;
					} else if (sessionQuantityObj instanceof String) {
					    sessionQuantity = Integer.parseInt((String) sessionQuantityObj);
					}

					int newQuant = pdo1.getQuantity() - sessionQuantity;
					if(newQuant<pdo1.getThreshold()) {
						
					}
					productService_obj.updateQuantity(newQuant,productId);
					double totalPrice = 0.0;
					Object totalPriceObj = session.getAttribute("Total_Price");

					if (totalPriceObj instanceof String) {
					    totalPrice = Double.parseDouble((String) totalPriceObj);
					} else if (totalPriceObj instanceof Double) {
					    totalPrice = (Double) totalPriceObj;
					}
					orderService_obj.insertOrder(new OrderEntity(bdo.getB_id(),pdo1.getS_id(),pdo1.getP_id(),totalPrice,bdo.getAddress(),"Delivered"));
					OrderDto orderDto_obj=orderService_obj.display(bdo.getB_id(),pdo1.getP_id());
					payService_obj.insertPay(orderDto_obj.getO_id(),"Completed",(String)request.getAttribute("paymentMethod"));
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
                response.sendRedirect("/ecommProject/ServerController?userType=success");
            }
        } else if ("cancelPayment".equals(p)) {
            request.setAttribute("paymentStatus", "Failed");
            response.sendRedirect("/ecommProject/ServerController?userType=fail");
        }
	}

}
