package AWT.AdminFrame.Create;

public class CreateFrameVo {
	String id;
	String password;
	String name;
	String birthdate;
	String pnum;
	String email;
	String addr;

	public CreateFrameVo() {

	}

	public CreateFrameVo(String id,String password, String name, String birthdate, String pnum, String email, String addr ) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.birthdate = birthdate;
		this.pnum = pnum;
		this.email = email;
		this.addr = addr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPnum() {
		return pnum;
	}

	public void setPnum(String pnum) {
		this.pnum = pnum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
