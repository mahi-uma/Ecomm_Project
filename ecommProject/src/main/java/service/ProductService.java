package service;
import java.sql.SQLException;
import dao.ProductDao;
import entity.ProductEntity;
import exception.InvalidInputException;
public class ProductService {
	private ProductDao productDao_obj=new ProductDao();
	public void add_product(ProductEntity productEntity_obj) throws InvalidInputException, SQLException {
		productDao_obj.addProduct(productEntity_obj);
	}
	public int getProductIdByImage(String name) {
		return productDao_obj.getProductIdByImage(name);
	}
//	public List<ProductDto> displayAllProducts() {
//		// TODO Auto-generated method stub
//		return productDao_obj.displayAllProducts();
//	}
}
