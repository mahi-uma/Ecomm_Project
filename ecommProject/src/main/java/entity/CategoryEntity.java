package entity;

public class CategoryEntity {
	private String cname;
	private int s_id;
	public CategoryEntity(int s_id, String cname) {
		super();
		this.s_id=s_id;
		this.cname = cname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	
}
