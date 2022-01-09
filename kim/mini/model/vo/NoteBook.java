package kim.mini.model.vo;

public class NoteBook extends Device{
	private int nIndex; // 노트북 번호
	
	public NoteBook() {
		// TODO Auto-generated constructor stub
	}

	public NoteBook(String brand, String dName, int dSize, int dHour,int nIndex) {
		super(brand, dName, dSize, dHour);
		this.nIndex = nIndex;
	}

	public int getnIndex() {
		return nIndex;
	}

	public void setnIndex(int nIndex) {
		this.nIndex = nIndex;
	}

	@Override
	public String deviceInfo() {
		// 조회하기 쉽게 수정
		return "(" + nIndex + ") " + super.deviceInfo();
	}

	
	
	
	
	

}
