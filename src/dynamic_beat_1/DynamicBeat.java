package dynamic_beat_1;

import javax.swing.JFrame;

public class DynamicBeat extends JFrame {
	
	public DynamicBeat() {
		setTitle("Dynamic Beat");//제목 지정
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //게임창 사이즈 지정(nain 클래스에 정의)
		setResizable(false);//시용자가 맘대로 게임창 사이즈 조절 제한
		setLocationRelativeTo(null);//게임창 컴터 화면정가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//나가기 버튼 활성화
		setVisible(true);//게임창 보이게 세팅
	}

}
