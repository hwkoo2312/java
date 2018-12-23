package Calc;

import javax.swing.*;			//AWT�� �������� �� ���� GUI�� �����ִ°� SWING �̴�. 
import java.awt.*;			    //Java���� �׷��� ���α׷��� �ϱ� ���� Ŭ���� ���̺귯���� �����ϴµ� �̰��� AWT�� 
import java.awt.event.*;		//Java���� �����ϴ� �̺�Ʈ�� ����ϱ� ���ؼ��� java.awt.event�� import�ؾ��ϴµ�, ���콺�� ��ư Ŭ�� or Ű����� �Է� �̺�Ʈ�� ���� import�ߴ�.

public class Calculator extends JFrame { 		//awt�� ����� Frame���� ������ SWING�� ����ϱ⿡ �տ� J�� �ٿ��� JFrame�� ���.

	private JLabel numberOut; // ���ڰ� ǥ�õ� ����
	private JButton buttons[]; // ��ư�� �迭�� ���� �̺�Ʈ ���� buttons[i]������ �����ؼ� switch������ �ذ��� ����.
	private JButton lButtons[]; // MC, MR, M+, M-, MS, M*�� �߰��ϱ� ���ؼ� �迭�� �ϳ� �� �������.
	private JPanel captainPanel, btnPanel, lbtnPanel; // ��ư�� ���̺��� ��ġ�� ����

	// GUI�� �����غ���.
	public Calculator() {

		captainPanel = new JPanel(); //���� ū ������ ����� ���ؼ� ����.

		numberOut = new JLabel("0", JLabel.RIGHT); // ������ �ʱ� ���� 0�̱� ������ 0�� ���� ������ ������ ���ؼ� JLabel.RIGHT�� �������.
		numberOut.setBackground(Color.WHITE);	   // ��� ������ �� ������ �Ͼ��
		numberOut.setOpaque(true);				   // setOpaque �Լ��� ����� �����ϰ� ����� �Լ��̴�. 

		buttons = new JButton[24];				   // 0 ~ 9�׸��� ������ ��ư�� �ֱ� ���� �迭.
		
		lButtons = new JButton[6];				   // MC, MR, M+, M-, MS, M*�� �߰��ϱ� ���ؼ� �迭

		btnPanel = new JPanel(); //���� �гΰ���
		lbtnPanel = new JPanel(); // ���� �гΰ���

		// ���� ��ư�� �����.
		buttons[0] = new JButton("0");
		buttons[1] = new JButton("1");
		buttons[2] = new JButton("2");
		buttons[3] = new JButton("3");
		buttons[4] = new JButton("4");
		buttons[5] = new JButton("5");
		buttons[6] = new JButton("6");
		buttons[7] = new JButton("7");
		buttons[8] = new JButton("8");
		buttons[9] = new JButton("9");
		

		// ���� ��ư�� �����.
		buttons[10] = new JButton("��");
		buttons[11] = new JButton(".");
		buttons[12] = new JButton("=");
		buttons[13] = new JButton("/");
		buttons[14] = new JButton("*");
		buttons[15] = new JButton("-");
		buttons[16] = new JButton("+");
		buttons[17] = new JButton("��");
		buttons[18] = new JButton("1/x");
		buttons[19] = new JButton("%");
		buttons[20] = new JButton("��");
		buttons[21] = new JButton("CE");
		buttons[22] = new JButton("C");
		buttons[23] = new JButton("X2");
		
		// ���� ��ư�� �����.
		lButtons[0] = new JButton("MC");
		lButtons[1] = new JButton("MR");
		lButtons[2] = new JButton("M+");
		lButtons[3] = new JButton("M-");
		lButtons[4] = new JButton("MS");
		lButtons[5] = new JButton("M*");
		
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setForeground(Color.black); // ���� ��ư��	setForeground�� ���ڻ� setBackground�� ����
		}
		
