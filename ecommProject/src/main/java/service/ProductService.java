package service;
import java.sql.SQLException;
import java.util.List;
import dto.ProductDto;
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
	public List<ProductDto> displayProducts() throws SQLException {
		return productDao_obj.displayProducts();
	}
	public ProductDto getProductById(int productId) throws SQLException {
		return productDao_obj.getProductById(productId);
	}
}
