package Main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Component.DialoguePanel;
import Component.DragonAttackPanel;
import Component.MonsterAttackPanel;
import Component.PackBackGround;
import Component.PaintManager;
import Component.TodoList.EggTodoList;
import Component.TodoList.HatchlingTodoList;
import Component.TodoList.JuvenileTodoList;
import Dragon.Dragon;
import Dragon.Egg;
import other.BackGround;
import other.Growth;
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
	public static DragonAttackPanel dragon_attack_panel;
	public static MonsterAttackPanel monster_attack_panel;
	// ���������Ʈ�� �ϳ��� ���� �����̳�
	public static PackBackGround pack_back;
	// ���� �޴�â
	public static EggTodoList todolist_egg;
	public static HatchlingTodoList todolist_hatchling;
	public static JuvenileTodoList todolist_juvenile;

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
	
		// ���ϸ�� �г�
		todolist_egg = new EggTodoList();
		todolist_egg.setVisible(false);
		main_background.add(todolist_egg);

		// ���������� ����
		main_frame.setResizable(false);
		main_frame.setTitle("Dragon Cave - fan program");
		main_frame.setBounds(100, 100, 1280, 750);
		main_frame.setLocationRelativeTo(null);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// �����̳ʿ� ������Ʈ �߰� �� ���� ����
		pack_back.add(main_background, new Integer(0), 0);
		pack_back.add(dialogue_panel, new Integer(1), 0);
		pack_back.add(dragon_attack_panel, new Integer(1), 0);

		main_frame.pack();

		// KeyListner �̺�Ʈ ����
		main_frame.addKeyListener(new KeyListner());
		// �ʱ�ȭ
		dragon = null;
		home = new Home();
		
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
		// �ε� ����� ���� Ű �̺�Ʈ �߻�
		case KeyEvent.VK_ENTER:
			if (PaintManager.background == BackGround.LOADING) {

				// �̸����� â
				new SetName();
				// dragon ������ Egg �ν��Ͻ� ����Ű�� �ϱ�
				MainFrame.dragon = new Egg();
				// �� �׸� �׷��ְ� �ϱ�
				PaintManager.stage = Growth.EGG;
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
			setSize(300, 150);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			// �ǳ�(��+�ؽ�Ʈ�ʵ�)
			JPanel name_panel = new JPanel();
			JLabel name_label = new JLabel("�巡�� �̸�:");
			JTextField name_field = new JTextField(10);
			JButton name_input = new JButton("�Է�");

			name_input.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// �ؽ�Ʈ �ʵ忡�� �Է��� �̸� �����ͼ� dragon ������ ����Ű�� �ν��Ͻ��� name �ʵ忡 �Ҵ�
					MainFrame.dragon.name = name_field.getText().toString();
					// dragon_main.change_status();

					// �׷��� ���� �ٲٰ� repaint().
					PaintManager.background = BackGround.HOME;
					MainFrame.main_background.repaint();
					MainFrame.todolist_egg.setVisible(true);
					// �� ȯ��
					MainFrame.home = new Home();
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