package GuiRelatedClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;

public class DragonStatePanel extends JPanel {
	// �˸����� �ۼ��� ��
		private JLabel dialogue_label;
		// ����
		private JLabel blank;

		public DragonStatePanel(String string) {

			// ���� ��
			blank = new JLabel("       ");
			// ���ڷ� �޾ƿ� string ���� ������ ��
			dialogue_label = new JLabel(string);
			dialogue_label.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 20));
			dialogue_label.setVisible(true);
			// �ش� Ŭ���� �⺻ ����

			setLayout(new BorderLayout());
			setBackground(new Color(237, 170, 125));
			setForeground(Color.WHITE);
			setBounds(50, 50, 300, 200);
			setVisible(true);
			add(blank, BorderLayout.WEST);
			add(dialogue_label, BorderLayout.CENTER);

		}


		// �� ���� �˸� �г� �����ϰ� �� �˸� �г� �߰��ϴ� �޼���
		static public void insert_dialogue(String string) {
			MainFrame.pack_back.remove(MainFrame.dragon_state_panel);
			MainFrame.dragon_state_panel = new DragonStatePanel(string);
			MainFrame.pack_back.add(MainFrame.dragon_state_panel, new Integer(1), 0);
		}
}
