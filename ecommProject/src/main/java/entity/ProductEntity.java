package entity;

public class ProductEntity {
	private int s_id;
	private String product_name;
	private int quant;
	private String cname;
	private int threshold;
	private Double price_per_piece;
	private Double discount_per_piece;
	private String pdesc;
	private String image;
	public ProductEntity(int s_id, String product_name, String pdesc, String image, String cname,int quant, int threshold, Double price_per_piece,
			Double discount_per_piece) {
		super();
		this.s_id = s_id;
		this.product_name = product_name;
		this.pdesc=pdesc;
		this.image=image;
		this.cname=cname;
		this.quant = quant;
		this.threshold = threshold;
		this.price_per_piece = price_per_piece;
		this.discount_per_piece = discount_per_piece;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProduct_name() {
		return product_name;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getQuant() {
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public Double getPrice_per_piece() {
		return price_per_piece;
	}
	public void setPrice_per_piece(Double price_per_piece) {
		this.price_per_piece = price_per_piece;
	}
	public Double getDiscount_per_piece() {
		return discount_per_piece;
	}
	public void setDiscount_per_piece(Double discount_per_piece) {
		this.discount_per_piece = discount_per_piece;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
