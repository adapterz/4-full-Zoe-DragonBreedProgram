package GuiRelatedClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;

// �巡�� ��ü�� �ൿ �޼��带 ȣ������ִ� �̺�Ʈ ��ư�� ������ �� �˸� ������ ������ �г�
public class DialoguePanel extends JPanel {
	// �˸����� �ۼ��� ��
	private JLabel dialogue_label;
	// ����
	private JLabel blank;

	public DialoguePanel(String string) {

		// ���� ��
		blank = new JLabel("       ");
		// ���ڷ� �޾ƿ� string ���� ������ ��
		dialogue_label = new JLabel(string);
		dialogue_label.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 20));
		dialogue_label.setVisible(true);
		// �ش� Ŭ���� �⺻ ����

		setLayout(new BorderLayout());
		setBackground(new Color(250, 128, 114));
		setForeground(Color.WHITE);
		setBounds(230, 545, 820, 150);
		setVisible(true);
		add(blank, BorderLayout.WEST);
		add(dialogue_label, BorderLayout.CENTER);

	}

	// �� ���� �˸� �г� �����ϰ� �� �˸� �г� �߰��ϴ� �޼���
	static public void insert_dialogue(String string) {
		MainFrame.pack_back.remove(MainFrame.dialogue_panel);
		MainFrame.dialogue_panel = new DialoguePanel(string);
		MainFrame.pack_back.add(MainFrame.dialogue_panel, new Integer(1), 0);
	}

}
