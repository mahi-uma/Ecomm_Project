package entity;

public class InventoryEntity {
	private int p_id;
	private String pro_name;
	private int s_id;
	private int quant;
	private int threshold;
	public InventoryEntity(int p_id, String pro_name, int s_id, int quant, int threshold) {
		super();
		this.p_id = p_id;
		this.pro_name = pro_name;
		this.s_id = s_id;
		this.quant = quant;
		this.threshold = threshold;
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
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
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
}
