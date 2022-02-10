package GuiRelatedClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;

public class DragonStatePanel extends JPanel {
	// 알림내용 작성할 라벨
		private JLabel dialogue_label;
		// 여백
		private JLabel blank;

		public DragonStatePanel(String string) {

			// 여백 라벨
			blank = new JLabel("       ");
			// 인자로 받아온 string 내용 보여줄 라벨
			dialogue_label = new JLabel(string);
			dialogue_label.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 20));
			dialogue_label.setVisible(true);
			// 해당 클래스 기본 설정

			setLayout(new BorderLayout());
			setBackground(new Color(237, 170, 125));
			setForeground(Color.WHITE);
			setBounds(50, 50, 300, 200);
			setVisible(true);
			add(blank, BorderLayout.WEST);
			add(dialogue_label, BorderLayout.CENTER);

		}


		// 그 전의 알림 패널 삭제하고 새 알림 패널 추가하는 메서드
		static public void insert_dialogue(String string) {
			MainFrame.pack_back.remove(MainFrame.dragon_state_panel);
			MainFrame.dragon_state_panel = new DragonStatePanel(string);
			MainFrame.pack_back.add(MainFrame.dragon_state_panel, new Integer(1), 0);
		}
}
