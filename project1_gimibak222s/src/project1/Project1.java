package project1;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import coninfo.conInfo_vo.ConInfoVO;
import coninfo.dao.ConInfoDAO;
import faq.Faq;
import hall_info.Hall_infoDAO;
import hall_info.Hall_infoVO;
import reservation.Reservation;
import reservation.ReservationDAO;
import reservation.ReservationVO;
import seat_info.Seat_infoDAO;
import seat_info.Seat_infoVO;
import users.MyPage;
import users.PageSignup;
import users.UsersDAO;
import users.UsersVO;


public class Project1 {
	//필드-------------------------------------------------------------
	private Scanner scan = new Scanner(System.in); //스캐너 선언
	private static final String CONCERT_INFO = "CONCERT";
	private static final String RESERVATION = "예약하기";
	private static final String MY = "마이페이지";
	private static final String QUESTION = "문의사항";
	private static final String LOGIN = "로그인";
	private static final String SIGNUP = "회원가입";
	private static final String MANAGER = "관리자모드";
	
	private static final String MANAGERPW = "project1"; //관리자모드 패스워드

	
	//생성자------------------------------------------------------------
	
	//메소드------------------------------------------------------------
	public void startProject1() {
		int select; //메인 메뉴에서 입력한 번호 값
		LocalDate nowDate = LocalDate.now(); //현재 날짜, 시간 입력할 때 사용할 변수

		ReservationDAO reservationDAO = new ReservationDAO(); //reservation 테이블 용 메소드
    	ConInfoDAO conInfoDAO = new ConInfoDAO(); //concert_info 테이블 용 메소드
    	UsersDAO usersDAO = new UsersDAO(); //users 테이블 용 메소드
    	Hall_infoDAO hallInfoDAO = new Hall_infoDAO(); //users 테이블 용 메소드
    	Seat_infoDAO seatInfoDAO = new Seat_infoDAO(); //users 테이블 용 메소드
    	
    	List<ReservationVO> rlist = reservationDAO.selectAll(); //reservation 테이블 데이터 전체 조회
    	List<ConInfoVO> clist = conInfoDAO.selectAll(); //concert_info 테이블 데이터 전체 조회
    	List<UsersVO> ulist = usersDAO.selectAll(); //users 테이블 데이터 전체 조회
    	List<Hall_infoVO> hlist = hallInfoDAO.selectAll(); //hall_info 테이블 데이터 전체 조회
    	List<Seat_infoVO> slist = seatInfoDAO.selectAll(); //seat_info 테이블 데이터 전체 조회
		
		//마이페이지 사용 변수---------------------------------------------------------
		
		String useridscanner;
		
		int intscanner; //숫자 스캐너
		String sscanner; //문자열 스캐너
		//-----------------------------------------------------------------------

		

		while (true) {
			System.out.println("----------------------------GIMIBAK----------------------------");
			System.out.println("1." + CONCERT_INFO + " 2." + RESERVATION + " 3." + MY + " 4." + 
					QUESTION + " 5." + LOGIN + " 6." + SIGNUP + " 7." + MANAGER + " 0.종료");
			System.out.println("---------------------------------------------------------------");
			System.out.println("원하는 서비스의 번호를 입력하시오.");
			System.out.print("번호 입력 : ");
			select = scan.nextInt();
			scan.nextLine();
			System.out.println();
			System.out.println("---------------------------------------------------------------");
			
			//콘서트---------------------------------------------------------------------------------
			// 콘서트 정보 조회
			if (select == 1) {
				while (true) {
				    // 메뉴 출력 & 사용자 선택
				    System.out.println("1. 콘서트 정보 조회");
				    System.out.println("0. 이전 메뉴로 돌아가기");
				    System.out.print("번호 입력 : ");
				    int choice1 = scan.nextInt();
				    scan.nextLine();

				    if (choice1 == 1) {
				        // 콘서트 정보 출력
				        System.out.println("<콘서트 INFORMATION>");
				        System.out.println("번호\t콘서트명\t\t\t\t장르\t러닝타임\t날짜\t\t\t장소\t\t시간");
						for (ConInfoVO vo : clist) {
						    System.out.println(String.format("%-1s\t%-24s\t%-5s\t%-5s\t%-10s\t%-20s\t%-10s",
				                    vo.getConcert_id(), vo.getTitle(), vo.getGenre(),
				                    vo.getRunning_time(), vo.getConcert_date().substring(0, 10), vo.getLocation(),
				                    vo.getTime()));
				        }
				        System.out.println("---------------------------------------------------------------");
				    } else if (choice1 == 0) {
				        // 이전 메뉴로 돌아가기
				        break;
				    } else {
				        System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
				    }
				
			    }
			}
			
			//예약하기---------------------------------------------------------------------------------
			if (select == 2) { 
				Reservation booking = new Reservation ();
				booking.booking();
			}
			
			//마이페이지---------------------------------------------------------------------------------
			if (select == 3) { 
				MyPage mp = new MyPage();
				mp.mypage();
			}
	
			//문의사항---------------------------------------------------------------------------------
			if (select == 4) { 
				Faq faq = new Faq ();
				faq.faqStart();
				
			}
			
			//로그인---------------------------------------------------------------------------------
			if (select == 5) { 
				PageSignup ps1 = new PageSignup();
				ps1.Login();
			}
			//회원가입------------------------------------------------------------
			if (select == 6) {
				PageSignup ps2 = new PageSignup();
				ps2.Signup();
			}
			
			// 관리자모드---------------------------------------------------------------------------------
			// 7번 관리자모드에서 콘서트 정보 수정 기능 추가
			if (select == 7) {
				System.out.println("관리자 비밀번호를 입력하세요.");

				while (true) {
					// 1. 관리자 비밀번호 입력
					System.out.print("비밀번호 : ");
					sscanner = scan.nextLine(); //비밀번호 맞으면 다음으로 넘어가게 하기
					System.out.println();
					System.out.println("---------------------------------------------------------------");
					
					System.out.println("관리자 모드로 변경되었습니다.");
					System.out.println("---------------------------------------------------------------");
					
					if (MANAGERPW.equalsIgnoreCase(sscanner)) {
						while (true) {
							// 2. 원하는 메뉴 선택하기
							
							System.out.println("<관리자 모드>");
							System.out.println("1.고객정보 조회 2.고객 결제정보 조회 3.고객 예매정보 조회 4.콘서트 정보 수정 5.관리자모드 종료");
							System.out.print("번호 입력 : ");
							select = scan.nextInt();
							scan.nextLine();
							System.out.println("---------------------------------------------------------------");
							
							//2-1. 고객정보 조회 (users select all)
							if (select == 1) {
								
							}
							
							//2-2. 고객 결제정보 조회 (payment select all)
							if (select == 2) {
								
							}
		
						
							//2-3. 고객 예매 정보 조회 (reservation select all)
							if (select == 3) {
								System.out.println("<고객 예매정보>");
		
								System.out.println("예약ID\t사용자ID\t콘서트ID\t홀ID\t좌석ID\t인원수\t좌석번호\t\t예약날짜\t\t상태");
								for (ReservationVO vo : rlist) {
									 System.out.println(String.format("%-1s\t%-7s\t%-5s\t%-5s\t%-5s\t%-5s\t%-10s\t%-10s\t%-20s",
						                      vo.getBook_id(), vo.getUser_id(), vo.getConcert_id(),
						                      vo.getHall_id(), vo.getSeat_id(), vo.getCount(),
						                      vo.getSeat(), vo.getCreateDate(), 
						                      vo.getStatus()));
								}
								
								System.out.println();
								System.out.println("---------------------------------------------------------------");
		
							}
							
							//2-4. 콘서트 정보 수정 (concert_info insert, update, delete)
							if (select == 4) {
							    System.out.println("1. 콘서트 정보 수정");
							    System.out.println("2. 콘서트 정보 삭제");
							    System.out.println("3. 이전 메뉴로 돌아가기");
							    System.out.print("번호 입력 : ");
							    int adminChoice = scan.nextInt();
							    scan.nextLine(); // 개행 문자 처리
							    
							    
							    if (adminChoice == 1) {
							        // 콘서트 정보 수정
							        System.out.print("수정할 콘서트의 번호 입력: ");
							        int concertNumber = scan.nextInt();
							        scan.nextLine(); // 개행 문자 처리
							        
							        ConInfoVO selectedConcert = conInfoDAO.selectOne(Integer.toString(concertNumber));
							        if (selectedConcert != null) {
							            System.out.println("수정할 필드를 선택하세요.");
							            System.out.println("1. 제목 2. 장르 3. 러닝타임 4. 날짜 5. 장소 6. 시간");
							            System.out.print("번호 입력: ");
							            int fieldChoice = scan.nextInt();
							            scan.nextLine(); // 개행 문자 처리
							            
							            switch (fieldChoice) {
							                case 1:
							                    System.out.print("새로운 제목 입력: ");
							                    String newTitle = scan.nextLine();
							                    selectedConcert.setTitle(newTitle);
							                    break;
							                case 2:
							                    System.out.print("새로운 장르 입력: ");
							                    String newGenre = scan.nextLine();
							                    selectedConcert.setGenre(newGenre);
							                    break;
							                case 3:
							                    System.out.print("새로운 러닝타임 입력: ");
							                    int newRunningTime = scan.nextInt();
							                    selectedConcert.setRunning_time(newRunningTime);
							                    break;
							                case 4:
							                    System.out.print("새로운 날짜 입력 (YYYY-MM-DD): ");
							                    String newConcertDate = scan.nextLine();
							                    selectedConcert.setConcert_date(newConcertDate);
							                    break;
							                case 5:
							                    System.out.print("새로운 장소 입력: ");
							                    String newLocation = scan.nextLine();
							                    selectedConcert.setLocation(newLocation);
							                    break;
							                case 6:
							                    System.out.print("새로운 시간 입력: ");
							                    String newTime = scan.nextLine();
							                    selectedConcert.setTime(newTime);
							                    break;
							                default:
							                    System.out.println("잘못된 선택입니다.");
							            }
							            
							            
							            // 수정된 콘서트 정보를 데이터베이스에 업데이트
							            int updateResult = conInfoDAO.update(selectedConcert);
							            if (updateResult > 0) {
							                System.out.println("콘서트 정보가 수정되었습니다.");
							            } else {
							                System.out.println("콘서트 정보 수정에 실패하였습니다.");
							            }
							        } else {
							            System.out.println("해당 번호의 콘서트 정보가 없습니다.");
							        }
							    }
							    
							    if (adminChoice == 2) {
							        System.out.print("삭제할 콘서트의 번호 입력: ");
							        int concertNumber = scan.nextInt();
							        scan.nextLine(); // 개행 문자 처리
							        
							        ConInfoVO selectedConcert = conInfoDAO.selectOne(Integer.toString(concertNumber));
							        if (selectedConcert != null) {
							            // 콘서트 정보를 데이터베이스에서 삭제
							            int deleteResult = conInfoDAO.delete(selectedConcert);
							            if (deleteResult > 0) {
							                System.out.println("콘서트 정보가 삭제되었습니다.");
							            } else {
							                System.out.println("콘서트 정보 삭제에 실패하였습니다.");
							            }
							        } else {
							            System.out.println("해당 번호의 콘서트 정보가 없습니다.");
							        }

							        // "콘서트 정보 삭제" 작업이 완료되었으므로 select 값을 변경
							        select = 0; // 0은 종료를 나타내는 값으로 설정
							    }
							
							    
							    if (select == 5) {
									System.out.println("관리자 모드가 종료되었습니다.");
									System.out.println("---------------------------------------------------------------");
									System.out.println();
									System.out.println();
									break;
								} 
							    
							
							// 7번 관리자모드에서 콘서트 정보 삭제 기능 추가
						    if (select == 5) {
						        System.out.println("1. 콘서트 정보 수정");
						        System.out.println("2. 콘서트 정보 삭제");
						        System.out.print("번호 입력 : ");
						        int adminChoice2 = scan.nextInt();
						        scan.nextLine(); // 개행 문자 처리
						        
						        if (select == 2) {
						            System.out.print("삭제할 콘서트의 번호 입력: ");
						            int concertNumber = scan.nextInt();
						            scan.nextLine(); // 개행 문자 처리
						            
						            ConInfoVO selectedConcert = conInfoDAO.selectOne(Integer.toString(concertNumber));
						            if (selectedConcert != null) {
						                // 콘서트 정보를 데이터베이스에서 삭제
						                int deleteResult = conInfoDAO.delete(selectedConcert);
						                System.out.println("콘서트 정보가 삭제되었습니다.");
						            } else {
						            	System.out.println("콘서트 정보 삭제에 실패하였습니다.");
						            }
						         } else {
						                System.out.println("해당 번호의 콘서트 정보가 없습니다.");
						          
						        }
							  }
							}
						}
						 break; // 비밀번호가 맞으면 첫 번째 while 루프도 종료
			        } else {
			            // 올바르지 않은 비밀번호를 입력한 경우
			            System.out.println("비밀번호가 올바르지 않습니다. 다시 입력하세요.");
			        }
				}
			}
			
			//종료---------------------------------------------------------------------------------
			if (select == 0) { 
				System.out.print("감사합니다. 안녕히가시오.");
				break;
			}
		}
	}	
	}


