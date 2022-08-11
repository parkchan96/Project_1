package edu.ssafy.happyhouse.DTO;

public class FavoriteDTO {
	private int houseNo;
	private String id;
	private String aptName;
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String roadName;

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	@Override
	public String toString() {
		return "FavoriteDTO [houseNo=" + houseNo + ", id=" + id + ", aptName=" + aptName + ", dongCode=" + dongCode
				+ ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", roadName=" + roadName + "]";
	}
}
