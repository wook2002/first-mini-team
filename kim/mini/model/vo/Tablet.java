package kim.mini.model.vo;

public class Tablet extends Device{
	private int tIndex; // 태블릿번호
	
	public Tablet() {
		// TODO Auto-generated constructor stub
	}

	public Tablet(String brand, String dName, int dSize, int dHour, int tIndex) {
		super(brand, dName, dSize, dHour);
		this.tIndex = tIndex;
		
	}

	public int gettIndex() {
		return tIndex;
	}

	public void settIndex(int tIndex) {
		this.tIndex = tIndex;
	}

	@Override
	public String deviceInfo() {
		return "(" + tIndex + ") " + super.deviceInfo();
	}
	
	
	

}