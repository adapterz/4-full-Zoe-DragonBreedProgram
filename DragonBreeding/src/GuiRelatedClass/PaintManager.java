package GuiRelatedClass;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import enums.BackGround;
import enums.Growth;

// ���� �巡���� �׷��� �׸��� ���� �г� Ŭ����
public class PaintManager extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ��� �� �׸�
	private Image loadingScreen = new ImageIcon(getClass().getClassLoader().getResource("loading.png")).getImage();
	private Image guideScreen = new ImageIcon(getClass().getClassLoader().getResource("guide.png")).getImage();
	private Image mainScreen = new ImageIcon(getClass().getClassLoader().getResource("home.jpg")).getImage();
	private Image fightScreen = new ImageIcon(getClass().getClassLoader().getResource("yard_1.png")).getImage();
	private Image flightScreen = new ImageIcon(getClass().getClassLoader().getResource("flight.png")).getImage();
	private Image happy_endScreen = new ImageIcon(getClass().getClassLoader().getResource("happyending.png")).getImage();
	private Image endScreen = new ImageIcon(getClass().getClassLoader().getResource("normalending.png")).getImage();;
	private Image sad_endScreen = new ImageIcon(getClass().getClassLoader().getResource("sadending.png")).getImage();;

	// �巡���� ����ܰ躰 �׸�
	private Image egg_image = new ImageIcon(getClass().getClassLoader().getResource("egg.png")).getImage();
	private Image hatchling_image = new ImageIcon(getClass().getClassLoader().getResource("hatchling.png")).getImage();
	private Image juvenile_image = new ImageIcon(getClass().getClassLoader().getResource( "juvenile.png")).getImage();
	private Image juvenile_flight = new ImageIcon(getClass().getClassLoader().getResource( "juvenile_symmetry.png")).getImage();
	private Image adult_image = new ImageIcon(getClass().getClassLoader().getResource("adult.png")).getImage();

	// � ���� �巡���� �׷����� �����ִ� ����(enum) ����
	public static Growth stage; // ������ �巡���� ����ܰ� ����
	public static BackGround background; // ������ ����� ����

	// �⺻����
	public PaintManager() {
		setSize(1280, 720);
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);
		setVisible(true);
		init();
	}

	// �ʱ�ȭ
	private void init() {
		// ������ ���� �巡�� ���� �ʱ�ȭ
		stage = Growth.EGG;
		background = BackGround.LOADING;

	}

	// �׸��� �޼��� (JPanel�� �޼��� �������̵�)
	public void paintComponent(Graphics g) {
// ������ �׸� �б�ó��
		// ������ ���: �ε�â
		if (background == BackGround.LOADING) {
			g.drawImage(loadingScreen, 0, 0, null);
			// ������ ���: ���̵� ȭ��
		} else if (background == BackGround.GUIDE) {
			g.drawImage(guideScreen, 0, 0, null);

			// ������ ���: �ο����
		} else if (background == BackGround.FIGHT) {
			g.drawImage(fightScreen, 0, 0, null);

			switch (stage) {
			// ������ �巡��: ���Ʊ�
			case HATCHLING:

				g.drawImage(hatchling_image, 200, 450, null);
				break;
			// ������ �巡��: �����
			case JUVENILE:

				g.drawImage(juvenile_image, 200, 400, null);
				break;

			}
			// ������ ���: �������
		} else if (background == BackGround.FLIGHT) {
			g.drawImage(flightScreen, 0, 0, null);
			g.drawImage(juvenile_flight, 1000, 400, null);
			// ������ ���: ��
		} else if (background == BackGround.HOME) {
			g.drawImage(mainScreen, 0, 0, null);
			switch (stage) {
			// ������ �巡��: ��
			case EGG:

				g.drawImage(egg_image, 155, 275, null);

				break;
			// ������ �巡��: ���Ʊ�
			case HATCHLING:

				g.drawImage(hatchling_image, 155, 310, null);
				break;
			// ������ �巡��: �����
			case JUVENILE:

				g.drawImage(juvenile_image, 145, 280, null);
				break;
			// ������ �巡��: � (����)
			case ADULT:
				g.drawImage(adult_image, 580, 300, null);
				break;

			}
			// ������ ���: ���ǿ���
		} else if (background == BackGround.HAPPY_END) {
			g.drawImage(happy_endScreen, 0, 0, null);
			g.drawImage(adult_image, 800, 100, null);
			// ������ ���: �븻����
		} else if (background == BackGround.NORMAL_END) {
			g.drawImage(endScreen, 0, 0, null);
			g.drawImage(adult_image, 800, 100, null);
			// ������ ���: ���忣��
		} else if (background == BackGround.SAD_END) {
			g.drawImage(sad_endScreen, 0, 0, null);
			g.drawImage(adult_image, 800, 100, null);

		}

	}

}
