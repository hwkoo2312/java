package Calc;

import javax.swing.*;			//AWT를 발전시켜 더 좋은 GUI를 보여주는게 SWING 이다. 
import java.awt.*;			    //Java에서 그래픽 프로그램을 하기 위한 클래스 라이브러리를 제공하는데 이것이 AWT다 
import java.awt.event.*;		//Java에서 제공하는 이벤트를 사용하기 위해서는 java.awt.event를 import해야하는데, 마우스로 버튼 클릭 or 키보드로 입력 이벤트를 위해 import했다.

public class Calculator extends JFrame { 		//awt는 상속을 Frame으로 하지만 SWING을 사용하기에 앞에 J를 붙여서 JFrame을 상속.

	private JLabel numberOut; // 숫자가 표시될 공간
	private JButton buttons[]; // 버튼을 배열로 만들어서 이벤트 값이 buttons[i]면으로 응용해서 switch문으로 해결할 예정.
	private JButton lButtons[]; // MC, MR, M+, M-, MS, M*을 추가하기 위해서 배열을 하나 더 만들었다.
	private JPanel captainPanel, btnPanel, lbtnPanel; // 버튼과 레이블을 배치할 공간

	// GUI를 생성해보자.
	public Calculator() {

		captainPanel = new JPanel(); //가장 큰 공간을 만들기 위해서 생성.

		numberOut = new JLabel("0", JLabel.RIGHT); // 계산기의 초기 값은 0이기 때문에 0을 쓰고 오른쪽 정렬을 위해서 JLabel.RIGHT를 사용헀다.
		numberOut.setBackground(Color.WHITE);	   // 계산 내용이 들어갈 공간은 하얀색
		numberOut.setOpaque(true);				   // setOpaque 함수는 배경을 투명하게 만드는 함수이다. 

		buttons = new JButton[24];				   // 0 ~ 9그리고 나머지 버튼을 넣기 위한 배열.
		
		lButtons = new JButton[6];				   // MC, MR, M+, M-, MS, M*을 추가하기 위해서 배열

		btnPanel = new JPanel(); //숫자 패널공간
		lbtnPanel = new JPanel(); // 영어 패널공간

		// 숫자 버튼을 만든다.
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
		

		// 연산 버튼을 만든다.
		buttons[10] = new JButton("±");
		buttons[11] = new JButton(".");
		buttons[12] = new JButton("=");
		buttons[13] = new JButton("/");
		buttons[14] = new JButton("*");
		buttons[15] = new JButton("-");
		buttons[16] = new JButton("+");
		buttons[17] = new JButton("√");
		buttons[18] = new JButton("1/x");
		buttons[19] = new JButton("%");
		buttons[20] = new JButton("←");
		buttons[21] = new JButton("CE");
		buttons[22] = new JButton("C");
		buttons[23] = new JButton("X2");
		
		// 영어 버튼을 만든다.
		lButtons[0] = new JButton("MC");
		lButtons[1] = new JButton("MR");
		lButtons[2] = new JButton("M+");
		lButtons[3] = new JButton("M-");
		lButtons[4] = new JButton("MS");
		lButtons[5] = new JButton("M*");
		
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setForeground(Color.black); // 연산 버튼들	setForeground는 글자색 setBackground는 배경색
		}
		
		for (int i=0; i< lButtons.length; i++) {
			
			if(i>1 && i<5) {
				lButtons[i].setForeground(Color.gray); // 영어 버튼들 
			}
			else {
				lButtons[i].setForeground(Color.black); // 영어 버튼들
			}
			
			
			
		}
		
		lbtnPanel.setLayout(new GridLayout(1, 6, 2, 2));
		
		lbtnPanel.add(lButtons[0]); //MC
		lbtnPanel.add(lButtons[1]); //MR
		lbtnPanel.add(lButtons[2]); //M+
		lbtnPanel.add(lButtons[3]); //M-
		lbtnPanel.add(lButtons[4]); //MS
		lbtnPanel.add(lButtons[5]); //M*

		//이때 GridLayout을 사용해서  세로 6개 가로 4개 그리고 공간은 22로 주고 버튼을 배치했다. 
		btnPanel.setLayout(new GridLayout(6, 4, 2, 2));  

		btnPanel.add(buttons[19]); // %
		btnPanel.add(buttons[17]); // √
		btnPanel.add(buttons[23]); // X2
		btnPanel.add(buttons[18]); // 1/X

		btnPanel.add(buttons[21]); // CE
		btnPanel.add(buttons[22]); // C
		btnPanel.add(buttons[20]); // ←
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

		btnPanel.add(buttons[10]); // ±
		btnPanel.add(buttons[0]); // 0
		btnPanel.add(buttons[11]); // .
		btnPanel.add(buttons[12]); // =

		// BorderLayout을 사용해서 동, 서, 남, 북, 센터로 방향을 설정 할 수 있도록 한다. (East, West, South, North, Center)
		// 
		captainPanel.setLayout(new BorderLayout()); 		//captainPanel을 BorderLayout 으로 생성
		captainPanel.add(lbtnPanel, BorderLayout.NORTH);    // lbtnPanel을 북쪽에
		captainPanel.add(btnPanel, BorderLayout.SOUTH);     // brnPanel을 남쪽에

		getContentPane().add(numberOut, BorderLayout.NORTH);		//JFrame은 AWT와 달리 add메서드가 존재하지 않는다. add는 AWT에 있다. 
		getContentPane().add(captainPanel, BorderLayout.SOUTH);		//그래서 getContentPane을 사용해서 add를 추가해줘야한다. 
																	//getContentPane()을 SWING에서 사용하는 이유 ==  https://blog.naver.com/yks9574/220239916737
		requestFocus();

		addWindowListener(new WindowAdapter() {		   //Frame의 종료 이벤트를 처리하기 위해서는 WindowListener라는것을 사용. 
													   //이것은 창의 아이콘화, 최대화, 활성화, 비활성화, 종료상황등의 상태를 처리 가능.
			public void windowClosing(WindowEvent e) { //Window가 닫히고 있을 때 호출하는 메소드.
				System.exit(0);
			}
		});
	}
	

	public static void main(String args[]) {
		Calculator cal = new Calculator(); //cal이라는 객체 선언
		cal.setTitle("Calc"); // 제목은 calc
		cal.setSize(300, 300); //사이즈는 300 x 300
		cal.pack(); 		   //필요없는 공간을 제거? 맞추기 위해서 pack사용.
		cal.setVisible(true);	//보이도록 ! 
		cal.setResizable(false);
	}
}
