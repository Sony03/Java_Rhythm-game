package dynamic_beat_15;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter { //사용자가 키를 누르면 그것을 감지하는 클래스

	@Override
	public void keyPressed(KeyEvent e) {
		if(DynamicBeat.game == null) return;
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.pressS();
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.pressD();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.pressF();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.presssSpace();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.pressJ();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.pressK();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.pressL();
		}
	}
	
	
	//키를 눌렀다가 떼는 경우 이벤트
	@Override
	public void keyReleased(KeyEvent e) {
		if(DynamicBeat.game == null) return;
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.releasedS();
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releasedD();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.releasedF();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.releasedSpace();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.releasedJ();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.releasedK();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.releasedL();
		}

	}
}
