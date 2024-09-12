package dto;
public class BuyerDto {
	private int b_id;
	private String name;
	private String email;
	private String phno;
	private String address;
	public BuyerDto(int b_id,String name, String email, String phno, String address) {
		super();
		this.b_id=b_id;
		this.name = name;
		this.email = email;
		this.phno = phno;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
}

