package GuiTodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Dragon.Juvenile;
import GuiRelatedClass.DragonStatus;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.BackGround;
import enums.Growth;
import other.MakeFileForLog;

// �巡���� ����ܰ谡 '������'������ �� �ൿ �޼��带 ȣ���� �� �ִ� ��ư��� ����â ��ư�� ������ �г�
public class JuvenileTodoList extends TodoList {

	// ������ Juvenile Ŭ���� �޼��� ȣ���� ��ư ����
	private JButton stroke;
	private JButton flight;
	private JButton fight;
	private JComboBox feed;
	// ��ȭ��� �޼����� (�� ��ư�̺�Ʈ ���� ��ȭ��� �������� üũ)
	static private boolean is_evolution = false;

	@SuppressWarnings("unchecked")
	public JuvenileTodoList() {
		super();
		// �ൿ �޼��� ȣ���� ��ư
		// Juvenile Ŭ������ ���࿬�� �޼��� ȣ���� ��ư
		flight = new JButton("���࿬��");
		flight.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		flight.setBackground(SystemColor.controlHighlight);
		flight.setBounds(56, 134, 98, 28);
		add(flight);
		// Juvenile Ŭ������ �ο�� �޼��� ȣ���� ��ư
		fight = new JButton("��������");
		fight.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		fight.setBackground(SystemColor.controlHighlight);
		fight.setBounds(56, 94, 98, 28);
		add(fight);
		// Juvenile Ŭ������ ���ٵ�� �޼��� ȣ���� ��ư
		stroke = new JButton("���ٵ��");
		stroke.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		stroke.setBackground(SystemColor.controlHighlight);
		stroke.setBounds(56, 54, 98, 28);
		add(stroke);
		// ����â ��ư (DragonStatus �ν��Ͻ� ȣ��)
		JButton status = new JButton("����â");
		status.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		status.setBackground(SystemColor.controlHighlight);
		status.setBounds(56, 214, 98, 28);
		add(status);

		// Juvenile Ŭ������ �巡�￡�� ���ֱ� �޼��带 ȣ���� �޺��ڽ�
		// ���� ��Ҹ� �޼����� ���ڷ� �Ѱ��ֱ�
		String[] kind_of_eat = { "���̱�", "����", "���ۿ�", "��������" };
		feed = new JComboBox(kind_of_eat);
		feed.setFont(new Font("210 �ǹ���û��", Font.BOLD, 12));
		feed.setBounds(56, 174, 98, 28);
		add(feed);

		// ���ֱ� �޼��� ȣ���� �޺��ڽ� �̺�Ʈ
		feed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					Juvenile j_dragon = (Juvenile) MainFrame.dragon;

					// �޺��ڽ� ���ÿ�� ���ڷ� �Ѱ� �޼��� ȣ��
					String what_eat = feed.getSelectedItem().toString();
					j_dragon.feed(what_eat);
					// ��ȭ���� �����ߴ��� üũ
					is_evolution = j_dragon.is_evolution();
					if (is_evolution) {
						// ��ȭ���� ������ ��ȭ �޼��� ȣ��
						go_evolution();
					}
				}
			}

		});

		// ��ư �̺�Ʈ
		// ���࿬�� ��ư�� �̺�Ʈ
		flight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// �� ����϶��� �˸��г� �Ⱥ��̰��ϱ�
				MainFrame.pack_back.remove(MainFrame.dialogue_panel);
				MainFrame.pack_back.remove(MainFrame.dragon_state_panel);
				// �ش� �ν��Ͻ� �Ⱥ��̰� �ϱ� (��ư �г� �Ⱥ��̰� �ϱ�)
				setVisible(false);
				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// ���� ��� �׷��ֱ�
				PaintManager.background = BackGround.FLIGHT;
				MainFrame.main_background.repaint();

				// Juvenile Ŭ������ ���࿬�� �޼��� ȣ��
				downcast_dragon_juvenile.flying_practice();

				// �������� �� �� ����� �˸����� ���̰��ϱ�
				MainFrame.dialogue_panel.setVisible(true);

				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_juvenile.is_evolution();
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
				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// �ش� �ν��Ͻ� �Ⱥ��̰��ϱ� (��ư �г� �Ⱥ��̰��ϱ�)
				setVisible(false);
				// �ο�� ����г� �����ֱ�
				PaintManager.background = BackGround.FIGHT;
				MainFrame.main_background.repaint();

				// Juenile Ŭ������ fight �޼��� ȣ��
				downcast_dragon_juvenile.fight();

				// �ο� ���� �� �� ����� �˸����� ���̰��ϱ�
				MainFrame.dialogue_panel.setVisible(true);
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_juvenile.is_evolution();
				// ��ȭ���� �������� �� ��ȭ �޼��� ȣ��
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// ���ٵ�� ��ư�� �̺�Ʈ
		stroke.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// Juvenile Ŭ������ ���ٵ�� �޼��� ȣ��
				downcast_dragon_juvenile.stroke();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_juvenile.is_evolution();
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
	// �ش� TodoList �ν��Ͻ� ����
	// �巡���� �ʵ尪�� ���� �ٸ� ���� �����ֱ�
	private void go_evolution() {

		// �� ����� �˸����� �Ⱥ��̰� �ϱ�
		MainFrame.pack_back.remove(MainFrame.dialogue_panel);
		MainFrame.pack_back.remove(MainFrame.dragon_state_panel);
		// �ش� �ν��Ͻ� �Ⱥ��̰��ϱ�
		setVisible(false);
		MainFrame.todolist_juvenile = null;
		// PaintManager Ŭ�������� �׷��� �巡�� ���� �ٲ��ֱ�(�巡�� ����)
		PaintManager.stage = Growth.ADULT;
		MainFrame.main_background.repaint();

		// ��ȭ �˸�â
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ��� �ƾ��!");
		// �巡�� ��ü�� ȣ������ ���� �ٸ� ���� �б�ó��
		// ���ǿ���(ȣ���� 23 �ʰ�)
		if (MainFrame.dragon.likeable > 23) {
			// ���α׷� ���� �� �α� ���Ϸ� �����
			MakeFileForLog.makeFile("dragon_breeding_log", MainFrame.log_list);

			// PaintManager�� �׷��� ��� ���� ����
			PaintManager.background = BackGround.HAPPY_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ��� �Բ��ϰ� �ʹٳ׿�! ���ǿ���~");
		}
		// ���忣��(ȣ���� 7 �̸�)
		else if (MainFrame.dragon.likeable < 7) {
			// ���α׷� ���� �� �α� ���Ϸ� �����
			MakeFileForLog.makeFile("dragon_breeding_log", MainFrame.log_list);

			// PaintManager�� �׷��� ��� ���� ����
			PaintManager.background = BackGround.SAD_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� �ƹ��� ���� �������. ���忣��");
		}
		// �븻����(ȣ���� 7~23)
		else {
			// ���α׷� ���� �� �α� ���Ϸ� �����
			MakeFileForLog.makeFile("dragon_breeding_log", MainFrame.log_list);

			// PaintManager�� �׷��� ��� ���� ����
			PaintManager.background = BackGround.NORMAL_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� Ű���༭ ���� ���󱸰��� �ϰ� �ʹ��. �븻����!");
		}

		// �ش� �ν��Ͻ� ����
		remove(this);

	}
}