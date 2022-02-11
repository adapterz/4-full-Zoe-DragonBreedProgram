package GuiTodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Dragon.Egg;
import GuiRelatedClass.DragonStatus;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.Growth;

// �巡���� ����ܰ谡 '��'������ �� �ൿ �޼��带 ȣ���� �� �ִ� ��ư��� ����â ��ư�� ������ �г�
public class EggTodoList extends TodoList {
	// ������ Egg Ŭ������ �ൿ �޼��� ȣ���� ��ư ����
	private JButton decrease_degree_control;
	private JButton increase_degree_control;
	private JButton talk_to;
	// ��ȭ��� �޼����� (�� ��ư�̺�Ʈ ���� ��ȭ��� �������� üũ)
	static private boolean is_evolution = false;

	public EggTodoList() {
		super();

		// �ൿ �޼��� ȣ���� ��ư
		// EggŬ������ �˿��� ���ϱ� �޼��� ȣ���� ��ư
		talk_to = new JButton("���ϱ�");
		talk_to.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		talk_to.setBackground(SystemColor.controlHighlight);
		talk_to.setBounds(56, 134, 98, 28);
		add(talk_to);
		// �� �µ� ���� ��ư
		// Egg Ŭ������ �� �µ� ��� �޼��� ȣ���� ��ư
		increase_degree_control = new JButton("�µ� ���");
		increase_degree_control.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		increase_degree_control.setBackground(SystemColor.controlHighlight);
		increase_degree_control.setBounds(56, 94, 98, 28);
		add(increase_degree_control);
		// Egg Ŭ������ �� �µ� �϶� �޼��� ȣ���� ��ư
		decrease_degree_control = new JButton("�µ� �϶�");
		decrease_degree_control.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		decrease_degree_control.setBackground(SystemColor.controlHighlight);
		decrease_degree_control.setBounds(56, 54, 98, 28);
		add(decrease_degree_control);

		// ����â ��ư (DragonStatus �ν��Ͻ� ȣ��)
		JButton dragon_status = new JButton("����â");
		dragon_status.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		dragon_status.setBackground(SystemColor.controlHighlight);
		dragon_status.setBounds(56, 174, 98, 28);
		add(dragon_status);

		// ��ư���� �̺�Ʈ ����
		// ���ϱ� ��ư�� �̺�Ʈ
		talk_to.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.talk_to();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_egg.is_evolution();
				// ��ȭ ���� �����ϸ� ��ȭ �޼��� ȣ��
				if (is_evolution) {
					go_evolution();
				}

			}

		});
		// �� �µ� ����
		// �µ� ��� ��ư�� �̺�Ʈ
		increase_degree_control.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.increase_degree_control();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_egg.is_evolution();
				// ��ȭ���� �������� �� ��ȭ �޼��� ȣ��
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// �µ� �϶� ��ư�� �̺�Ʈ
		decrease_degree_control.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.decrease_degree_control();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_egg.is_evolution();
				// ��ȭ���� �������� �� ��ȭ �޼��� ȣ��
				if (is_evolution) {
					go_evolution();
				}
			}

		});

		// ����â ��ư �̺�Ʈ(DragonStatus Ŭ������ ������ ������â�� ����� ��ư)
		dragon_status.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new DragonStatus();

			}

		});
	}

	// ��ȭ �޼���
	// ��ȭ��� �������� �� ȣ���� �޼��� (�巡�� ����)
	// �ش� TodoList �ν��Ͻ� ����, �巡���� ����ܰ迡 �´� ���ο� TodoList �ν��Ͻ� ����
	private void go_evolution() {
		// �ش� �ν��Ͻ� �Ⱥ��̰��ϱ�
		setVisible(false);
		MainFrame.todolist_egg = null;
		// PaintManager Ŭ�������� �׷��� �巡�� ���� �ٲ��ֱ�(�巡�� ����)
		PaintManager.stage = Growth.HATCHLING;
		MainFrame.main_background.repaint();
		// �� TodoList ����(�巡���� ����ܰ� ���Ʊ� ������ �� �гη� ����)
		MainFrame.todolist_hatchling = new HatchlingTodoList();
		MainFrame.main_background.add(MainFrame.todolist_hatchling);

		remove(this);
	}
}
