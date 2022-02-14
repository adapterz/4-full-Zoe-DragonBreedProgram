package GuiTodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import Dragon.Hatchling;
import GuiRelatedClass.DragonStatus;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.BackGround;
import enums.Growth;

// �巡���� ����ܰ谡 '���Ʊ�'������ �� �ൿ �޼��带 ȣ���� �� �ִ� ��ư��� ����â ��ư�� ������ �г�
public class HatchlingTodoList extends TodoList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9188690795585243354L;
	// ������ Hatchling Ŭ���� �ൿ �޼��� ȣ���� ��ư ����
	private JButton wash;
	private JButton stroke;
	private JButton fight;
	private JComboBox feed;
	// ��ȭ��� �޼����� (�� ��ư�̺�Ʈ ���� ��ȭ��� �������� üũ)
	static private boolean is_evolution = false;

	@SuppressWarnings("unchecked")
	public HatchlingTodoList() {
		super();

		// �ൿ �޼��� ȣ���� ��ư
		// Hatchling Ŭ������ ���ٵ�� �޼��� ȣ���� ��ư
		stroke = new JButton("���ٵ��");
		stroke.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		stroke.setBackground(SystemColor.controlHighlight);
		stroke.setBounds(56, 134, 98, 28);
		add(stroke);
		// Hatchling Ŭ������ �ο�� �޼��� ȣ���� ��ư
		fight = new JButton("��������");
		fight.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		fight.setBackground(SystemColor.controlHighlight);
		fight.setBounds(56, 94, 98, 28);
		add(fight);
		// Hatchlign Ŭ������ �ı�� �޼��� ȣ���� ��ư
		wash = new JButton("�ı��");
		wash.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		wash.setBackground(SystemColor.controlHighlight);
		wash.setBounds(56, 54, 98, 28);
		add(wash);
		// ����â ��ư (DragonStatus �ν��Ͻ� ȣ��)
		JButton status = new JButton("����â");
		status.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		status.setBackground(SystemColor.controlHighlight);
		status.setBounds(56, 214, 98, 28);
		add(status);

		// Hathcling Ŭ������ �巡�￡�� ���ֱ� �޼��带 ȣ���� �޺��ڽ�
		// ���� ��Ҹ� �޼����� ���ڷ� �Ѱ��ֱ�
		String[] kind_of_eat = { "���̱�", "����", "�Ǵн���", "�ͶѶ��" };
		feed = new JComboBox(kind_of_eat);
		feed.setFont(new Font("210 �ǹ���û��", Font.BOLD, 12));
		feed.setBounds(56, 174, 98, 28);
		add(feed);

		// ���ֱ� �޼��� ȣ���� �޺��ڽ� �̺�Ʈ
		feed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
					// �޺��ڽ� ���ÿ�� ���ڷ� �Ѱ�  Hatchling Ŭ������ feed �޼��� ȣ��
					String what_eat = feed.getSelectedItem().toString();
					downcast_dragon_hatchling.feed(what_eat);
					// ��ȭ���� �����ߴ��� üũ
					is_evolution = downcast_dragon_hatchling.is_evolution();
					// ��ȭ���� ���������� ��ȭ �޼��� ȣ��
					if (is_evolution) {
						go_evolution();
					}
				}
			}

		});

		// ��ư �̺�Ʈ
		// ���ٵ�� ��ư�� �̺�Ʈ
		stroke.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// Hatchling Ŭ������ ���ٵ�� �޼��� ȣ��
				downcast_dragon_hatchling.stroke();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_hatchling.is_evolution();
				// ��ȭ���� �������� �� ��ȭ �޼��� ȣ��
				if (is_evolution) {
					go_evolution();
				}

			}

		});
		// �ο�� ��ư�� �̺�Ʈ
		fight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// �� ����϶��� �˸��г� �Ⱥ��̰��ϱ�
				MainFrame.pack_back.remove(MainFrame.dialogue_panel);
				MainFrame.pack_back.remove(MainFrame.dragon_state_panel);
				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// �ش� �ν��Ͻ� �Ⱥ��̰��ϱ� (��ư �г� �Ⱥ��̰��ϱ�)
				setVisible(false);
				// �ο�� ����г� �����ֱ�
				PaintManager.background = BackGround.FIGHT;
				MainFrame.main_background.repaint();

				//  Hatchling Ŭ������ �ο�� �޼��� ȣ��
				downcast_dragon_hatchling.fight();

				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_hatchling.is_evolution();
				// ��ȭ���� �������� �� ��ȭ �޼��� ȣ��
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// �ı�� ��ư�� �̺�Ʈ
		wash.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				//  Hatchling Ŭ������ �ı�� �޼��� ȣ��
				downcast_dragon_hatchling.wash();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_hatchling.is_evolution();
				// ��ȭ���� �������� �� ��ȭ �޼��� ȣ��
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// ����â ��ư �̺�Ʈ (DragonStatus Ŭ������ ������ ������â�� ����� ��ư)
		status.addActionListener(new ActionListener() {

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
		MainFrame.todolist_hatchling = null;
		// PaintManager Ŭ�������� �׷��� �巡�� ���� �ٲ��ֱ�(�巡�� ����)
		PaintManager.stage = Growth.JUVENILE;
		MainFrame.main_background.repaint();
		// �� TodoList ����(�巡���� ����ܰ� ����� ������ �� �гη� ����)
		MainFrame.todolist_juvenile = new JuvenileTodoList();
		MainFrame.main_background.add(MainFrame.todolist_juvenile);

		remove(this);
	}
}