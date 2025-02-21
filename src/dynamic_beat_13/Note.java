package dynamic_beat_13;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

//각 노트는 하나의 부분적인 기능으로 떨어져아하기때문에 쓰레드를 이용해야함
public class Note extends Thread {
	
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - 1000 / Main.SlEEP_TIME * Main.NOTE_SPEED; //노트 위치 // 떨어지는 노트가 생성된지 1초뒤에 노트 판정대 위에 도달할 수있게 연산
	private String noteType;
	
	//생성자
	public Note(int x, String noteType) {
		this.x = x;
		this.noteType = noteType;				
		
	}
	
	public void screenDraw(Graphics2D g) {
		if(noteType.equals("short")) {
			g.drawImage(noteBasicImage, x, y, null);
			
		}else if(noteType.equals("long")) {//space바를 위해서
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;//7만큼 y좌표가 증가해서 즉 7만큼 아래로 떨어짐
	}
	
	@Override
	public void run() {
		try {
			while(true) {//노트 무한으로 떨어트리기
				drop();
				Thread.sleep(Main.SlEEP_TIME);//쓰레드를 SlEEP_TIME 0.01초 쉬게 하고 다시 떨어뜨리고 기능하기 1초에 100번 실행 1초에 y가 700px이 내려감
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void close() {
		
	}

	public void dropNotes(String titleName) {
		
	}
}
