package AWT.Login;

public class LoginFrameVo {
private String id;
private String password;

public LoginFrameVo() {
	
}

public LoginFrameVo(String id, String password) {
	this.id = id;
	this.password =password;
}

public String getLid() {
	return id;
}

public String getLpassword() {
	return password;
}
}
