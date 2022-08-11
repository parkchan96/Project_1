package edu.ssafy.happyhouse.DTO;

public class FacilityDTO {
	private String faclTyCd;
	private String faclTyName;

	public String getFaclTyCd() {
		return faclTyCd;
	}

	public void setFaclTyCd(String faclTyCd) {
		this.faclTyCd = faclTyCd;
	}

	public String getFaclTyName() {
		return faclTyName;
	}

	public void setFaclTyName(String faclTyName) {
		this.faclTyName = faclTyName;
	}

	@Override
	public String toString() {
		return "FacilityDTO [faclTyCd=" + faclTyCd + ", faclTyName=" + faclTyName + "]";
	}
}
