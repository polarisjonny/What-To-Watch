package q_a;

public class QAstart {

	//�޴������ֱ�
	public void showQaMenu() {
		System.out.println();
		System.out.println("1.���ǻ��� �ۼ�");
		System.out.println("2.���ǰԽ��Ǹ��");
		System.out.println("3.���� ����");
		System.out.println("4.�亯 �ۼ�");
	}
	
	//�޴����ùޱ�
	public String selectQaMenu() {
		String input = Main.SC.nextLine();
		
		return input;
	}
	
	//���ÿ� ���� ������
	public void showQaList(String input) {
		Qfunction q = new Qfunction();
		Afunction a = new Afunction();
		
		switch(input) {
		case "1" : q.writeQ();
		case "2" :
		case "3" :
		case "4" :
		}
	}
}
