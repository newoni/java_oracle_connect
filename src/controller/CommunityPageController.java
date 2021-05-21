package controller;

import java.sql.SQLException;
import java.util.Scanner;

import service.CommunityService;

public class CommunityPageController {
	// �Խ���
	
	CommunityService communityService = new CommunityService();
	String userInfo = null;
	Scanner scan = new Scanner(System.in);

	public CommunityPageController(String userInfo) {
		this.userInfo = userInfo;
	}
	
	
	public boolean showCommunityPage() {
		System.out.println("-------------------- ���� ������ --------------------");
		System.out.println();
		
		System.out.println("1: ��� ��ȸ");
		System.out.println("2: �Խñ� ��ȸ");
		System.out.println("3: �� ����");
		System.out.println("4: ���� �� ��");
		System.out.println("5: �Խñ� ����");
		System.out.println("6: �α׾ƿ�");
	
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.print("�Է�: ");
		int input = scan.nextInt();
		
		System.out.println("---------------------------------------------------");
		System.out.println();
		
		if(input ==1) {
			communityService.readAllArticle();
			return true;
		}else if(input==2) {
			
			System.out.println("�а����ϴ� �Խù��� ��ȣ�� �Է����ּ���");
			System.out.print("�Է�: ");
			int articleNumber = scan.nextInt();
			
			communityService.readOneArticle(articleNumber);
			return true;
		}else if(input==3) {
			int cnt =1;
			String title;
			String contents;
			System.out.println("������ �Է��ϼ���");
			scan.nextLine();
			title = scan.nextLine();
			System.out.println("������ �Է��ϼ���");
			contents = scan.nextLine();
			
			try {
				communityService.writeArticle(cnt, userInfo, title, contents);
			} catch (SQLException e) {
				while(true) {
					cnt++;
					if(cnt ==100) {
						System.out.println("ID�� 100������ á���ϴ�.");
						break;
					}
					try {
						communityService.writeArticle(cnt, userInfo, title, contents);
						break;
					} catch (SQLException e1) {
					}
				}
			}
			return true;
			
		}else if(input==4) {
			communityService.findArticleById(userInfo);
			return true;
		}else if(input==5) {
			System.out.println("������ �Խù��� ID�� �Է��ϼ���");
			System.out.print("�Է�: ");
			String articleId4Delete = scan.next();
			
			communityService.deleteArticleById(userInfo, articleId4Delete);
			return true;
		}else if(input==6) {
			System.out.println("Sign Out");
			return false;
		}else {
			System.out.println("�߸��� �Է��Դϴ�.");
			return true;
		}
	}
}
