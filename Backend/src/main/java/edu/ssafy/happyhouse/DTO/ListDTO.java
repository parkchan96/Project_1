package edu.ssafy.happyhouse.DTO;

public class ListDTO {
	String aptName;
	String dongName;
	String dealAmount;
	String dealYear;
	String dealMonth;
	String dealDay;
	String type;
	String rentMoney;
	String lat;
	String lng;
	String subway;

	public ListDTO() {
		super();
	}

	public ListDTO(String aptName, String dongName, String dealAmount, String dealYear, String dealMonth,
			String dealDay, String type, String rentMoney, String lat, String lng, String subway) {
		super();
		this.aptName = aptName;
		this.dongName = dongName;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.type = type;
		this.rentMoney = rentMoney;
		this.lat = lat;
		this.lng = lng;
		this.subway = subway;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getDealDay() {
		return dealDay;
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getSubway() {
		return subway;
	}

	public void setSubway(String subway) {
		this.subway = subway;
	}

	@Override
	public String toString() {
		return "ListDTO [aptName=" + aptName + ", dongName=" + dongName + ", dealAmount=" + dealAmount + ", dealYear="
				+ dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", type=" + type + ", rentMoney="
				+ rentMoney + ", lat=" + lat + ", lng=" + lng + ", subway=" + subway + "]";
	}
}