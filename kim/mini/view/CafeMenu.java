package kim.mini.view;
//mvc 패턴중 view 패키지 : 사용자가 보게될 화면을 담당하는 부분 , 사용자가 보게되면 (print구문)은 반드시 view 패키지 내에서만 작성.

import java.text.SimpleDateFormat;
import java.util.Date;

//scanner 사용도 view 패키지 안에서만 작성

import java.util.Scanner;

import kim.mini.controller.CafeManager;
import kim.mini.model.vo.Device;
import kim.mini.model.vo.Member;

public class CafeMenu {
	// 필드부
	private Scanner sc = new Scanner(System.in); // Scanner객체 생성
	private CafeManager cm = new CafeManager(); // CafeManager클래스 접근하기 위해 객체 생성 (cm)

	private final int SIZE = 10;
	private int[] rooms = new int[SIZE]; // 메소드를 나가게되면 초기화되서 전역변수로 사용

	public CafeMenu() {
	}

	// ==== 메인메뉴 ====
	// 1.회원가입
	// 2.로그인
	// 3.운영사항
	// 4.관리자
	// 0.프로그램종료
	public void mainMenu() {

		// 메인메뉴 반복출력
		while (true) {
			System.out.println("====신이주신 스터디양~====");
			System.out.println("1. 회원가입"); // signUp() 실행
			System.out.println("2. 로그인"); // login() 실행
			System.out.println("3. 운영사항"); // notice() 실행
			System.out.println("4. 관리자"); // manager() 실행
			System.out.println("0. 프로그램 종료"); // "프로그램을 종료합니다" 출력 후 리턴
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				signUp();
				break;
			case 2:
				login();
				break;
			case 3:
				notice();
				break;
			case 4:
				manager();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 누르셨습니다 다시 입력해주세요");
				continue; // while문은 이어서 실행

			}
		}
	}// mainMenu()

	// case 3 : 운영사항
	public void notice() {
		while (true) {
			System.out.println("====무엇이 궁금하십니까 ?====");

			System.out.println("1.공지사항");// 1번 실행
			System.out.println("2.마일리지 등급 혜택 안내");// 2번 실행
			System.out.println("3.운영시간 안내");// 3번 실행
			System.out.println("4.방역 수칙 안내");// 4번 실행
			System.out.println("5.시간당 요금제 안내");// 5번 실행
			System.out.println("0.이전 메뉴로");// 0번 실행
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				System.out.println("1. 19세이상의 성인고객은 백신 접종 완료증명서 또는 PCR음성 확인서(48시간 이내)를 제출해야 합니다.");
				System.out.println("2. 다른 고객님들께 방해가 되지 않게 실내에서 정숙 부탁드립니다.");
				break;
			case 2:
				System.out.println("저희 신이주신 스터디양~은 회원제로 운영을 하며, 결제한 금액의 10프로를 적립해 드립니다.");
				break;
			case 3:
				System.out.println(
						"\t저희는 연중무휴으로 운영중입니다. ^ㅅ^ \n**하지만 앞으로의 상황에 따라 24시간 운영을 못 할수도 있는 점 양해 부탁드립니다.**\n\t항상 이용해 주셔서 감사합니다 ^ㅅ^");
				break;
			case 4:
				System.out.println("1. 스터디 카페 내에서 항상 마스크 착용 부탁드립니다.");
				System.out.println("2. 코로나19 예방을 위해 당분간 자정부터 1시까지 방역/소독을 진행합니다.\n\t양해 부탁드립니다.");
				break;
			case 5:
				System.out.println("1시간 금액 : 1000원입니다.\n4시간 금액 : 2000원입니다.\n6시간 금액 : 3000원입니다.");
				break;
			case 0:
				System.out.println("이전으로 돌아갑니다.");
				return;
			default:
				System.out.println("잘 못 누르셨습니다 다시 입력해 주세요.");
				continue;// while문으로 이어서 실행

			}

		}

	}// notice() 종료

	public void manager() {// 관리자 로그인 메서드

		System.out.println("아이디 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호 : ");
		String pwd = sc.nextLine();
		if (id.equals("sys")) {
			if (pwd.equals("1234")) {
				System.out.println("로그인 성공!");
				managerMenu();

			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
		} else {
			System.out.println(id + "는 관리자가 아닙니다.");
		}

	}

	// case 4 : 관리자
	public void managerMenu() {

		while (true) {

			System.out.println("====관리자 모드====");

			System.out.println("1.회원 조희");
			System.out.println("2.회원 삭제");
			System.out.println("3.기기 등록");
			System.out.println("4.이용권 관리");
			System.out.println("0.프로그램 종료");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {

			case 1:// 1. 회원 조회
				cm.fileRead();
				break;
			case 2:// 2. 회원 삭제
				memDelete();
				break;
			case 3:// 3. 기기 등록
				sysDevice();
				break;
			case 4:
				sysMenu();
				break;
			case 0:
				System.out.println("프로그램 종료합니다.");
				return;

			default:
				System.out.println("잘 못 누르셨습니다 다시 입력해 주세요.");
				continue;// while문으로 이어서 실행

			}
		}
	}

	private void memDelete() {
		System.out.println("삭제할 회원 아이디 :");
		String sysMemId = sc.nextLine();

		Member m1 = cm.checkId(sysMemId);
		if (m1 == null) {
			System.out.println("회원 아이디가 없습니다.");
		} else {
			cm.memDelete(sysMemId);
			System.out.println("삭제가 완료 되었습니다.");
			cm.fileSave();
			cm.fileRead();
		}

	}

	// 기기추가
	private void sysDevice() {
		cm.checkDevice();

		System.out.println("추가할 기기 브랜드 : ");
		String sysB = sc.nextLine();
		sc.nextLine();

		System.out.println("추가할 기기명 : ");
		String sysN = sc.nextLine();

		System.out.println("추가할 기기 크기");
		int sysS = sc.nextInt();

		System.out.println("기기가 새로 등록되었습니다.");
		cm.sysDevice(sysB, sysN, sysS);

		cm.checkDevice();

		return;
	}

	// case 1 : 회원가입
	private void signUp() {
		// 1. 최대 회원 수 10명보다 더 입력할 수 없으므로 -> 회원가입수 > 10 (X)
		if (cm.getMemCount() > cm.NUM) {
			return;
		} else {
			// 2. 회원 수 남는 자리가 있다면 아이디, 비밀번호, 이름, 나이, 성별, 전화번호를 순서대로 키보드로 입력
			System.out.println("***** 회원가입 *****");
			System.out.print("아이디 : ");
			String id = sc.nextLine();

			// 3. 다른 유저 아이디와 중복되면 안되므로 아이디 checkId에서 돌려돌려 중복 유무 확인
			Member m = cm.checkId(id); // m or null이 들어가 있는 상태

			if (m != null) {
				System.out.println("동일한 아이디가 존재합니다.");
			} else { // 동일 아이디 X 나머지 정보 입력해서 회원가입 고
				System.out.print("비밀번호 : ");
				String pwd = sc.nextLine();
				System.out.print("이름 : ");
				String name = sc.nextLine();
				System.out.print("나이 : ");
				int age = sc.nextInt();
				sc.nextLine();
				System.out.print("성별 : ");
				char gender = sc.nextLine().charAt(0);
				System.out.print("전화번호 : ");
				String phoneNum = sc.nextLine();

				// 입력된 정보 담기
				Member m1 = new Member(id, pwd, name, age, gender, phoneNum);

				// Cafemanager signUp()에 담기
				cm.signUp(m1);

				System.out.println(name + "님의 회원가입이 완료되었습니다!");
				// IO입출력 입력된 정보 member.txt에 저장!!!!!
				cm.fileSave();

			}

		}

	}// signUp()종료

	// case 2 : 로그인
	private void login() {
		// 1. 아이디, 비밀번호 로그인
		System.out.println("아이디 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호 : ");
		String pwd = sc.nextLine();

		// 2. 입력받은 id와 mem id가 일치하는지 결과 담을 변수
		Member mId = cm.checkId(id);
		Member mPwd = cm.checkPwd(pwd);

		if (mId != null) {
			if (mPwd != null) {
				System.out.println("로그인 성공!");
				cm.getMIdPass(mId); // 로그인 성공 받기위해 이것만 추가
				seat(); // ----->로그인 성공시 서브메뉴 출력
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
		} else {
			System.out.println(id + "는 등록되어 있지 않은 회원입니다.");
		}

	}// login()

	// case 2의 로그인 성공시 ***서브메뉴*** 출력
	private void seat() {

		while (true) {
			System.out.println("===신이주신 스터디양~===");// 서브메뉴
			System.out.println("1. 입퇴실");
			System.out.println("2. 이용권 구매");
			System.out.println("3. 대여물품 확인");
			System.out.println("4. 마이페이지");
			System.out.println("0. 로그아웃");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				inOut();
				break;
			case 2:
				passMenu();
				break;
			case 3:
				rentDevice();
				break;
			case 4:
				myPage();
				break;
			case 0:
				System.out.println("로그아웃 되었습니다.");
				return;
			default:
				System.out.println("잘못 누르셨습니다 다시 입력해주세요");
				continue; // seat()(서브메뉴) while문은 이어서 실행

			}

		}

	}// seat()

	// 입실,퇴실,자리현황 메서드
	public void inOut() {
		
		Member mem = cm.loginUser();//마일리지 적립을 위해 선언
		double result = 0; //마일리지 담을 값
		double sum;//마일리지 쌓이는 값

		if (mem.getUsePass() != 0) {//이용권이 없으면 접근불가

			while (true) {

				System.out.println("1.입실 2.퇴실 3.자리조회 4.이전메뉴로  :");
				int menu = sc.nextInt();//메뉴선택
				int seat;//좌석 변수

				switch (menu) {

				case 1:
					System.out.print("사용하실 좌석 입력 :");
					seat = sc.nextInt();

					if (seat > SIZE || seat < 1) {//좌석이 10이기 때문에 10보다크거나 좌석이 1보다 작으면 if문타게만듬
						System.out.println("선택가능 좌석을 벗어낫습니다!");
						break;
					} else if (rooms[seat - 1] == 1) {//밑에 rooms[입력한 값-1]이 1과 같으면 사용중인 if문
						System.out.println("사용중인 좌석입니다!");
						break;
					}

					System.out.println(seat + "번 좌석에 입실하시면 됩니다!");
					rooms[seat - 1] = 1;//입실하면 rooms[입력한 값-1] = 1;이라고 줌
					break;

				case 2:

					System.out.print("퇴실 할 좌석 입력 :");
					seat = sc.nextInt();
					try {
						if (rooms[seat - 1] == 1) {//입실을 한 if문
							rooms[seat - 1] = -1;// 퇴실하면 rooms[-1]=-1값을 줌
							Date today = new Date();
							System.out.println(today);//퇴실한 현재시간 표현
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/aa hh시 mm분 E요일 ");
							System.out.println(sdf.format(today));
							System.out.println(seat + "번 좌석에서 퇴실처리 되었습니다!");

							result += 0.5;//마일리지 적립

							sum = mem.getMileage();
							mem.setMileage(result);
							System.out.println(result + " 마일리지가 적립 되었습니다.");
							cm.fileSave();//파일에 세이브
							

						} else {
							System.out.println(seat + "번 좌석에 아무도 없습니다.");
						}

					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("좌석이 존재하지 않습니다.");
					}

					break;

				case 3://좌석현황
					for (int i = 0; i < rooms.length; i++) {
						if (rooms[i] == 1) {//위에서 입실을 했으면 ==1로 해서 사용중이면 
							System.out.println(i + 1 + "번 좌석은 사용중입니다.");
						} else {
							System.out.println(i + 1 + "번 좌석은 비었습니다.");
						}
					}
					break;
				case 4:
					System.out.println("이전메뉴로 돌아갑니다.");
					return;

				default:
					System.out.println("잘못입력하셨습니다. 다시 입력해 주세요.");
					break;

				}

			}

		} else
			System.out.println("이용권을 먼저 구매해주세요");

	}

	// 대여 물품 확인 메서드
	public void rentDevice() {
		cm.deviceSave(); // 임시로 device 정보 담을 메서드 호출
		cm.readDevice(); // device 조회

		while (true) {
			System.out.println("=== 대여 물품 확인 ===");
			System.out.println("1. 노트북/태블릿 대여");
			System.out.println("2. 노트북/태블릿 반납");
			System.out.println("0. 이전 메뉴로");
			System.out.println("메뉴 선택: ");

			int menu = sc.nextInt();

			if (menu == 1) {
				// 기기 조회
				cm.checkDevice();

				// 기기 번호 입력
				System.out.println("대여할 기기 번호: ");
				int deviceN = sc.nextInt();

				// 기기대여
				cm.rentD(deviceN);

			} else if (menu == 2) {
				System.out.println("반납할 기기 번호: ");
				int deviceN = sc.nextInt();

				// 기기 반납
				cm.returnD(deviceN);

				System.out.println("반납이 완료되었습니다");
			} else if (menu == 0) {
				System.out.println("이전 메뉴");
				return;
			}

		}

	}// reantDevice()

	// 서브메뉴case 3 : 마이페이지
	private void myPage() {

		String m = "";

		System.out.println("비밀번호를 입력하세요");
		String pwd = sc.nextLine();

		Member mem = cm.loginUser();
		// Member mem = cm.checkPwd(pwd);

		if (mem.getPwd().equals(pwd)) {

			System.out.println("***** 마이페이지 *****");
			System.out.println("1. 내 정보 조회");
			System.out.println("2. 내 정보 수정");
			System.out.println("메뉴 선택 : ");
			int menu1 = sc.nextInt();

			if (menu1 == 1) {

				System.out.println("***** 내 정보^ㅇ^ *****");
				m = cm.getMember(pwd);
				System.out.println(mem.memInfo());

			} else {

				System.out.println("***** 수정할 항목 *****");
				System.out.println("1. 이름");
				System.out.println("2. 비밀번호");
				System.out.println("3. 전화번호");
				System.out.println("0. 이전 메뉴로");
				System.out.println("변경할 항목을 선택하세요 : ");
				int menu2 = sc.nextInt();
				sc.nextLine();

				if (menu2 == 0) {

					return;

				} else {

					System.out.print("기존 정보 : ");
					System.out.println(mem.memInfo());
					System.out.println("변경할 내용 : ");
					String update = sc.nextLine();
					cm.updateMem(mem, menu2, update);
					System.out.println("회원 정보가 변경되었습니다.");
					System.out.println(mem.memInfo());
				}
			}
		} else {
			System.out.println("비밀번호가 틀립니다.");
		}

	}// myPage()끝

	// ==============이용권 시작==============
	private void passMenu() { // 이용권메뉴
		while (true) {
			System.out.println("1.이용권조회");
			System.out.println("2.이용권선택");
			System.out.println("0. 이전 메뉴로");
			System.out.print("> ");

			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				searchPass();// 이용권조회
				break;
			case 2:
				selectPass();// 로그인된 id로 이용권 선택
				break;
			case 0: // 종료
				return;
			}

		}

	}

	// 이용권 전체 출력
	private void searchPass() {
		System.out.println("====== 이용권 ======");
		cm.searchPass();
		System.out.println("==================");
	}

	// id로 이용권 선택
	private void selectPass() {

		searchPass(); // 전체 이용권 출력
		while (true) {

			System.out.println("이용권을 선택해주세요 : ");
			int scPass = sc.nextInt();

			boolean tf = cm.checkSc(scPass);
			if (tf != true) {
				continue;
			}

			System.out.println("==================");
			System.out.println(cm.selectPass(scPass));
			System.out.println("==================");
			return;
		}
	}

	private void sysMenu() {

		while (true) {
			System.out.println("1.이용권조회");
			System.out.println("2.이용권등록");
			System.out.println("3.이용권수정");
			System.out.println("4.이용권삭제");
			System.out.println("0. 이전 메뉴로");
			System.out.print("> ");

			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				searchPass(); // 1.전체조회
				break;
			case 2:
				sysWritePass(); // 2.이용권입력
				break;
			case 3:
				sysCorrPass(); // 3.이용권수정
				break;
			case 4:
				sysDeletePass(); // 4.이용권삭제
				break;
			case 0: // 종료
				return;
			}

		}

	}

	private void sysWritePass() { // 이용권추가
		searchPass();

		System.out.println("추가할 이용권 시간(int) : ");
		int scHour = sc.nextInt();
		sc.nextLine();

		System.out.println("이용권 가격(String) : ");
		String scName = sc.nextLine();

		cm.sysWriterPass(scHour, scName);
		System.out.println("이용권이 추가되었습니다");
		

		searchPass();

		return;
	}

	private void sysDeletePass() { // 이용권삭제
		searchPass();

		System.out.println("삭제할 이용권 번호 : ");
		int scDel = sc.nextInt();
		sc.nextLine();

		cm.sysDeletePass(scDel);

		searchPass();

	}

	private void sysCorrPass() { // 이용권수정
		searchPass();

		System.out.println("수정할 이용권 번호 : ");
		int scCorr = sc.nextInt();
		sc.nextLine();

		System.out.println("수정할 이용권 시간(int) : ");
		int scHour = sc.nextInt();
		sc.nextLine();

		System.out.println("이용권 가격(String) : ");
		String scName = sc.nextLine();

		System.out.println("이용권이 추가되었습니다");

		cm.sysCorrPass(scCorr, scHour, scName);

		searchPass();
	}

	// ==============이용권 끝==============
}