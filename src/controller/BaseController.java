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
		// �α��� ���� boolean type return
		// �Է�: none
		// ���: boolean
	
		System.out.println("-------------------- ���� ������ --------------------");
		System.out.println();
		
		System.out.println("1: ȸ�� ����");
		System.out.println("2: �α���");
		System.out.println("3: ���̵� ã��");
		System.out.println("4: ��й�ȣ ã��");
	
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.print("�Է�: ");
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
			System.out.println("�߸��� �Է��Դϴ�");
			return false;
		}
		
	}

	public boolean signUpUser() {
		// ����� ȸ������ controller
		// input : none
		// output: boolean type, sign up success or not
		System.out.print("���̵� �Է�: ");
		String id = scan.next();
		System.out.print("��й�ȣ �Է�: ");
		String password = scan.next();
		System.out.print("�̸� �Է�: ");
		String name = scan.next();
		System.out.print("��ȭ��ȣ �Է�( \'-\' �����ؼ� �ۼ�): ");
		String phoneNumber = scan.next();
		System.out.print("�̸��� �Է�: ");
		String eMail = scan.next();
		
		System.out.println("���̵� �ߺ��˻� ����");
		boolean isDuplicated = logInService.ckCustomer(id);
		
		if(isDuplicated) {
			System.out.println("�ߺ� ���̵� �߰�");
			return false;
		}else {
			System.out.println("���̵� ��� ����");
		}		
		
		
		try{ // sign up�� �� �� ��
			customerService.create(cnt, id, password, name, phoneNumber, eMail);
			return true;
			
		}catch(Exception e) { //sign up �� �ȵ� �� id ���� �ø��鼭 �� ���� �õ�
			System.out.println("�̹� �����ϴ� user id ���̹Ƿ� �ٸ� ������ id ����");
			while(true) {
				try {
					cnt++;
					customerService.create(cnt, id, password, name, phoneNumber, eMail);
					cnt=0;
					return true;
				}catch(Exception e2){ // �ش� id�� ���� ���
					if(cnt == 1000) {//���� �� �̻� customer�� ���� ���
						return false;
					}
					continue;
				}
			}
		}
	}

	public HashMap<String,String> signInUser() {
		System.out.print("���̵� �Է�: ");
		String userId = scan.next();
		
		System.out.print("��й�ȣ �Է�: ");
		String userPassword = scan.next();
		
		userInfo =  logInService.signIn(userId, userPassword);
		return userInfo;
		
	}
	
	public void findNickName() {
		System.out.print("�̸� �Է�: ");
		String userName = scan.next();
		
		System.out.print("��ȭ��ȣ �Է�(- ����): ");
		String userPhone = scan.next();
		
		logInService.findUserIdByNameAndPhone(userName, userPhone);
	}
	
	public void findPassword() {
		System.out.println("���̵� �Է�: ");
		String userId = scan.next();
		
		System.out.print("�̸� �Է�: ");
		String userName = scan.next();
		
		System.out.print("��ȭ��ȣ �Է�(- ����): ");
		String userPhone = scan.next();
		
		logInService.findPasswordByUserIdAndNameAndPhone(userId, userName, userPhone);
	}
}
