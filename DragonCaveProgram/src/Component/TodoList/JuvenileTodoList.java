package Component.TodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Component.DragonStatus;
import Component.PaintManager;
import Dragon.Juvenile;
import Main.MainFrame;
import other.BackGround;
import other.Growth;
// Juvenile ������ �� �� �� �ִ� �� �г�
public class JuvenileTodoList extends TodoList {
	
	// ������ Juvenile Ŭ���� �޼��� ȣ���� ��ư ����
	private JButton stroke;
	private JButton flight;
	private JButton fight;
	private JComboBox feed;
	static private boolean is_evolution = false;

	@SuppressWarnings("unchecked")
	public JuvenileTodoList() {
		super();
		// ���Ϲ�ư �����
		// ���࿬��
		flight = new JButton("���࿬��");
		flight.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		flight.setBackground(SystemColor.controlHighlight);
		flight.setBounds(56, 134, 98, 28);
		add(flight);
		// ����
		fight = new JButton("��������");
		fight.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		fight.setBackground(SystemColor.controlHighlight);
		fight.setBounds(56, 94, 98, 28);
		add(fight);
		// ���ٵ��
		stroke = new JButton("���ٵ��");
		stroke.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		stroke.setBackground(SystemColor.controlHighlight);
		stroke.setBounds(56, 54, 98, 28);
		add(stroke);
		// ����â
		JButton status = new JButton("����â");
		status.setFont(new Font("210 �ǹ���û�� L", Font.BOLD, 12));
		status.setBackground(SystemColor.controlHighlight);
		status.setBounds(56, 214, 98, 28);
		add(status);

		// ���̱�
		String[] kind_of_eat = { "���̱�", "����", "���ۿ�", "��������" };
		feed = new JComboBox(kind_of_eat);
		feed.setFont(new Font("210 �ǹ���û��", Font.BOLD, 12));
		feed.setBounds(56, 174, 98, 28);
		add(feed);

		// �޺��ڽ� �̺�Ʈ
		feed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					Juvenile j_dragon = (Juvenile) MainFrame.dragon;

					// �޺��ڽ����� ������ �׸� String���� ��������
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
		// ���࿬��
		flight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// �˸����� �Ⱥ��̰��ϱ�
				MainFrame.pack_back.remove(MainFrame.dialogue_panel);
				// ���ϸ�� �Ⱥ��̰� �ϱ�
				setVisible(false);
				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// ����г��� ���� ��� �׷��ְ� �ϱ�
				PaintManager.background = BackGround.FLIGHT;
				MainFrame.main_background.repaint();
				
				downcast_dragon_juvenile.flying_practic();

				// �˸����� ���̰��ϱ�
				MainFrame.dialogue_panel.setVisible(true);

				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_juvenile.is_evolution();
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

				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// ���ϸ�� �Ⱥ��̰� �ϱ�
				setVisible(false);
				// ��� �г��� ���� ��� �׷��ְ� �ϱ�
				PaintManager.background = BackGround.FIGHT;
				MainFrame.main_background.repaint();
				
				downcast_dragon_juvenile.fight();

				// ���ϸ�� ���̰� �ϱ�
				// setVisible(true);
				// �˸����� ���̰��ϱ�
				MainFrame.dialogue_panel.setVisible(true);
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_juvenile.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// ���ٵ��
		stroke.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				downcast_dragon_juvenile.stroke();
				// ��ȭ���� �����ߴ��� üũ
				is_evolution = downcast_dragon_juvenile.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// ����â
		status.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// ����â �� ������� ����ֱ�
				new DragonStatus();

			}

		});
	}

	private void go_evolution() {

		// �˸����� �Ⱥ��̰��ϱ�
		MainFrame.pack_back.remove(MainFrame.dialogue_panel);
		// �� Ŭ������ ���� ��� �Ⱥ��̰��ϱ�
		setVisible(false);
		MainFrame.todolist_juvenile = null;
		// � �巡�� �׷��ֱ�
		PaintManager.stage = Growth.ADULT;
		MainFrame.main_background.repaint();

		// ��ȭ �˸�â
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ��� �ƾ��!");
		// �巡�� ��ü�� ȣ������ ���� �ٸ� ���� �б�ó��
		// ���ǿ���
		if (MainFrame.dragon.likeable > 23) {
			PaintManager.background = BackGround.HAPPY_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ��� �Բ��ϰ� �ʹٳ׿�! ���ǿ���~");
		}
		// ���忣��
		else if (MainFrame.dragon.likeable < 7) {
			PaintManager.background = BackGround.SAD_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� �ƹ��� ���� �������. ���忣��");
		}
		// �븻����
		else {
			PaintManager.background = BackGround.NORMAL_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� Ű���༭ ���� ���󱸰��� �ϰ� �ʹ��. �븻����!");
		}

		remove(this);

	}
}