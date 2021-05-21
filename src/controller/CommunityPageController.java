package controller;

import java.sql.SQLException;
import java.util.Scanner;

import service.CommunityService;

public class CommunityPageController {
	// 게시판
	
	CommunityService communityService = new CommunityService();
	String userInfo = null;
	Scanner scan = new Scanner(System.in);

	public CommunityPageController(String userInfo) {
		this.userInfo = userInfo;
	}
	
	
	public boolean showCommunityPage() {
		System.out.println("-------------------- 메인 페이지 --------------------");
		System.out.println();
		
		System.out.println("1: 목록 조회");
		System.out.println("2: 게시글 조회");
		System.out.println("3: 글 쓰기");
		System.out.println("4: 내가 쓴 글");
		System.out.println("5: 게시글 삭제");
		System.out.println("6: 로그아웃");
	
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.print("입력: ");
		int input = scan.nextInt();
		
		System.out.println("---------------------------------------------------");
		System.out.println();
		
		if(input ==1) {
			communityService.readAllArticle();
			return true;
		}else if(input==2) {
			
			System.out.println("읽고자하는 게시물의 번호를 입력해주세요");
			System.out.print("입력: ");
			int articleNumber = scan.nextInt();
			
			communityService.readOneArticle(articleNumber);
			return true;
		}else if(input==3) {
			int cnt =1;
			String title;
			String contents;
			System.out.println("제목을 입력하세요");
			scan.nextLine();
			title = scan.nextLine();
			System.out.println("내용을 입력하세요");
			contents = scan.nextLine();
			
			try {
				communityService.writeArticle(cnt, userInfo, title, contents);
			} catch (SQLException e) {
				while(true) {
					cnt++;
					if(cnt ==100) {
						System.out.println("ID가 100번까지 찼습니다.");
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
			System.out.println("삭제할 게시물의 ID를 입력하세요");
			System.out.print("입력: ");
			String articleId4Delete = scan.next();
			
			communityService.deleteArticleById(userInfo, articleId4Delete);
			return true;
		}else if(input==6) {
			System.out.println("Sign Out");
			return false;
		}else {
			System.out.println("잘못된 입력입니다.");
			return true;
		}
	}
}
