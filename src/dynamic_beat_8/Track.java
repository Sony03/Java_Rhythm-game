package dynamic_beat_8;

//노래에 대한 정보를 갖는 클래스 
public class Track {
	private String titleImage;//제목 부분 이미지
	private String startImage;//게임 선택 창 표지 이미지
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	private String gameImage;//해당 곡을 실행했을때 표지
	private String startMusic;//게임 선택 창 음악
	private String gameMusic;//해당 곡을 실행했을때 음악
	
	//track 생성자 : track class를 이용해 새로운 변수를 만들때 한번에 값들을 한번에 초기화
	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
	}
	
	

	
	
	

}
