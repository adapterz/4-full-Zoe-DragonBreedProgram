package other;

import java.util.Random;

import javax.swing.JOptionPane;

import Dragon.Hatchling;
import Dragon.Reptile;
import GuiRelatedClass.DragonAttackPanel;
import GuiRelatedClass.MonsterAttackPanel;
import Main.MainFrame;

// �巡���� fight �޼��带 ȣ���� �� �ο� ����� �� ���� Ŭ���� 
public class Monster {
	// ü��
	public int hp;
	// ���ݷ�
	public int attack;
	// ���ݼӵ�
	public int attack_speed;

	public Monster() {
		Random random = new Random();
		// ü�°� ���ݷ� �����ϰ� ����
		hp = 100 + random.nextInt(30);
		attack = 20 + random.nextInt(30);
		attack_speed = 3 + random.nextInt(5);
	}

	// Reptile Ŭ������ fight �޼��忡 ȣ������ �޼���
	// ���Ͱ� �巡���� �����ϴ� �����带 ȣ�����ݴϴ�.
	public void MonsterAttack(Reptile reptile) {
		// ���Ͱ� �巡�� �����ϴ� ������
		// ���ڷ� �ο� ����� �� �巡�� ��ü�� �Ѱ��ݴϴ�.
		Thread monster_attack = new Thread(new MonsterAttackThread(reptile));
		monster_attack.start();
	}

	// ���Ͱ� �巡�� �����ϴ� ������ ����
	class MonsterAttackThread implements Runnable {
		// �ο� ��� �巡��
		Reptile target_reptile;

		public MonsterAttackThread(Reptile reptile) {
			target_reptile = reptile;

			// ���ڰ� ����� �Ѿ�Դ��� �α׷� ����� 
			MainFrame.log_list
					.add("Monster Ŭ����_MonsterAttackThread ������ȣ��_���ڰ� üũ, target_reptile.hp: " + target_reptile.hp);
			MainFrame.log_list.add(
					"Monster Ŭ����_MonsterAttackThread ������ȣ��_���ڰ� üũ, target_reptile.attack: " + target_reptile.attack);
			MainFrame.log_list.add("Monster Ŭ����_MonsterAttackThread ������ȣ��_���ڰ� üũ, target_reptile.attack_speed: "
					+ target_reptile.attack_speed);
		}

		@Override
		public void run() {
			// �ο� ���� ���� boolean
			boolean is_fight = true;
			// ����Ƚ��
			int attack_count = 0;
			// is_fight�� false�� �Ǹ� �ο� ����
			while (is_fight) {
				try {
					// ������ ���ݼӵ� �ݿ��ؼ� ���ݰ��� ������
					Thread.sleep(5000 / attack_speed);
					// ���� ü�� 0�� �� (�巡���� �¸�, �ش� ������ ����)
					if (hp <= 0) {
						is_fight = false;
					}
					// ������ ���ݷ¸�ŭ �巡�� ü�� �϶�
					target_reptile.hp -= attack;
					attack_count++;
					// �巡�� ü�� üũ
					// �巡���� ü�� 0 �̻�
					if (target_reptile.hp > 0) {
						// ����-> �巡�� ���� �˸�����
						MonsterAttackPanel
								.insert_dialogue("<html>���Ͱ� " + attack_count + " ��° ������ �ؿԴ�.<br> (���� �巡�� ü��: "
										+ target_reptile.hp + ")<br>���ݷ�:" + attack + "</html>");
					}
					// �巡�� ü�� 0�� �� (�巡���� �й�)
					else if (target_reptile.hp <= 0) {
						MonsterAttackPanel.insert_dialogue("<html>���Ͱ� " + attack_count
								+ " ��° �����ؿԴ�.<br> (���� �巡�� ü��: 0)<br>���ݷ�:" + attack + "</html>");
						// ���� ü���� 0 �̻��̰� �巡�� ü�� 0 �����̸� �巡���� �й�
						if (hp > 0) {
							// �ο� ����
							JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� �й��߽��ϴ�.");
							System.exit(0);
							is_fight = false;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// �ο�� �޼��� ���� �˸� �г� �Ⱥ��̰��ϱ�
			MainFrame.monster_attack_panel.setVisible(false);

		}

	}
}