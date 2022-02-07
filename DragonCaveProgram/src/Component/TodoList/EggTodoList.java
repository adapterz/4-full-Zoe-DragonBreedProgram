package Component.TodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Component.DragonStatus;
import Component.PaintManager;
import Dragon.Egg;
import Main.MainFrame;
import other.Growth;

// Egg ������ �� �� �� �ִ� �� �г�
public class EggTodoList extends TodoList {
	// ������ Egg Ŭ���� �޼��� ȣ���� ��ư ����
	private JButton decrease_degree_control;
	private JButton increase_degree_control;
	private JButton talk_to;
	static private boolean is_evolution = false;

	public EggTodoList() {

		// �θ� ������ ȣ��
		super();

		// ���� ��ư
		// ���ϱ� ��ư
		talk_to = new JButton("���ϱ�");
		talk_to.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		talk_to.setBackground(SystemColor.controlHighlight);
		talk_to.setBounds(56, 134, 98, 28);
		add(talk_to);
		// �µ� ���� ��ư
		// �µ� ���
		increase_degree_control = new JButton("�µ� ���");
		increase_degree_control.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		increase_degree_control.setBackground(SystemColor.controlHighlight);
		increase_degree_control.setBounds(56, 94, 98, 28);
		add(increase_degree_control);
		// �µ��϶�
		decrease_degree_control = new JButton("�µ� �϶�");
		decrease_degree_control.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		decrease_degree_control.setBackground(SystemColor.controlHighlight);
		decrease_degree_control.setBounds(56, 54, 98, 28);
		add(decrease_degree_control);

		// ����â ��ư
		JButton dragon_status = new JButton("����â");
		dragon_status.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		dragon_status.setBackground(SystemColor.controlHighlight);
		dragon_status.setBounds(56, 174, 98, 28);
		add(dragon_status);

		// ��ư �̺�Ʈ ����
		// ���ϱ�
		talk_to.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.talk_to();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_egg.is_evolution();
				if (is_evolution) {
					// ��ȭ ���� �����ϸ� ��ȭ �޼��� ȣ��
					go_evolution();
				}

			}

		});
		// �µ� ����
		// �µ� ���
		increase_degree_control.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.increase_degree_control();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_egg.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// �µ� �϶�
		decrease_degree_control.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.decrease_degree_control();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_egg.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});

		// ����â
		dragon_status.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DragonStatus();

			}

		});
	}

	// ��ȭ �޼���
	private void go_evolution() {
		// �� Ŭ������ ���� ��� �Ⱥ��̰��ϱ�
		setVisible(false);
		MainFrame.todolist_egg = null;
		// �׷��� �巡�� ���� �ٲ��ֱ�(�巡�� ����)
		PaintManager.stage = Growth.HATCHLING;
		MainFrame.main_background.repaint();
		// �� ���� ��� ����(������ ������ �� �� �� �ִ� �� ������� ����)
		MainFrame.todolist_hatchling = new HatchlingTodoList();
		MainFrame.main_background.add(MainFrame.todolist_hatchling);

		remove(this);
	}
}
