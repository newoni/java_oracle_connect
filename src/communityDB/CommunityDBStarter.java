package communityDB;

import controller.BaseController;
import controller.CommunityPageController;

//

public class CommunityDBStarter {

	static boolean signIn = false;
	// --check. <21.05.20> map 타입으로 바꾸고 아래 if는 null 일 경우로 바꿀 것 
	public static void main(String[] args) {
		
		
		BaseController controller = new BaseController();
		while(true) { // 서버는 계속 실행
			
			// 로그인이 안 되었을 때
			if(signIn==false) { 
				signIn = controller.showIndexPage();
			}else { //로그인 되었을 때
				System.out.println("Logged IN 상태입니다");
				System.out.println();
				CommunityPageController comController = new CommunityPageController(controller.userInfo.get("ID"));
				signIn = comController.showCommunityPage();
			}
			continue;
		
		}

	}

}
