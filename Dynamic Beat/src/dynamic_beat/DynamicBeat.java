package dynamic_beat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButton.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.jpg"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.jpg"));
	
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	

	
	private Image Background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;
	
	ArrayList<Track> trackList = new ArrayList<Track>();
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private int nowSelected = 0;
	
	
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
		
		Music introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		
		trackList.add(new Track("Mighty Love Start Image.jpg"
				, "Mighty Love Start Image.jpg"
				, "Mighty Love Game Image.jpg"
				, "Mighty Love.mp3"));
		trackList.add(new Track("Energy Start Image.jpg"
				, "Energy Start Image.jpg"
				, "Energy Game Image.jpg"
				, "Energy.mp3"));
		trackList.add(new Track("Wild Flower Start Image.jpg"
				, "Wild Flower Start Image.jpg"
				, "Wild Flower Game Image.jpg"
				, "Wild Flower.mp3"));
		trackList.add(new Track("Next Level Start Image.jpg"
				, "Next Level Start Image.jpg"
				, "Next Level Game Image.jpg"
				, "Next Level.mp3"));
		trackList.add(new Track("Mighty Love Start Image.jpg"
				, "Mighty Love Start Image.jpg"
				, "Mighty Love Game Image.jpg"
				, "Day.mp3"));
		trackList.add(new Track("Energy Start Image.jpg"
				, "Energy Start Image.jpg"
				, "Energy Game Image.jpg"
				, "Eight.mp3"));
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exitButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		startButton.addMouseListener(new MouseAdapter() {

//			@Override
//			public void mouseEntered(MouseEvent e) {
//				startButton.setIcon(startButtonEnteredImage);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				startButton.setIcon(startButtonBasicImage);
//			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 게임 시작 버튼
				introMusic.close();
				selectTrack(0);
				startButton.setVisible(false);
				quitButton.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
				isMainScreen = true;
			}
		});
		add(startButton);
		
		quitButton.setBounds(40, 330, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		quitButton.addMouseListener(new MouseAdapter() {

//			@Override
//			public void mouseEntered(MouseEvent e) {
//				startButton.setIcon(startButtonEnteredImage);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				startButton.setIcon(startButtonBasicImage);
//			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(quitButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		leftButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//왼쪽 버튼 이벤트
				selectLeft();
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rightButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//오른쪽 버튼 이벤트
				selectRight();
			}
		});
		add(rightButton);
		
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
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(Background, 0, 0, null);
		if(isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
//			g.drawImage(titleImage, 340, 70, null);  나중에 이미지넣을거 곡제목
		}
		paintComponents(g);
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getGameMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else {
			nowSelected--;
		}
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() -1) {
			nowSelected = 0;
		} else {
			nowSelected++;
		}
		selectTrack(nowSelected);
	}

}
