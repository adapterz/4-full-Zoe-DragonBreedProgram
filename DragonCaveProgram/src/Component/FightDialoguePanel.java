package Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;

public class FightDialoguePanel extends JPanel {
	// 대화내용
		private JLabel dialogue_label;
		// 여백
		private JLabel blank;

		public FightDialoguePanel(String string) {

			// 여백 라벨
			blank = new JLabel("       ");
			// 인자로 받아온 string 내용을 보여줄 라벨
			dialogue_label = new JLabel(string);
			dialogue_label.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 20));
			dialogue_label.setVisible(true);

			// 기본 설정
			setLayout(new BorderLayout());
			setBackground(new Color(0,78,162));
			setForeground(Color.WHITE);
			setBounds(230, 50, 820, 150);
			setVisible(true);
			add(blank, BorderLayout.WEST);
			add(dialogue_label, BorderLayout.CENTER);

		}

		// 기존의 대화내용 패널 삭제하고 새 대화내용 패널 추가
		static public void insert_dialogue(String string) {
			MainFrame.pack_back.remove(MainFrame.fight_dialogue_panel);
			MainFrame.fight_dialogue_panel = new FightDialoguePanel(string);
			MainFrame.pack_back.add(MainFrame.fight_dialogue_panel, new Integer(1), 0);
		}
}
