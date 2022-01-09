package kim.mini.controller;
//controller : 사용자의 요청에 의해서 서비스 호출전 파라미터셋팅.
//(서비스 단을 생략해서 현재는 비즈니스 로직(데이터의 수정,가공처리)을 구현)

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;





import kim.mini.model.vo.Device;
import kim.mini.model.vo.Member;
import kim.mini.model.vo.UserPass;

public class CafeManager {

	// ===========================필드부============================
	// =================회원==================
	// *스터디에 앉을 수 있는 자리는 총10자리, 가입 할 수 있는 멤버 수도 이에 맞춤
	public static final int NUM = 10; // 최대 회원 수 10
	private Member[] mem = new Member[NUM];
	private int memCount = 0; // 현재 회원 수
	// ================대여물품=================
	private Device[] dList = new Device[2]; // device 임시 데이터
	private Device[] nList = new Device[10]; // 파일에서 읽을 데이터
	// =================이용권==================
	static UserPass upS = new UserPass();
	private final int passMAX = 10; // 총 10개 저장가능
	private int passCount = 3; // 배열에 들어있는 이용권 수
	private UserPass[] up = new UserPass[passMAX];





//=================인스턴스 초기화 블럭을 이용하여 정보 초기화==================
	{
	mem[0] = new Member("co1", "pass01", "김재호", 20, 'M', "010-6666-7777");
	mem[1] = new Member("co2", "pass02", "김태훈", 20, 'M', "010-5555-6666");
	mem[2] = new Member("co3", "pass03", "신현지", 20, 'F', "010-4444-5555");
	mem[3] = new Member("co4", "pass04", "양수연", 20, 'F', "010-3333-4444");
	mem[4] = new Member("co5", "pass05", "이재욱", 20, 'M', "010-2222-3333");
	memCount = 5;
	}

	// 대여물품 초기화
	{
		dList[0] = new Device("애플", "맥북", 13, 0);
		dList[1] = new Device("엘지", "그램", 16, 0);
	}

	// 이용권 초기화
	{
		up[0] = new UserPass(1, "1천원");
		up[1] = new UserPass(4, "2천원");
		up[2] = new UserPass(6, "3천원");

	}

	
	// =================생성자부==================
	public CafeManager() {
	} // 기본생성자

	
	// ==================메소드===================
	public int getMemCount() {
		// 지금 회원 수 몇 명?
		return memCount;
	}

	public Member checkId(String id) {
		// mem에 중복id가 있는지 확인해
		Member m = null; // m이라는 멤버에 null이라고 치자
		for (int i = 0; i < memCount; i++) { // memCount=현재회원수만큼 반복하면됨
			if (mem[i].getId().equals(id)) {
				m = mem[i]; // mem에 있는 중복아이디를 m에 넣기
			}
		}
		return m; // 중복아이디가 있다 -> m 리턴, 중복아이디 없다 -> null 리턴
	}

	public Member checkPwd(String pwd) {
		// mem에 중복pwd가 있는지 확인해
		Member m = null; // m이라는 멤버에 null이라고 치자
		for (int i = 0; i < memCount; i++) { // memCount=현재회원수만큼 반복하면됨
			if (mem[i].getPwd().equals(pwd)) {
				m = mem[i]; // mem에 있는 중복아이디를 m에 넣기
			}
		}
		return m; // 중복아이디가 있다 -> m 리턴, 중복아이디 없다 -> null 리턴
	}

	public void signUp(Member m1) {
		// 새로운 멤버 Member[] mem에 넣고 회원 수 1증감
		mem[memCount++] = m1;

	}

