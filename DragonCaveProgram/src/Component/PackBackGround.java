package Component;

import java.awt.Dimension;

import javax.swing.JLayeredPane;

// 알림내용 패널, 배경 패널 등을 추가해줄 컨테이너 클래스(추가할 패널들의 순서를 관리하는 역할)
public class PackBackGround extends JLayeredPane {

	public PackBackGround() {
		super();
		setSize(1280, 720);
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);
		setVisible(true);
	}
}
