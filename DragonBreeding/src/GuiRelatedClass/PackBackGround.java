package GuiRelatedClass;

import java.awt.Dimension;

import javax.swing.JLayeredPane;

// �پ��� ������Ʈ���� �������� �����̳�
// ���������ӿ��� �� �гθ� �߰�
// �߰��� ������Ʈ���� ������ �����ϴ� ����

public class PackBackGround extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PackBackGround() {
		super();
		setSize(1280, 720);
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);
		setVisible(true);
	}
}
