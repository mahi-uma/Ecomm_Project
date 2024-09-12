package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.BuyerEntity;
import service.BuyerService;
import service.ProductService;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phno = request.getParameter("phno");
        String address = request.getParameter("address");
        if (phno.length() != 10) {
        	request.setAttribute("error", "Phone number must be 10 digits.");
            request.getRequestDispatcher(request.getContextPath()+"/SellerController?userType=buyer_register").forward(request, response);
            return;
        }
        BuyerEntity buyerEntity_obj=new BuyerEntity(name, email, password, phno, address);
        try {
			buyerService_obj.register(buyerEntity_obj);
		} catch (SQLException e) {
			 request.setAttribute("errorMessage", e.getMessage());
			 request.getRequestDispatcher(request.getContextPath()+"/SellerController?userType=buyer_register").forward(request, response);
		}
//        diplayProducts();
        request.getRequestDispatcher(request.getContextPath()+"/ServerController?userType=buyer").forward(request, response);
	}
//	private static void diplayProducts() {
//	    List<ProductDto> productDto_objs = productService_obj.displayAllProducts();
//	    System.out.println("p_id\ts_id\tpro_name\tpro_desc\tiamge\t\tcname\tquantity\tthreshold\tprice\tdiscount\tcname\tCreated\tUpadted \n");
//	    if (productDto_objs != null && !productDto_objs.isEmpty()) {
//	        for (ProductDto product : productDto_objs) {
//	        	System.out.println(product.getP_id()+"\t"+product.getS_id()+"\t"+product.getPro_name()+"\t\t"+product.getPdesc()+"\t"+product.getImage()+"\t"+product.getQuantity()+"\t"+product.getCname()+"\t"+product.getThreshold()+"\t"+product.getPpp()+"\t"+product.getDpp()+"\t"+product.getCreated_at()+"\t"+product.getUpdated_at());
//	        }
//	    } else {
//	        System.out.println("No products found for the seller.");
//	    }
//	}
}
