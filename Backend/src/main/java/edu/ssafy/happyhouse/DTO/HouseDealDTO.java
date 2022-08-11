package edu.ssafy.happyhouse.DTO;

public class HouseDealDTO {

	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private String dealAmount;
	private String area;
	private String floor;

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

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "HouseDealDTO [dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay
				+ ", dealAmount=" + dealAmount + ", area=" + area + ", floor=" + floor + "]";
	}
}