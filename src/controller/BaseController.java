package controller;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Scanner;

import service.CustomerService;
import service.LogInService;

public class BaseController {
	Scanner scan = new Scanner(System.in);
	int cnt = 1; 
	boolean stop = false;
	public HashMap<String,String> userInfo = null;
	
	
	CustomerService customerService = new CustomerService();
	LogInService logInService = new LogInService();
	
	public boolean showIndexPage() { 
		// 로그인 여부 boolean type return
		// 입력: none
		// 출력: boolean
	
		System.out.println("-------------------- 메인 페이지 --------------------");
		System.out.println();
		
		System.out.println("1: 회원 가입");
		System.out.println("2: 로그인");
		System.out.println("3: 아이디 찾기");
		System.out.println("4: 비밀번호 찾기");
	
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.print("입력: ");
		int input = 0;
		try {
			input = scan.nextInt();
		}catch(Exception e){
			return false;
		}
		
		System.out.println("---------------------------------------------------");
		System.out.println();
		
		
		
		if(input == 1) {
			boolean signUpSuccess = signUpUser();
			return false;
		}else if(input ==2) {
			boolean rs;
			userInfo= signInUser();
			
			boolean isSuccess = false;
			if(userInfo!=null) {
				isSuccess = true;
			}
			
			System.out.println(userInfo.get("ID"));
			return isSuccess;
			
		}else if(input ==3) {
			findNickName();
			return false;
		}else if(input ==4) {
			findPassword();
			return false;
		}else {
			System.out.println("잘못된 입력입니다");
			return false;
		}
		
	}

	public boolean signUpUser() {
		// 사용자 회원가입 controller
		// input : none
		// output: boolean type, sign up success or not
		System.out.print("아이디 입력: ");
		String id = scan.next();
		System.out.print("비밀번호 입력: ");
		String password = scan.next();
		System.out.print("이름 입력: ");
		String name = scan.next();
		System.out.print("전화번호 입력( \'-\' 포함해서 작성): ");
		String phoneNumber = scan.next();
		System.out.print("이메일 입력: ");
		String eMail = scan.next();
		
		System.out.println("아이디 중복검사 실행");
		boolean isDuplicated = logInService.ckCustomer(id);
		
		if(isDuplicated) {
			System.out.println("중복 아이디 발견");
			return false;
		}else {
			System.out.println("아이디 사용 가능");
		}		
		
		
		try{ // sign up이 잘 될 때
			customerService.create(cnt, id, password, name, phoneNumber, eMail);
			return true;
			
		}catch(Exception e) { //sign up 이 안될 때 id 값을 올리면서 재 생성 시도
			System.out.println("이미 존재하는 user id 값이므로 다른 값으로 id 생성");
			while(true) {
				try {
					cnt++;
					customerService.create(cnt, id, password, name, phoneNumber, eMail);
					cnt=0;
					return true;
				}catch(Exception e2){ // 해당 id가 있을 경우
					if(cnt == 1000) {//일정 수 이상 customer가 있을 경우
						return false;
					}
					continue;
				}
			}
		}
	}

	public HashMap<String,String> signInUser() {
		System.out.print("아이디 입력: ");
		String userId = scan.next();
		
		System.out.print("비밀번호 입력: ");
		String userPassword = scan.next();
		
		userInfo =  logInService.signIn(userId, userPassword);
		return userInfo;
		
	}
	
	public void findNickName() {
		System.out.print("이름 입력: ");
		String userName = scan.next();
		
		System.out.print("전화번호 입력(- 포함): ");
		String userPhone = scan.next();
		
		logInService.findUserIdByNameAndPhone(userName, userPhone);
	}
	
	public void findPassword() {
		System.out.println("아이디 입력: ");
		String userId = scan.next();
		
		System.out.print("이름 입력: ");
		String userName = scan.next();
		
		System.out.print("전화번호 입력(- 포함): ");
		String userPhone = scan.next();
		
		logInService.findPasswordByUserIdAndNameAndPhone(userId, userName, userPhone);
	}
}
