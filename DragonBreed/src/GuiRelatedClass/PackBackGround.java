package GuiRelatedClass;

import java.awt.Dimension;

import javax.swing.JLayeredPane;

// 다양한 컴포넌트들을 관리해줄 컨테이너
// 메인프레임에는 이 패널만 추가
// 추가할 컴포넌트들의 순서를 관리하는 역할

public class PackBackGround extends JLayeredPane {

	public PackBackGround() {
		super();
		setSize(1280, 720);
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);
		setVisible(true);
	}
}
