package edu.ssafy.happyhouse.DTO;

public class DustDTO {
	String date;
	String guguncode;
	String gugunname;
	String state;
	String totalnum;
	String material;
	String NO2;
	String Ozone;
	String CO;
	String gas;
	String dust;
	String ultradust;
	String condition;
	
	public DustDTO(String gugunname, String dust, String ultradust) {
		super();
		this.gugunname = gugunname;
		this.dust = dust;
		this.ultradust = ultradust;
	}

	public DustDTO(String gugunname, String dust, String ultradust, String condition) {
		super();
		this.gugunname = gugunname;
		this.dust = dust;
		this.ultradust = ultradust;
		this.condition = condition;
	}

	public DustDTO(String date, String guguncode, String gugunname, String state, String totalnum, String material,
			String nO2, String ozone, String cO, String gas, String dust, String ultradust) {
		super();
		this.date = date;
		this.guguncode = guguncode;
		this.gugunname = gugunname;
		this.state = state;
		this.totalnum = totalnum;
		this.material = material;
		this.NO2 = nO2;
		this.Ozone = ozone;
		this.CO = cO;
		this.gas = gas;
		this.dust = dust;
		this.ultradust = ultradust;
	}

	public DustDTO(String date, String guguncode, String gugunname, String state, String totalnum, String material,
			String nO2, String ozone, String cO, String gas, String dust, String ultradust, String condition) {
		super();
		this.date = date;
		this.guguncode = guguncode;
		this.gugunname = gugunname;
		this.state = state;
		this.totalnum = totalnum;
		this.material = material;
		NO2 = nO2;
		Ozone = ozone;
		CO = cO;
		this.gas = gas;
		this.dust = dust;
		this.ultradust = ultradust;
		this.condition = condition;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGuguncode() {
		return guguncode;
	}

	public void setGuguncode(String guguncode) {
		this.guguncode = guguncode;
	}

	public String getGugunname() {
		return gugunname;
	}

	public void setGugunname(String gugunname) {
		this.gugunname = gugunname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getNO2() {
		return NO2;
	}

	public void setNO2(String nO2) {
		NO2 = nO2;
	}

	public String getOzone() {
		return Ozone;
	}

	public void setOzone(String ozone) {
		Ozone = ozone;
	}

	public String getCO() {
		return CO;
	}

	public void setCO(String cO) {
		CO = cO;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

	public String getDust() {
		return dust;
	}

	public void setDust(String dust) {
		this.dust = dust;
	}

	public String getUltradust() {
		return ultradust;
	}

	public void setUltradust(String ultradust) {
		this.ultradust = ultradust;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "DustDTO [date=" + date + ", guguncode=" + guguncode + ", gugunname=" + gugunname + ", state=" + state
				+ ", totalnum=" + totalnum + ", material=" + material + ", NO2=" + NO2 + ", Ozone=" + Ozone + ", CO="
				+ CO + ", gas=" + gas + ", dust=" + dust + ", ultradust=" + ultradust + ", condition=" + condition
				+ "]";
	}
}