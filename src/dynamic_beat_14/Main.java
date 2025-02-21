package dynamic_beat_14;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED =  3; //노트의 속도 지정
	public static final int SlEEP_TIME = 10; //노트가 무작정 떨어지는것이 아니라, 얼마간의 시간의 주기로 떨어지는 지정 
	public static final int REACH_TIME  = 2; //노트가 생성되고 나서 판정바에 떨어지기전까지의 시간
	
	
	public static void main(String[] args) {
		
		new DynamicBeat();

	}

}
