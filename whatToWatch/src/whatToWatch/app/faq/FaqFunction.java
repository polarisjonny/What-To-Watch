package faqfunction;

public class FaqFunction {
	
	FaqData data = new FaqData();
	
	int good;
	int bad;
	
	//�޴������ֱ�
	public void showFaqMenu() {
		System.out.println();
		System.out.println("1.�α����� �ȵſ�");
		System.out.println("2.���� ���� ������ ��� �ϳ���?");
		System.out.println("3.������ �����ϰ� �ͽ��ϴ�");
		System.out.println("4.�̺�Ʈ�� �����ߴµ� ������ ���ŵ��� �ʾƿ�");
		System.out.println("5.�̺�Ʈ�� ��÷�ƴµ� ��ǰ�� �����ֳ���?");
		System.out.println("6.������ �߰��� ��û�մϴ�/������ ���� ������ ��û�մϴ�");
		System.out.println("0.����");
		
		System.out.println();
		System.out.print("�Է� __ ");
	}
	
	//�޴����ùޱ�
	public String selectFaqMenu() {
		String input = Main.SC.nextLine();
		
		return input;
	}
	
	//���ÿ� ���� ��������
	public void showSolution(String input) {
		
		switch(input) {
		case "1" :
			System.out.println("���̵�, ��й�ȣ�� Ȯ���϶�� �޼����� �� ��� >>");
			System.out.println("���̵� �� ��й�ȣ ������ Ȯ���� �� �ؾ���� ��� ��й�ȣ�� �����ؾ� �մϴ�.\n");
			goodBad();
			break;
		case "2" :
			System.out.println("�̸���, �г���, ��й�ȣ �� ���������� �����ϰ� ���� ��� >>");
			System.out.println("ȸ������ ���� �������� �� �����Ϸ��� ���� ������ Ȯ�� �� �����ϸ� �˴ϴ�.\n");
			goodBad();
			break;
		case "3" :
			System.out.println("ȸ��Ż�� �ϰ� ���� ��� >>");
			System.out.println("ȸ������ ���� �������� �� �ϴܿ� �ִ� ȸ��Ż�� ���� �� ���� ������ �����ϸ� �˴ϴ�.\n");
			goodBad();
			break;
		case "4" :
			System.out.println("�̺�Ʈ ���� ������ �߻��� ��� >>");
			System.out.println("�ҹ����α׷��� ����Ͽ� �̺�Ʈ�� ������ ��� ������ ���ŵ��� �ʽ��ϴ�. �ش� ������ �߻��� ��� ������ 02-123-4567 �� �ڼ��� ���� �ٶ��ϴ�.\n");
			goodBad();
			break;
		case "5" :
			System.out.println("�̺�Ʈ ���� �� ��ǰ���޿� ���� ������ ��� >>");
			System.out.println("��ǰ ������ �̺�Ʈ ���� Ȯ�� �� �ָ�(������ ����)���� �ִ� ������ ���� �ҿ�˴ϴ�. ���� �Ⱓ�� ���� �� ��ǰ������ ���� �ʾ��� ��� ������ ���� �ٶ��ϴ�.\n");
			goodBad();
			break;
		case "6" :
			System.out.println("�������� �������� �� �߰��� ��û�� ��� >>");
			System.out.println("������ �߰�/���� ������ ���� ��û�� ���ǰԽ����� ���������ù��� ī�װ��� �̿����ֽø� �˴ϴ�.\n");
			goodBad();
			break;
		default : System.out.println("���� ��ȣ�� �ٽ� Ȯ���� �ּ���.");
		}
	}
	
	public void goodBad() {
		
		System.out.println("�亯�� ������ �Ǿ�����?");
		System.out.println("1.����� 2.����ȵ�");
	
		String gbInput = Main.SC.nextLine();
		
		switch(gbInput) {
		case "1" :
			good++;
			System.out.println("�����( " + good + " )/" + "����ȵ�( " + bad+ " )");
			break;
		case "2" :
			bad++;
			System.out.println("�����( " + good + " )/" + "����ȵ�( " + bad + " )");
			break;
		}
			
	}

}
