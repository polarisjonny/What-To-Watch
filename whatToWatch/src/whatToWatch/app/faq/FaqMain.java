package faq;

public class FaqMain {
	PlayStart ps = new PlayStart();

	public static void main(String[] args) {
		
		//FAQ�Խ���
		while(true) {
			boolean isFinish = ps.faqPlayStart();
			if(isFinish) {break;}
		}
		System.out.println("����� �����մϴ�. �̿��� �ּż� �����մϴ�.");

	}
}
