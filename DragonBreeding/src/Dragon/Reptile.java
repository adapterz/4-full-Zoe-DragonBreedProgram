package Dragon;

import javax.swing.JOptionPane;

import GuiRelatedClass.DialoguePanel;
import GuiRelatedClass.DragonAttackPanel;
import GuiRelatedClass.DragonStatePanel;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.BackGround;
import enums.Gender;
import enums.Growth;
import other.MakeFileForLog;
import other.Monster;

/* 	�ֻ����θ�				Dragon (�߻�)
 * 	1����� 		Egg ��   		Reptile (�����,�߻�)
 *  2����� 				  Hatchling(����), Juvenile(�����)
 *  
 *  �巡�� ����ܰ�: Egg -> Hatchling -> Juvenile -> Adult
	�������: Adult Ŭ������ ���� �������� ����
 */

// �巡���� �˿��� ��ȭ�� ���� ����� ������ ���� ������ �߻�Ŭ����
abstract public class Reptile extends Dragon {
// �ʵ�
	// ������
	public int full;
	// ���ݷ�
	public int attack;
	// ���ݼӵ�
	public int attack_speed;
	// ü��
	public int hp;
	// ����
	// enum���� ���� ����
	public Gender gender;

// �ൿ�޼���
	// �巡���� ���ٵ��
	public void stroke() {
		// �˸�����
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)�� ���ٵ������� �����ϳ׿�! (ȣ����+2, ��ȭ������+1, ������-1)");

		// �ൿ�� ���� �巡�� ���°� ����
		Reptile downcast_reptile = (Reptile) MainFrame.dragon;
		downcast_reptile.likeable += 2;
		downcast_reptile.evolution++;
		downcast_reptile.full--;
		MainFrame.dragon = downcast_reptile;
	}

	// �巡���� �ı��
	public void wash() {
		// �˸�����
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)�� �İ�����!(ȣ����+1, ��ȭ������+2, ������-2)");

		// �ൿ�� ���� �巡�� ���°� ����
		Reptile downcast_reptile = (Reptile) MainFrame.dragon;
		downcast_reptile.likeable++;
		downcast_reptile.evolution += 2;
		downcast_reptile.full -= 2;
		MainFrame.dragon = downcast_reptile;

	}

	// �巡�￡�� ���ֱ�(�������̵�)
	// �޺��ڽ� ���� ��Ҹ� ���ڷ� �޾ƿͼ� �б�ó��
	abstract public void feed(String what_eat);

	// ����(Ŭ����)�� �ο��
	// ���Ͱ� �巡���� �����ϴ� ������� �巡���� ���͸� �����ϴ� ������ ���� ȣ�� (�� ��ü�� ���ݼӵ��� ���� ���ݰ��� ������)
	// �� ���ݽ� ���ݷ¸�ŭ ü�� ����
	public void fight() {
		// ���� ����ħ (�ش� �޼����� ��������)
		Monster monster = new Monster();
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� �߻��� ���͸� ������.");

		// ���Ͱ� �巡���� �����ϴ� ������ ȣ���ϴ� �޼��� ȣ�� (���ݴ�� ���ڷ� �Ѱ��ֱ�)
		monster.MonsterAttack((Reptile) MainFrame.dragon);
		// �巡���� ���͸� �����ϴ� ������ ȣ���ϴ� �޼��� ȣ�� (���ݴ�� ���ڷ� �Ѱ��ֱ�)
		DragonAttack(monster);

	}

	// �巡���� ���͸� �����ϴ� ������ ȣ���ϴ� �޼���
	private void DragonAttack(Monster monster) {
		Thread dragon_attack = new Thread(new DragonAttackThread(monster));
		dragon_attack.start();
	}

