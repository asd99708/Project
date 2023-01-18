package AWT;

public class MainFrameVo {
	String id;
	String commute;
	
	public MainFrameVo() {

	}
	
	public MainFrameVo(String id,String commute) {
		this.id = id;
		this.commute = commute;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommute() {
		return commute;
	}

	public void setCommute(String commute) {
		this.commute = commute;
	}
}
