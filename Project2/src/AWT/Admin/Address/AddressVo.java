package AWT.Admin.Address;

public class AddressVo {
	   String zipNo; // 우편번호
	   String lnmAdres; // 시군구명
	   String rnAdres; // 도로명

	   public AddressVo(String zipNo, String lnmAdres, String rnAdres) {
	      this.zipNo = zipNo ;
	      this.lnmAdres = lnmAdres;
	      this.rnAdres = rnAdres;
	   }

	public String getZipNo() {
		return zipNo;
	}

	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}

	public String getLnmAdres() {
		return lnmAdres;
	}

	public void setLnmAdres(String lnmAdres) {
		this.lnmAdres = lnmAdres;
	}

	public String getRnAdres() {
		return rnAdres;
	}

	public void setRnAdres(String rnAdres) {
		this.rnAdres = rnAdres;
	}
	}
