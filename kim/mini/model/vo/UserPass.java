package kim.mini.model.vo;



public class UserPass{
	
	private String passPrice; // 이용권 명
	private int passHour; // 시간
	private String tempId; // 임시아이디저장

	public UserPass() {}

	//(임시아이디)만 받는 생성자
	public UserPass(String tempId) { 
		this.tempId = tempId;
	}

	//(이용권,시간) 받는 생성자
	public UserPass(int passHour, String passPrice) { 
		this.passPrice = passPrice;
		this.passHour = passHour;
	}

	//시간 가격 출력
	public String passInfo() {
		return " " + passHour + " 시간 가격 : " + passPrice ;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getPassPrice() {
		return passPrice;
	}

	public void setPassPrice(String passPrice) {
		this.passPrice = passPrice;
	}

	public int getPassHour() {
		return passHour;
	}

	public void setPassHour(int passHour) {
		this.passHour = passHour;
	}
	

}