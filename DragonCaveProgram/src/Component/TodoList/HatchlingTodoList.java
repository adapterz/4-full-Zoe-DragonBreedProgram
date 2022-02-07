package Component.TodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import Component.DragonStatus;
import Component.PaintManager;
import Dragon.Hatchling;
import Main.MainFrame;
import other.BackGround;
import other.Growth;
// Hatchling ������ �� �� �� �ִ� �� �г�
public class HatchlingTodoList extends TodoList {

	// ������ Hatchling Ŭ���� �޼��� ȣ���� ��ư ����
	private JButton wash;
	private JButton stroke;
	private JButton fight;
	private JComboBox feed;
	static private boolean is_evolution = false;

	@SuppressWarnings("unchecked")
	public HatchlingTodoList() {
		super();

		// ���Ϲ�ư �����
		// ���ٵ��
		stroke = new JButton("���ٵ��");
		stroke.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		stroke.setBackground(SystemColor.controlHighlight);
		stroke.setBounds(56, 134, 98, 28);
		add(stroke);
		// ����
		fight = new JButton("��������");
		fight.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		fight.setBackground(SystemColor.controlHighlight);
		fight.setBounds(56, 94, 98, 28);
		add(fight);
		// �ı��
		wash = new JButton("�ı��");
		wash.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		wash.setBackground(SystemColor.controlHighlight);
		wash.setBounds(56, 54, 98, 28);
		add(wash);
		// ����â
		JButton status = new JButton("����â");
		status.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		status.setBackground(SystemColor.controlHighlight);
		status.setBounds(56, 214, 98, 28);
		add(status);

		// ���̱�
		String[] kind_of_eat = { "���̱�", "����", "�Ǵн���", "�ͶѶ��" };
		feed = new JComboBox(kind_of_eat);
		feed.setFont(new Font("210 �ǹ���û��", Font.BOLD, 12));
		feed.setBounds(56, 174, 98, 28);
		add(feed);

		// �޺��ڽ� �̺�Ʈ
		feed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// ������忡�� �����ϸ� 2�� �̺�Ʈ ���޵�->
				// �Ʒ����� �ϸ� �����ϱ� ���� ���ǹ�(���õǴ� ��츸 �̺�Ʈ ó��)
				if (e.getStateChange() == 1) {
					Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
					String what_eat = feed.getSelectedItem().toString();
					downcast_dragon_hatchling.feed(what_eat);
					// ��ȭ���� �����ߴ��� üũ
					is_evolution = downcast_dragon_hatchling.is_evolution();
					if (is_evolution) {
						// ��ȭ���� ���������� ��ȭ �޼��� ȣ�� 
						go_evolution();
					}
				}
			}

		});

		// ��ư �̺�Ʈ
		// ���ٵ��
		stroke.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// ���ٵ�� �Լ� ȣ��
				downcast_dragon_hatchling.stroke();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_hatchling.is_evolution();
				if (is_evolution) {
					go_evolution();
				}

			}

		});
		// �ο��
		fight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// �˸����� �Ⱥ��̰��ϱ�
				MainFrame.pack_back.remove(MainFrame.dialogue_panel);
				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// ���ϸ�� �Ⱥ��̰��ϱ�
				setVisible(false);
				// ����г��� ���� �ٲ㼭 �ٽ� �׷��ֱ�
				PaintManager.background = BackGround.FIGHT;
				MainFrame.main_background.repaint();

				// �ο��Լ� ȣ��
				downcast_dragon_hatchling.fight();

				// ���ϸ�� ���̰��ϱ�
				// setVisible(true);
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_hatchling.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// �ı��
		wash.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// �ı���Լ� ȣ��
				downcast_dragon_hatchling.wash();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_hatchling.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// ����â
		status.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DragonStatus();

			}

		});
	}

	private void go_evolution() {
		// �� Ŭ������ ���� ��� �Ⱥ��̰��ϱ�
		setVisible(false);
		MainFrame.todolist_hatchling = null;
		// �׷��� �巡�� ���� �ٲ��ֱ�(�巡�� ����)
		PaintManager.stage = Growth.JUVENILE;
		MainFrame.main_background.repaint();
		// �� ���� ��� ����(������� ������ �� �� �� �ִ� �� ������� ����)
		MainFrame.todolist_juvenile = new JuvenileTodoList();
		MainFrame.main_background.add(MainFrame.todolist_juvenile);

		remove(this);
	}
}