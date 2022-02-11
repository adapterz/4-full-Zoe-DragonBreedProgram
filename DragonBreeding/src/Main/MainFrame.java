package Main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dragon.Dragon;
import Dragon.Egg;
import GuiRelatedClass.DialoguePanel;
import GuiRelatedClass.DragonAttackPanel;
import GuiRelatedClass.DragonStatePanel;
import GuiRelatedClass.MonsterAttackPanel;
import GuiRelatedClass.PackBackGround;
import GuiRelatedClass.PaintManager;
import enums.BackGround;
import enums.Growth;
import other.Home;

public class MainFrame {

	// �巡��
	// ������ �̿��� ����
	public static Dragon dragon;
	// ȯ�� ����
	public static Home home;
	// ���������� �� ����г�
	public static JFrame main_frame;
	public static PaintManager main_background;
	public static DialoguePanel dialogue_panel;
	public static DragonStatePanel dragon_state_panel;
	public static DragonAttackPanel dragon_attack_panel;
	public static MonsterAttackPanel monster_attack_panel;
	// ���������Ʈ�� �ϳ��� ���� �����̳�
	public static PackBackGround pack_back;
	// ���� �޴�â
	public static GuiTodoList.EggTodoList todolist_egg;
	public static GuiTodoList.HatchlingTodoList todolist_hatchling;
	public static GuiTodoList.JuvenileTodoList todolist_juvenile;

	// �α׷� ���� ArrayList
	public static ArrayList<String> log_list = new ArrayList<String>();

	// ���α׷� ����
	public static void main(String[] args) {
		// ���� ������
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.main_frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	// @SuppressWarnings("removal")
	private void initialize() {
		// ����������
		main_frame = new JFrame();
		// �����̳� ����
		pack_back = new PackBackGround();
		main_frame.add(pack_back);
		// ��� �г�
		main_background = new PaintManager();
		main_background.setVisible(true);
		main_background.setLayout(null);
		// ��ȭ �г�
		dialogue_panel = new DialoguePanel("");
		dialogue_panel.setVisible(false);

		// �ο�˸� �г�
		dragon_attack_panel = new DragonAttackPanel("");
		dragon_attack_panel.setVisible(false);
		monster_attack_panel = new MonsterAttackPanel("");
		monster_attack_panel.setVisible(false);

		// �巡�� ���� �г�
		dragon_state_panel = new DragonStatePanel("");
		dragon_state_panel.setVisible(false);

		// ���ϸ�� �г�
		todolist_egg = new GuiTodoList.EggTodoList();
		todolist_egg.setVisible(false);
		main_background.add(todolist_egg);

		// ���������� ����
		main_frame.setResizable(false);
		main_frame.setTitle("Dragon Breeding program");
		main_frame.setBounds(100, 100, 1280, 750);
		main_frame.setLocationRelativeTo(null);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// �����̳ʿ� ������Ʈ �߰� �� ���� ����
		pack_back.add(main_background, new Integer(0), 0);
		pack_back.add(dialogue_panel, new Integer(1), 0);
		pack_back.add(dragon_attack_panel, new Integer(1), 0);
		pack_back.add(monster_attack_panel, new Integer(1), 0);
		pack_back.add(dragon_state_panel, new Integer(1), 0);

		main_frame.pack();

		// KeyListner �̺�Ʈ ����
		main_frame.addKeyListener(new KeyListner());
		// �ʱ�ȭ
		dragon = null;
		home = null;

	}
}

// frame�� ������ Ű �̺�Ʈ
class KeyListner extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		// esc ������ ����
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		// �ε�â�϶� ���� ������ ���� �ܰ�� �Ѿ��
		case KeyEvent.VK_ENTER:
			if (PaintManager.background == BackGround.LOADING) {

				// �巡�� �̸����� â
				new SetName();
				// Egg ����
				MainFrame.dragon = new Egg();
				// �׸���� Ŭ������ �� �׷��ְ� �ϱ�
				PaintManager.stage = Growth.EGG;
			}
			// ���� ���� â�� �� ���� ������ ���� ����
			if (PaintManager.background == BackGround.GUIDE) {
				// �ΰ��� ȭ������ �ٲ��ֱ�
				PaintManager.background = BackGround.HOME;
				MainFrame.home = new Home();
				MainFrame.todolist_egg.setVisible(true);
				DragonStatePanel.insert_dialogue("<html>�巡�� ���̿���.<br> �����ϰ� �����ּ���!</html>");
				MainFrame.main_background.repaint();
			}
			break;
		}

	}

// �ε�â->���� ����ȭ�� �Ѿ �� ȣ���� �̸��Է� â 
	class SetName extends JFrame {

		// ������
		public SetName() {
			// �⺻ ������ ����
			setVisible(true);
			setTitle("Set Dragon's Name");
			setResizable(false);
			setSize(300, 150);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			// �巡�� �̸�: �ؽ�Ʈ �ʵ� |�Է¹�ư|
			JPanel name_panel = new JPanel();
			JLabel name_label = new JLabel("�巡�� �̸�:");
			JTextField name_field = new JTextField(10);
			JButton name_input = new JButton("�Է�");

			// �Է¹�ư ������ �� �̺�Ʈ
			name_input.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// �巡�� �̸� = �ؽ�Ʈ �ʵ忡�� �Է��� �̸�
					MainFrame.dragon.name = name_field.getText().toString();

					// ���� ���� ȭ�� �����ֱ�
					PaintManager.background = BackGround.GUIDE;
					MainFrame.main_background.repaint();
					// â ����
					dispose();
				}

			});

			name_panel.add(name_label);
			name_panel.add(name_field);
			name_panel.add(name_input);
			add(name_panel);
		}

	}
}