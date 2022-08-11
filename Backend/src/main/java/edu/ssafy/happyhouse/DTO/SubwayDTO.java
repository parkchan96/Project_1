package edu.ssafy.happyhouse.DTO;

public class SubwayDTO {
	private String lat;
	private String lng;

	public SubwayDTO(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
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

	@Override
	public String toString() {
		return "SubwayDTO [lat=" + lat + ", lng=" + lng + "]";
	}
}