package GuiTodoList;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;

abstract public class TodoList extends JPanel {

	private JLabel TODO;

	public TodoList() {
		// ���ϸ��
		setBackground(SystemColor.inactiveCaption);
		setBounds(1055, 0, 213, 270);
		setLayout(null);
		setVisible(true);
		// ���ϸ�� �󺧺��̱�
		TODO = new JLabel("TO DO");
		TODO.setBounds(76, 12, 66, 30);
		TODO.setFont(new Font("Dialog", Font.BOLD, 21));
		add(TODO);
	}

}