// �巡���� ���͸� �����ϴ� ������
	class DragonAttackThread implements Runnable {
		// ���ݴ��
		Monster target_monster;

		public DragonAttackThread(Monster monster) {
			target_monster = monster;

			// ���ڰ� ����� �Ѿ�Դ��� �α׷� �����
			MainFrame.log_list
					.add("Reptile Ŭ����_DragonAttackThread ������ȣ��_���ڰ� üũ, target_monster.hp: " + target_monster.hp);
			MainFrame.log_list.add(
					"Reptile Ŭ����_DragonAttackThread ������ȣ��_���ڰ� üũ, target_monster.attack: " + target_monster.attack);
			MainFrame.log_list.add("Reptile Ŭ����_DragonAttackThread ������ȣ��_���ڰ� üũ, target_monster.attack_speed: "
					+ target_monster.attack_speed);
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
					// �巡���� ���ݼӵ� �ݿ��ؼ� ���ݰ��� ������
					Thread.sleep(5000 / attack_speed);
					// �巡�� ü�� 0�� �� (�й�, ���� ����)
					if (hp <= 0) {
						// ���α׷� ���� �� �α� ���Ϸ� �����
						MakeFileForLog.makeFile("dragon_breeding_log", MainFrame.log_list);

						JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� �й��߽��ϴ�.");
						System.exit(0);
						// �ο� ����
						is_fight = false;
					}
					// �巡���� ���ݷ¸�ŭ ���� ü�� �϶�
					target_monster.hp -= attack;
					attack_count++;
					// ���� ü�� üũ
					// ������ ü�� 0 �̻�
					if (target_monster.hp > 0) {
						// �巡��-> ���� ���� �˸�����
						DragonAttackPanel.insert_dialogue("<html>" + MainFrame.dragon.name + "(��)�� ���͸� <br>"
								+ attack_count + "��° �����ߴ�.<br> (���� ���� ü��: " + target_monster.hp + ")<br>���ݷ�:" + attack
								+ "</html>");

					}
					// ���� ü�� 0�� ��(�¸�, �ش� �޼��� ����)
					else if (target_monster.hp <= 0) {
						// �¸� �˸�����
						DragonAttackPanel.insert_dialogue("<html>" + MainFrame.dragon.name + "(��)�� ���͸�<br>"
								+ attack_count + "��° �����ߴ�.<br>  (���� ���� ü��: 0)<br>���ݷ�:" + attack + "</html>");
						JOptionPane.showMessageDialog(null, "<html>" + MainFrame.dragon.name
								+ "<br>(��)�� �¸��߽��ϴ�.<br>  (ȣ����+2, ��ȭ������+1, ������-5)</html>");
						JOptionPane.showMessageDialog(null, "������ ���ư��ϴ�.");
						// �ൿ�� ���� �巡�� ���°� ����
						Reptile downcast_reptile = (Reptile) MainFrame.dragon;
						downcast_reptile.full -= 2;
						downcast_reptile.evolution += 3;
						downcast_reptile.likeable += 2;
						MainFrame.dragon = downcast_reptile;
						// �ο�� �޼��� ���� - �� ������� ����
						PaintManager.background = BackGround.HOME;
						DragonStatePanel.insert_dialogue("<html>ü�� ������ �ʿ��ؿ�<br>������ ���� ������ <br>���� ���� �־��</html>");
						MainFrame.main_background.repaint();

						// ���Ʊ� ������ �� ���Ʊ� �巡�� �׷��ֱ�
						if (PaintManager.stage == Growth.HATCHLING) {
							MainFrame.todolist_hatchling.setVisible(true);
						}
						// ������϶� ����� �巡�� �׷��ֱ�
						else if (PaintManager.stage == Growth.JUVENILE) {
							MainFrame.todolist_juvenile.setVisible(true);
						}
						// �ο� ���� ( �ݺ��� ����)
						is_fight = false;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// �ο�� �޼��� ���� �˸� �г� �Ⱥ��̰��ϱ�
			MainFrame.dragon_attack_panel.setVisible(false);

		}

	}
}
