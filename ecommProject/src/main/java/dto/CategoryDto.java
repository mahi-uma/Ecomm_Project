package dto;

public class CategoryDto {
	private int cat_id;
	private String cname;
	public CategoryDto(int cat_id, String cname) {
		super();
		this.cat_id = cat_id;
		this.cname = cname;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
