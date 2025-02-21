package dynamic_beat_11;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	
	
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	
	
	private Image Background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();//introBackgroud->Background로 변경한 이유는 Background인스턴스를 대기화면 배경과 게임화면 배경 모두 담을 변수처럼 사용하기 위해서 리소스만 다르게 할뿐임
	
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;//게임화면인지
	
	ArrayList<Track> tracklist = new ArrayList<Track>();
	private Music selectedMusic;
	private Image titleImage;
	private Image selectedImage; 
	private Music introMusic = new Music("flow-211881.mp3", true);//시작하자 인트로 화면 나오고 게임화면 나오면 소리 꺼야함
	private int nowSelected = 0;
	public static Game game = new Game();
	
	
	public DynamicBeat() {
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		//인트로 뮤직 스타트
		introMusic.start();

		//tracklist 노래 정보 넣기 ,1,2,3번 인덱스를 받게됨
		tracklist.add(new Track("Mighty Love Title Image.png", "Mighty Love Start Image.png", "Mighty Love Game Image.jpg", "Mighty Love Selected.mp3", "Joakim Karud - Mighty Love.mp3"));
		tracklist.add(new Track("Wild Flower Title Image.png", "Wild Flower Start Image.png", "Wild Flower Game Image.jpg", "Wild Flower Selected.mp3", "Joakim Karud - Wild Flower.mp3"));
		tracklist.add(new Track("Energy Title Image.png", "Energy Start Image.png", "Energy Game Image.png", "Energy Selected.mp3", "Bensound - energy.mp3"));
		
		
		//start Button
		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				//게임시작 버튼을 누르면 인트로 노래는 멈춰야함, 그리고 select된 노래가 시작되어야
				//하지만 노래를 선택하는 화면에서는 노래들의 풀 노래가 나오는것이 아닌, 하이라이트만 나오는것이 보통이므로 하아라이트된 노래가 재생되어야한다
				introMusic.close();
				enterMain();
				
			}
		});
		add(startButton);
		//quit Button
		quitButton.setBounds(40, 330, 400, 100);//가로 400, 세로 100
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
	
		//게임메인화면에서  왼쪽버튼
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);//가로 400, 세로 100
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
			//왼쪽 버튼 이벤트
				selectLeft();
			}
		});
		add(leftButton);
		
		//게임메인화면에서  오른쪽버튼
				rightButton.setVisible(false);
				rightButton.setBounds(1080, 310, 60, 60);//가로 400, 세로 100
				rightButton.setBorderPainted(false);
				rightButton.setContentAreaFilled(false);
				rightButton.setFocusPainted(false);
				rightButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						rightButton.setIcon(rightButtonEnteredImage);
						rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
						buttonEnteredMusic.start();
					}
					@Override
					public void mouseExited(MouseEvent e) {
						rightButton.setIcon(rightButtonBasicImage);
						rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e) {
						Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
						buttonEnteredMusic.start();
					//오른쪽 버튼 이벤트
						selectRight();
					}
				});
				add(rightButton);
		
		//easyButton 기능
				easyButton.setVisible(false);
				easyButton.setBounds(375, 580, 250, 67);//가로 400, 세로 100
				easyButton.setBorderPainted(false);
				easyButton.setContentAreaFilled(false);
				easyButton.setFocusPainted(false);
				easyButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						easyButton.setIcon(easyButtonEnteredImage);
						easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
						buttonEnteredMusic.start();
					}
					@Override
					public void mouseExited(MouseEvent e) {
						easyButton.setIcon(easyButtonBasicImage);
						easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e) {
						Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
						buttonEnteredMusic.start();
					//난이도 쉬움 이벤트
						gameStart(nowSelected, "easy");
					}
				});
				add(easyButton);
		
		//hardButton 기능
				hardButton.setVisible(false);
				hardButton.setBounds(655, 580, 250, 67);//가로 400, 세로 100
				hardButton.setBorderPainted(false);
				hardButton.setContentAreaFilled(false);
				hardButton.setFocusPainted(false);
				hardButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						hardButton.setIcon(hardButtonEnteredImage);
						hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
						buttonEnteredMusic.start();
					}
					@Override
					public void mouseExited(MouseEvent e) {
						hardButton.setIcon(hardButtonBasicImage);
						hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e) {
						Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
						buttonEnteredMusic.start();
					//난이도 어려움 이벤트
						gameStart(nowSelected, "hard");
					}
				});
				add(hardButton);		
				
				
		//backButton 기능
				backButton.setVisible(false);
				backButton.setBounds(20, 50, 60, 60);
				backButton.setBorderPainted(false);
				backButton.setContentAreaFilled(false);
				backButton.setFocusPainted(false);
				backButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						backButton.setIcon(backButtonEnteredImage);
						backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
						buttonEnteredMusic.start();
					}
					@Override
					public void mouseExited(MouseEvent e) {
						backButton.setIcon(backButtonBasicImage);
						backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e) {
						Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
						buttonEnteredMusic.start();
					//메인화면으로 돌아가는 이벤트
						backMain();
					}
				});
				add(backButton);			
				
				
		//exit Button
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);
		
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);//Graphics2D게임 화면에서 노래제목 글자가꺠지는것을 방지 
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {//Graphics2D
		g.drawImage(Background, 0, 0, null);//add가 된것이 아니라 단순히 이미지만 그려주는거, 역동적으로 이미지를 그려주는것
		if(isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g);//add로 추가된 애들만 그려준다
		this.repaint();
	}
	
	//selectTrack 함수화
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null) 
			selectedMusic.close();//어떤곡이 이미 실행되고있다면 일단 정지
		titleImage	= new ImageIcon(Main.class.getResource("../images/" + tracklist.get(nowSelected).getTitleImage())).getImage();
		titleImage	= new ImageIcon(Main.class.getResource("../images/" + tracklist.get(nowSelected).getTitleImage())).getImage();
		selectedImage	= new ImageIcon(Main.class.getResource("../images/" + tracklist.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(tracklist.get(nowSelected).getStartMusic(), true);//무한재생
		selectedMusic.start();
		
	}

	//곡 선택 화면에서 왼쪽버튼 기능처리
	public void selectLeft() {
		if(nowSelected == 0) {//맨 처음꺼 가장 왼쪽
			nowSelected = tracklist.size() -1;//가장 처음일때 왼쪽 버튼을 누르게 된다면 가장 마지막 노래로 재생이 되어야하니까 전체 사이즈에서 -1을 해준다 왜냐 0부터니까
		}else {//가장 맨처음이 아닐때는 하나씩 -1하면됨
			nowSelected--;
			selectTrack(nowSelected);
		}
	}
	
	//곡 선택 화면에서 오른쪽버튼 기능처리
	public void selectRight() {
		if(nowSelected == tracklist.size()-1) {//맨 마지막 노래 가장 오른쪽
			nowSelected = 0;//가장 맨 마지막일때 처음 노래로 돌아가야하니까 0으로 설정
		}else {//가장 맨 마지막이 아닐때는 하나씩 +1하면됨
			nowSelected++;
			selectTrack(nowSelected);
		}
	}
	
	//게임난이도 기능 함수
	public void gameStart(int nowSelected, String diffculty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		//메인화면 값 변수 false처리
		isMainScreen = false;
		//게임화면이기떄문에 버튼 숨기기
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		Background = new ImageIcon(Main.class.getResource("../images/" + tracklist.get(nowSelected).getGameImage())).getImage(); 
		//게임화면에서 메인화면으로 나가는 버튼
		backButton.setVisible(true);
		isGameScreen = true;
		setFocusable(true);//게임창에(메인프레임) 키보드포커스가 적용됨
	}
	
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		
		Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); 
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
	}
	
	public void enterMain() {
		//게임시작버튼 이벤트 
		//리택토링 => 함수화
		startButton.setVisible(false);
		quitButton.setVisible(false);
		Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); 
		isMainScreen =  true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}
}
