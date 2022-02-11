package GuiRelatedClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;


// 드래곤이 몬스터와 싸우는 메서드를 호출했을 때 드래곤이 몬스터를 공격한 알림내용 보여줄 패널
public class DragonAttackPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 알림내용 작성할 라벨
	private JLabel dialogue_label;
	// 여백
	private JLabel blank;

	public DragonAttackPanel(String string) {
		// 여백라벨
		blank = new JLabel("    ");
		// 인자로 받아온 string 내용을 보여줄 라벨
		dialogue_label = new JLabel(string);
		dialogue_label.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 20));
		dialogue_label.setVisible(true);

		// 기본 설정
		setLayout(new BorderLayout());
		setBounds(50, 50, 300, 250);
		setBackground(new Color(211, 211, 211));
		setForeground(Color.WHITE);
		setVisible(true);
		add(blank, BorderLayout.WEST);
		add(dialogue_label, BorderLayout.CENTER);

	}
	
	// 그 전의 알림 패널 삭제하고 새 알림 패널 추가하는 메서드
	static public void insert_dialogue(String string) {
		MainFrame.pack_back.remove(MainFrame.dragon_attack_panel);
		MainFrame.dragon_attack_panel = new DragonAttackPanel(string);
		MainFrame.pack_back.add(MainFrame.dragon_attack_panel, new Integer(1), 0);
	}
}
