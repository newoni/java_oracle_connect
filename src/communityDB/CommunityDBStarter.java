package communityDB;

import controller.BaseController;
import controller.CommunityPageController;

//

public class CommunityDBStarter {

	static boolean signIn = false;
	// --check. <21.05.20> map Ÿ������ �ٲٰ� �Ʒ� if�� null �� ���� �ٲ� �� 
	public static void main(String[] args) {
		
		
		BaseController controller = new BaseController();
		while(true) { // ������ ��� ����
			
			// �α����� �� �Ǿ��� ��
			if(signIn==false) { 
				signIn = controller.showIndexPage();
			}else { //�α��� �Ǿ��� ��
				System.out.println("Logged IN �����Դϴ�");
				System.out.println();
				CommunityPageController comController = new CommunityPageController(controller.userInfo.get("ID"));
				signIn = comController.showCommunityPage();
			}
			continue;
		
		}

	}

}
