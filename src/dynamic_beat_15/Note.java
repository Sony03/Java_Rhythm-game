package dynamic_beat_15;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

//각 노트는 하나의 부분적인 기능으로 떨어져아하기때문에 쓰레드를 이용해야함
public class Note extends Thread {
	
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SlEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; //노트 위치 // 떨어지는 노트가 생성된지 1초뒤에 노트 판정대 위에 도달할 수있게 연산
	private String noteType;
	private boolean proceeded = true; //현재 노트의 진행여부
	
	//현재 노트 타입을 알아낼 수 있는 메소드
	public String getNoteType(){
		return noteType;
	}
	
	
	//현재 노트가 진행이 되고있는지 확인하는 메소드
	public boolean isProceeded() {
		return proceeded;
	}
	//해당 노트가 입력되지않게 움직이지않게 하기 위해서
	public void close() {
		proceeded = false;
	}
	
	
	//생성자
	public Note(String noteType) {
		if(noteType.equals("S")) {
			x = 228;
		}else if(noteType.equals("D")) {
			x = 332;
		}else if(noteType.equals("F")) {
			x = 436;
		}else if(noteType.equals("Space")) {
			x = 540;
		}else if(noteType.equals("J")) {
			x = 744;
		}else if(noteType.equals("K")) {
			x = 848;
		}else if(noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
		
		
		
		
		
		
	}
	
	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
			
		}else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;//7만큼 y좌표가 증가해서 즉 7만큼 아래로 떨어짐
		if(y>620) {//노트가 판정바를 넘어서는 지점
			System.out.println("miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {//노트 무한으로 떨어트리기
				drop();
				if(proceeded) { //진행중인 상황이라면
					Thread.sleep(Main.SlEEP_TIME); //쓰레드 쉬기
				}else {
					interrupt();//중단
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void judge() {
		if(y >= 613) {
			System.out.println("Late");
			close();
		}else if(y >= 600 ) {
			System.out.println("Good");
			close();
		}else if(y >= 587 ) {
			System.out.println("Great");
			close();
		}else if(y >= 573 ) {
			System.out.println("Perfect");
			close();
		}else if(y >= 565 ) {
			System.out.println("Great");
			close();
		}else if(y >= 550 ) {
			System.out.println("Good");
			close();
		}else if(y >= 535 ) {
			System.out.println("Early");
			close();
		}
	}
	
}
