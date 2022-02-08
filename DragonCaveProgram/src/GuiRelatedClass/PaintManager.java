package GuiRelatedClass;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import enums.BackGround;
import enums.Growth;

// ���� �巡���� �׷��� �׸��� ���� �г� Ŭ����
public class PaintManager extends JPanel {
	// �����
	String path = System.getProperty("user.dir") + "/src/Image/";
	// ��� �� �׸�
	private Image loadingScreen = new ImageIcon(path + "loading.png").getImage();
	private Image mainScreen = new ImageIcon(path + "home.jpg").getImage();
	private Image fightScreen = new ImageIcon(path + "yard_1.png").getImage();
	private Image flightScreen = new ImageIcon(path + "flight.png").getImage();
	private Image happy_endScreen = new ImageIcon(path + "happyending.png").getImage();
	private Image endScreen = new ImageIcon(path + "normalending.png").getImage();;
	private Image sad_endScreen = new ImageIcon(path + "sadending.png").getImage();;

	// �巡���� ����ܰ躰 �׸�
	private Image egg_image = new ImageIcon(path + "egg.png").getImage();
	private Image hatchling_image = new ImageIcon(path + "hatchling.png").getImage();
	private Image juvenile_image = new ImageIcon(path + "juvenile.png").getImage();
	private Image juvenile_flight = new ImageIcon(path + "juvenile_symmetry.png").getImage();
	private Image adult_image = new ImageIcon(path + "adult.png").getImage();

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