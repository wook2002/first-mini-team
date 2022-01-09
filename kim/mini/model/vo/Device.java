package kim.mini.model.vo;

import java.io.Serializable;

public class Device implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brand; // 브랜드명
	private String dName; // 기기명
	private int dSize; // 크기
	private int dHour; // 대여시간
	
	public Device() {
		// TODO Auto-generated constructor stub
	}

	public Device(String brand, String dName, int dSize, int dHour) {
		super();
		this.brand = brand;
		this.dName = dName;
		this.dSize = dSize;
		this.dHour = dHour;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public int getdSize() {
		return dSize;
	}

	public void setdSize(int dSize) {
		this.dSize = dSize;
	}

	public int getdHour() {
		return dHour;
	}

	public void setdHour(int dHour) {
		this.dHour = dHour;
	}

	public String deviceInfo() {
		return "회사명: " + brand + ", 기기명: " + dName + ", 인치: " + dSize;
	}
	

}