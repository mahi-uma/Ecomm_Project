package dto;

public class InventoryDto {
	private String pro_name;
	private int quant;
	private int threshold;
	public InventoryDto(String pro_name, int quant, int threshold) {
		super();
		this.pro_name = pro_name;
		this.quant = quant;
		this.threshold = threshold;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
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
