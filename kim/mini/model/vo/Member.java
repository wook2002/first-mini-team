package kim.mini.model.vo;

import java.io.Serializable;

public class Member implements Serializable {
	
	/**
	 * 객체를 입출력하기 위해 직렬화 해주기
	 */
	private static final long serialVersionUID = 1L;
	//필드
	private String id; // 아이디
	private String pwd; // 비밀번호
	private String name; // 이름
	private int age; //나이
	private char gender; // 성별
	private String phoneNum; // 전화번호
	private double mileage; // 마일리지 적립
	private int usePass = 0; //0으로 고정(수정1 로그인에 메서드, 수정2 이거 =0으로)
	
	public Member() {
		//기본생성자
	}
	//회원가입에서 저장될 회원정보 매개변수
	public Member(String id, String pwd, String name, int age, char gender, String phoneNum) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phoneNum = phoneNum;
	}
	public Member(String id, String pwd, String name, int age, char gender, String phoneNum, double mileage, int usePass) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phoneNum = phoneNum;
		this.mileage = mileage;
		this.usePass = usePass;
	}
	
	//getter/setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public double getMileage() {
		return mileage;
	}
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	public int getUsePass() {
		return usePass;
	}
	public void setUsePass(int usePass) {
		this.usePass = usePass;
	}
	
	//회원정보 메소드
	public String memInfo() {
		return "아이디:" + id + " 비밀번호:" + pwd + " 이름:" + name + " 나이:" + age + " 성별:" + gender
				+ " 전화번호:" + phoneNum + " 마일리지:" + mileage + " 이용권:" + usePass;
	}
	

}