		for (int i=0; i< lButtons.length; i++) {
			
			if(i>1 && i<5) {
				lButtons[i].setForeground(Color.gray); // ���� ��ư�� 
			}
			else {
				lButtons[i].setForeground(Color.black); // ���� ��ư��
			}
			
			
			
		}
		
		lbtnPanel.setLayout(new GridLayout(1, 6, 2, 2));
		
		lbtnPanel.add(lButtons[0]); //MC
		lbtnPanel.add(lButtons[1]); //MR
		lbtnPanel.add(lButtons[2]); //M+
		lbtnPanel.add(lButtons[3]); //M-
		lbtnPanel.add(lButtons[4]); //MS
		lbtnPanel.add(lButtons[5]); //M*

		//�̶� GridLayout�� ����ؼ�  ���� 6�� ���� 4�� �׸��� ������ 22�� �ְ� ��ư�� ��ġ�ߴ�. 
		btnPanel.setLayout(new GridLayout(6, 4, 2, 2));  

		btnPanel.add(buttons[19]); // %
		btnPanel.add(buttons[17]); // ��
		btnPanel.add(buttons[23]); // X2
		btnPanel.add(buttons[18]); // 1/X

		btnPanel.add(buttons[21]); // CE
		btnPanel.add(buttons[22]); // C
		btnPanel.add(buttons[20]); // ��
		btnPanel.add(buttons[13]); // /

		for (int i = 7; i <= 9; i++) {
			btnPanel.add(buttons[i]);
		}
		btnPanel.add(buttons[14]); // *

		for (int i = 4; i <= 6; i++) {
			btnPanel.add(buttons[i]);
		}

		btnPanel.add(buttons[15]); // -

		for (int i = 1; i <= 3; i++) {
			btnPanel.add(buttons[i]);
		}

		btnPanel.add(buttons[16]); // +

		btnPanel.add(buttons[10]); // ��
		btnPanel.add(buttons[0]); // 0
		btnPanel.add(buttons[11]); // .
		btnPanel.add(buttons[12]); // =

		// BorderLayout�� ����ؼ� ��, ��, ��, ��, ���ͷ� ������ ���� �� �� �ֵ��� �Ѵ�. (East, West, South, North, Center)
		// 
		captainPanel.setLayout(new BorderLayout()); 		//captainPanel�� BorderLayout ���� ����
		captainPanel.add(lbtnPanel, BorderLayout.NORTH);    // lbtnPanel�� ���ʿ�
		captainPanel.add(btnPanel, BorderLayout.SOUTH);     // brnPanel�� ���ʿ�

		getContentPane().add(numberOut, BorderLayout.NORTH);		//JFrame�� AWT�� �޸� add�޼��尡 �������� �ʴ´�. add�� AWT�� �ִ�. 
		getContentPane().add(captainPanel, BorderLayout.SOUTH);		//�׷��� getContentPane�� ����ؼ� add�� �߰�������Ѵ�. 
																	//getContentPane()�� SWING���� ����ϴ� ���� ==  https://blog.naver.com/yks9574/220239916737
		requestFocus();

		addWindowListener(new WindowAdapter() {		   //Frame�� ���� �̺�Ʈ�� ó���ϱ� ���ؼ��� WindowListener��°��� ���. 
													   //�̰��� â�� ������ȭ, �ִ�ȭ, Ȱ��ȭ, ��Ȱ��ȭ, �����Ȳ���� ���¸� ó�� ����.
			public void windowClosing(WindowEvent e) { //Window�� ������ ���� �� ȣ���ϴ� �޼ҵ�.
				System.exit(0);
			}
		});
	}
	

	public static void main(String args[]) {
		Calculator cal = new Calculator(); //cal�̶�� ��ü ����
		cal.setTitle("Calc"); // ������ calc
		cal.setSize(300, 300); //������� 300 x 300
		cal.pack(); 		   //�ʿ���� ������ ����? ���߱� ���ؼ� pack���.
		cal.setVisible(true);	//���̵��� ! 
		cal.setResizable(false);
	}
}
