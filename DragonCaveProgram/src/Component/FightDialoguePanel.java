package Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;

public class FightDialoguePanel extends JPanel {
	// ��ȭ����
		private JLabel dialogue_label;
		// ����
		private JLabel blank;

		public FightDialoguePanel(String string) {

			// ���� ��
			blank = new JLabel("       ");
			// ���ڷ� �޾ƿ� string ������ ������ ��
			dialogue_label = new JLabel(string);
			dialogue_label.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 20));
			dialogue_label.setVisible(true);

			// �⺻ ����
			setLayout(new BorderLayout());
			setBackground(new Color(0,78,162));
			setForeground(Color.WHITE);
			setBounds(230, 50, 820, 150);
			setVisible(true);
			add(blank, BorderLayout.WEST);
			add(dialogue_label, BorderLayout.CENTER);

		}

		// ������ ��ȭ���� �г� �����ϰ� �� ��ȭ���� �г� �߰�
		static public void insert_dialogue(String string) {
			MainFrame.pack_back.remove(MainFrame.fight_dialogue_panel);
			MainFrame.fight_dialogue_panel = new FightDialoguePanel(string);
			MainFrame.pack_back.add(MainFrame.fight_dialogue_panel, new Integer(1), 0);
		}
}
