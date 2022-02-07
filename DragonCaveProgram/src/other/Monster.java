package other;

import java.util.Random;

import javax.swing.JOptionPane;

import Component.DragonAttackPanel;
import Component.MonsterAttackPanel;
import Dragon.Hatchling;
import Dragon.Reptile;
import Main.MainFrame;

//����
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
		attack_speed = 1 + random.nextInt(5);
	}

	// ���� �޼���

	public void MonsterAttack(Reptile reptile) {
		// ���� ���� ���� ������
		Thread monster_attack = new Thread(new MonsterAttackThread(reptile));
		monster_attack.start();
	}

	// ������ ���Ͱ� �����ϴ� ������
	class MonsterAttackThread implements Runnable {
		Reptile target_reptile;

		public MonsterAttackThread(Reptile reptile) {
			target_reptile = reptile;
		}

		@Override
		public void run() {
			boolean is_fight = true;
			int attack_count = 0;
			while (is_fight) {
				try {
					// ���ݼӵ� �ݿ��ؼ� ����
					Thread.sleep(5000 / attack_speed);
					// ���� ü�� 0�̸� ����
					if (hp <= 0) {
						// �ο� ����
						is_fight = false;
					}
					// ������ ���ݷ¸�ŭ �巡�� ü�� �϶�
					target_reptile.hp -= attack;
					attack_count++;
					// �巡�� ü�� üũ
					// �巡���� ü�� 0 �̻�
					if (target_reptile.hp > 0) {

						MonsterAttackPanel.insert_dialogue("<html>���Ͱ� " + attack_count
								+ " ��° ������ �ؿԴ�.<br> (���� �巡�� ü��: " + target_reptile.hp + ")</html>");
					}
					// �巡�� ü�� 0�� ��
					else if (target_reptile.hp <= 0) {
						MonsterAttackPanel
								.insert_dialogue("<html>���Ͱ� " + attack_count + " ��° �����ؿԴ�.<br> (���� �巡�� ü��: 0)</html>");
						// �ο� ����
						is_fight = false;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// �˸� �г� �Ⱥ��̰��ϱ�
			MainFrame.monster_attack_panel.setVisible(false);

		}

	}
}