	// 임시로 장치 정보 저장할 메서드 시작
	public void deviceSave() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("device.txt"))) {
			for (int i = 0; i < dList.length; i++) {
				oos.writeObject(dList[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 임시 메서드 끝

	// 기기 정보 조회 메서드
	public void checkDevice() {
		System.out.println("=== 기기 조회 ===");
		try {
			for (int i = 0; i < nList.length; i++) {
				System.out.print(nList[i].deviceInfo());
				if (nList[i].getdHour() == 1) {
					System.out.print(", 대여중");
				}
				System.out.println();
			}
		} catch (NullPointerException e) {

		}

	}

	// 대여 가능 목록 출력
	public void readDevice() {
		int count = 0;

		// File 읽기
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("device.txt"))) {
			while (true) {
				nList[count++] = (Device) ois.readObject();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 기기 대여 메서드
	public void rentD(int deviceN) {

		if (nList[deviceN - 1].getdHour() == 1) {
			System.out.println("이미 사용 중인 기기입니다.");
		} else {
			nList[deviceN - 1].setdHour(1);
			System.out.println("대여가 완료되었습니다");
		}
	}

	// 기기 반납 메서드
	public void returnD(int deviceN) {
		nList[deviceN - 1].setdHour(0);
	}

	// 내정보조회
	public String getMember(String pwd) {

		String info = null;

		for (int i = 0; i < memCount; i++) {
			if (mem[i].getPwd().equals(pwd)) {
				info = mem[i].memInfo();
			}

		}
		return info;
	}


	//마이페이지 - 내 정보 수정(setter이용)
	public void updateMem(Member mem, int menu2, String update) {
		//1번 이름 수정
		if (menu2 == 1) {
			
			mem.setName(update);
		//2번 비밀번호 수정
		} else if (menu2 == 2) {
			
			mem.setPwd(update);
		//3번 전화번호 수정
		} else if (menu2 == 3) {
			mem.setPhoneNum(update);

		}

	}

	// IO - 회원가입 목록 적기
	public void fileSave() {
		// ObjectOutputStream - 기반스트림
		// FileOutputStream - 보조스트림
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("member.txt"))) {

			for (int i = 0; i < mem.length; i++) {
				oos.writeObject(mem[i]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// IO - 회원가입 목록 읽기
	public void fileRead() {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("member.txt"))) {

			while ((mem[memCount] = (Member) ois.readObject()) != null) {
				System.out.println(mem[memCount].memInfo());
			}

		// catch 문구 순서는 상관X
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("읽기완료");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// (1)로그인 성공한거 주소를 받아서
	// 임시아이디(UserPass.tempId)에 저장만 하는 메서드
	// 나중에 사용하기 위해서 static 메서드로 해줬음
	// (static 안하면 저장한 id 지워짐)
	public static void getMIdPass(Member mId) {

		String id = mId.getId();
		upS.setTempId(id); // 아이디 성공한거 임시 저장

	}

	// (2)로그인 성공한 것을 주소로 보내주는 메서드
	// 로그인 성공한 것을 사용하기 위해서
	// 이 메서드 쓰면 로그인 성공된 주소를 반환시켜줌
	public Member loginUser() {
		Member result = null;
		for (int i = 0; i < memCount; i++) {
			if (mem[i] != null) {
				// 임시 저장된 아이디 (로그인 성공된 아이디) == 일치하는 아이디 찾아서
				if ((mem[i].getId()).equals(upS.getTempId())) {
					result = mem[i]; // 일치하는 아이디의 주소값을 넣어줌
				}
			}
		}
// 로그인 성공한 객체의 주소반환(Member의 getter, setter 가능해짐)
		return result;
	}
	
	// 뷰의 입력값 체크
	public boolean checkSc(int scPass) {
		if (passCount <= scPass) {
			System.out.println((scPass - 1) + "까지만 입력해 주세요");
			// MAX는 전체길이, 출력은 인덱스로 그래서 -1 해줬음
			return false;
		}
		return true;
	}
	
	//이용권 주소만 반환해주는 메서드
	public UserPass[] getPass() {
		return up;
	}

	//이용권 전체 출력
	public void searchPass() {
		for (int i = 0; i < passCount; i++) {
			if (up.length != 0) {
				System.out.println("[" + i + "]" + up[i].passInfo());
			}
		}
	}


	//id로 이용권 선택
	public String selectPass(int scPass) {
		Member loginMem = loginUser(); // 로그인된 주소
		int sum = 0;
	
		int loginPrice = loginMem.getUsePass(); // 로그인된 요금
			
		int scPrice = up[scPass].getPassHour(); // 선택한 시간요금
		
		sum = loginPrice + scPrice; // 위에 둘다 더해서
	
		loginMem.setUsePass(sum); // 로그인된 요금에 넣어줌
	
		String loginId = loginMem.getId(); // 로그인된 아이디 담고

	
		return loginId + " 님의 남은 시간은 " + sum + " 시간 입니다";
	
	}

	//이용권 등록
	public void sysWriterPass(int scHour, String scName) {
		// 초기 passCount = 3(등록된 이용권 수) , PSMAX = 10(전체 수)
		if (passCount < passMAX) {
			up[passCount++] = new UserPass(scHour, scName);
			return;
		} else if (passCount <= passMAX) {
			System.out.println("이용권 등록 수가 초과되었습니다");
		}

		
	}

	// 이용권 삭제
	public void sysDeletePass(int scDel) {
		up[scDel] = null;
	
		// 배열 밀기(선택한 인덱스에서 끝까지),(passCount-1 =>인덱스)
		for (int i = scDel; i < passCount - 1; i++) {
			up[i] = up[i + 1];
	
		}
		up[passCount - 1] = null; // 마지막 이용권은 null로
		passCount--; // 이용권 수도 줄이고
	
	}

	public void sysCorrPass(int scCorr, int scHour, String scName) {
	
		up[scCorr].setPassHour(scHour);
		up[scCorr].setPassPrice(scName);
	
	}

	

// ==============이용권 끝==============

	public void sysDevice(String sysB, String sysN ,int sysS) {//기기 추가 메서드
		int idx = 2;
		if (dList.length < nList.length) {
			nList[idx++] = new Device(sysB, sysN , sysS, sysS);
			System.out.println("기기가 추가 되었습니다.");
			return;
		} else if (dList.length <= nList.length ) {
			System.out.println("기기 등록 수가 초과되었습니다");
		}

		// 등록시 이용권.txt 추가
		Device deIo = new Device(sysB, sysN , sysS, sysS);
		
	}
	
	

	public void memDelete(String sysMemId) {
	      
	      int idx = 1;
	      for(int i = 0; i<memCount; i++) {
	         
	         if(mem[i].getId().equals(sysMemId)) {
	            mem[i] = null;
	             idx = i;
	             break;
	         }
	      }
	      //배열 밀기
	      if(idx != 0) {
	         for(int j = idx; j<memCount; j++) {
	            mem[j] = mem[j+1];
	         }
	         memCount--;
	      }
	      
	      
	      

	   }
		
		

	
		
		
	

}





