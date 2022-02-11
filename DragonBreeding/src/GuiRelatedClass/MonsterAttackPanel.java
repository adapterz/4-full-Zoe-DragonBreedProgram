package GuiRelatedClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;

// �巡���� ���Ϳ� �ο�� �޼��带 ȣ������ �� ���Ͱ� �巡���� ������ �˸����� ������ �г� Ŭ����
public class MonsterAttackPanel extends JPanel {

	// �˸����� �ۼ��� ��
	private JLabel dialogue_label;
	// ����
	private JLabel blank;

	public MonsterAttackPanel(String string) {
		// �����
		blank = new JLabel("    ");
		// ���ڷ� �޾ƿ� string ������ ������ ��
		dialogue_label = new JLabel(string);
		dialogue_label.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 20));
		dialogue_label.setVisible(true);

		// �⺻ ����
		setLayout(new BorderLayout());
		setBounds(800, 50, 300, 250);
		setBackground(new Color(211, 211, 211));
		setForeground(Color.WHITE);
		setVisible(true);
		add(blank, BorderLayout.WEST);
		add(dialogue_label, BorderLayout.CENTER);

	}

	// �� ���� �˸� �г� �����ϰ� �� �˸� �г� �߰��ϴ� �޼���
	static public void insert_dialogue(String string) {
		MainFrame.pack_back.remove(MainFrame.monster_attack_panel);
		MainFrame.monster_attack_panel = new MonsterAttackPanel(string);
		MainFrame.pack_back.add(MainFrame.monster_attack_panel, new Integer(1), 0);
	}

}
