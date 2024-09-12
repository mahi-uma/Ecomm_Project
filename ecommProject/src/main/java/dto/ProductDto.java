package dto;

import java.sql.Timestamp;
public class ProductDto {
	private int p_id;
	private int s_id;
	private String pro_name;
	private String pdesc;
	private String image;
	private int quantity;
	private int threshold;
	private Double ppp;
	private Double dpp;
	private String cname;
	private Timestamp created_at;
	private Timestamp updated_at;
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
	public ProductDto(int p_id, int s_id, String pro_name,String pdesc,String image, String cname,int quantity, int threshold, Double ppp, Double dpp,
			Timestamp created_at, Timestamp updated_at) {
		super();
		this.p_id = p_id;
		this.s_id = s_id;
		this.pro_name = pro_name;
		this.pdesc=pdesc;
		this.image=image;
		this.setCname(cname);
		this.quantity = quantity;
		this.threshold = threshold;
		this.ppp = ppp;
		this.dpp = dpp;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public Double getPpp() {
		return ppp;
	}
	public void setPpp(Double ppp) {
		this.ppp = ppp;
	}
	public Double getDpp() {
		return dpp;
	}
	public void setDpp(Double dpp) {
		this.dpp = dpp;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
