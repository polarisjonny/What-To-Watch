package faq;

public class FaqStart {
	
	import faq.Faq;
	import qa.QandA;

	public class PlayStart {
		Faq faq = new Faq();
		QandA qa = new QandA();
		
	//FAQ�Խ��� ����
	public boolean faqPlayStart() {
		//FAQ �޴������ֱ�
		faq.showFaqMenu();
		
		//�޴� ����
		String input = faq.selectFaqMenu();
		if(input.equals("0")) {
		return true;	
		}
		
		//���ÿ� ���� ��������
		faq.showSolution(input);
		return false;
	}

}
