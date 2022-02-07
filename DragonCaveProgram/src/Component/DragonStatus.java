package Component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Dragon.Juvenile;
import Dragon.Reptile;
import Main.MainFrame;
import other.Growth;

// dragon ��ü�� �ʵ尪���� ������ ����â(�� ������)
public class DragonStatus extends JFrame {

	public DragonStatus() {
		// �⺻ ������ ����
		setTitle("Status");
		setSize(300, 500);
		setForeground(Color.LIGHT_GRAY);
		setResizable(false);
		setVisible(true);
		setLayout(null);
		setLocation(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// �⺻ �ۼ� ����
		JLabel StatusLabel = new JLabel("Status");
		StatusLabel.setBounds(120, 10, 49, 25);
		StatusLabel.setSize(150, 50);
		StatusLabel.setFont(new Font("Bernard MT Condensed", Font.BOLD, 20));
		add(StatusLabel);
		JLabel DragonName = new JLabel("�̸� :  " + MainFrame.dragon.name);
		DragonName.setFont(new Font("210 �ǹ���û�� L", Font.PLAIN, 14));
		DragonName.setBounds(12, 45, 137, 15);
		add(DragonName);

		JLabel DragonLikeable = new JLabel("ȣ���� :  " + MainFrame.dragon.likeable);
		DragonLikeable.setFont(new Font("210 �ǹ���û�� L", Font.PLAIN, 14));
		DragonLikeable.setBounds(12, 70, 137, 15);
		add(DragonLikeable);

		JLabel DragonGauge = new JLabel("��ȭ������ :  " + MainFrame.dragon.evolution);
		DragonGauge.setFont(new Font("210 �ǹ���û�� L", Font.PLAIN, 14));
		DragonGauge.setBounds(12, 95, 137, 15);
		add(DragonGauge);

		// Ư�� Ŭ������ ������ ���� �߰�(�б�ó��)
		if ((PaintManager.stage == Growth.HATCHLING) || (PaintManager.stage == Growth.JUVENILE)) {
			Reptile downcast_dragon_reptile = (Reptile) MainFrame.dragon;
			JLabel ReptileFull = new JLabel("������ :  " + downcast_dragon_reptile.full);
			ReptileFull.setFont(new Font("210 �ǹ���û�� L", Font.PLAIN, 14));
			ReptileFull.setBounds(12, 120, 137, 15);
			add(ReptileFull);

			JLabel ReptileAttack = new JLabel("���ݷ� :  " + downcast_dragon_reptile.attack);
			ReptileAttack.setFont(new Font("210 �ǹ���û�� L", Font.PLAIN, 14));
			ReptileAttack.setBounds(12, 145, 137, 15);
			add(ReptileAttack);

			JLabel ReptileHP = new JLabel("ü�� :  " + downcast_dragon_reptile.hp);
			ReptileHP.setFont(new Font("210 �ǹ���û�� L", Font.PLAIN, 14));
			ReptileHP.setBounds(12, 170, 137, 15);
			add(ReptileHP);

			JLabel ReptileGender = new JLabel("���� :  " + downcast_dragon_reptile.gender);
			ReptileGender.setFont(new Font("210 �ǹ���û�� L", Font.PLAIN, 14));
			ReptileGender.setBounds(12, 195, 137, 15);
			add(ReptileGender);
		}
		if (PaintManager.stage == Growth.JUVENILE) {
			Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
			JLabel FlightProficiency = new JLabel("���� ���õ� :  " + downcast_dragon_juvenile.flight_proficiency);
			FlightProficiency.setFont(new Font("210 �ǹ���û�� L", Font.PLAIN, 14));
			FlightProficiency.setBounds(12, 220, 137, 15);
			add(FlightProficiency);
		}

	}

}