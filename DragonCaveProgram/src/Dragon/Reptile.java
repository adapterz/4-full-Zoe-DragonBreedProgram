package Dragon;

import javax.swing.JOptionPane;

import Component.DialoguePanel;
import Component.DragonAttackPanel;
import Component.PaintManager;
import Main.MainFrame;
import other.BackGround;
import other.Gender;
import other.Growth;
import other.Monster;

//1�� ���
abstract public class Reptile extends Dragon {
//�ʵ�
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

//�޼���
	// ���ٵ��
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

	// �ı��
	public void wash() {
		// �˸�����\
		
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)�� �İ�����!(ȣ����+1, ��ȭ������+2, ������-2)");

		// �ൿ�� ���� �巡�� ���°� ����
		Reptile downcast_reptile = (Reptile) MainFrame.dragon;
		downcast_reptile.likeable++;
		downcast_reptile.evolution += 2;
		downcast_reptile.full -= 2;
		MainFrame.dragon = downcast_reptile;

	}

	// ���ֱ� (�������̵� ���ֱ�)
	abstract public void feed(String what_eat);

	// �ο��
	public void fight() {
		// ���� ����ħ
		Monster monster = new Monster();
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� �߻��� ���͸� ������.");

		// ���Ͱ� �����ϴ� ������ ȣ���ϴ� �޼���
		monster.MonsterAttack((Reptile)MainFrame.dragon);
		// �巡���� �����ϴ� ������ ȣ���ϴ� �޼���
		DragonAttack(monster);

		
	}

// ������ �ʿ��� ���� ������ �� �巡�� ���� �޼���
	private void DragonAttack(Monster monster) {
		Thread dragon_attack = new Thread(new DragonAttackThread(monster));
		dragon_attack.start();
	}
// ������ �巡���� �����ϴ� ������
	class DragonAttackThread implements Runnable {
		Monster target_monster;
		
		public DragonAttackThread(Monster monster) {
			target_monster = monster;
		}

		@Override
		public void run() {
			boolean is_fight = true;
			int attack_count = 0;
			while (is_fight) {
				try {
					// ���ݼӵ� �ݿ��ؼ� ����
					Thread.sleep(5000 / attack_speed);
					// �巡�� ü�� 0�̸� �й�
					if(hp <= 0) {
						
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
					if(target_monster.hp >0) {
						DragonAttackPanel.insert_dialogue("<html>"+MainFrame.dragon.name + "(��)�� ���͸� <br>"+ attack_count+"��° �����ߴ�.<br> (���� ���� ü��: " + target_monster.hp + ")</html>");
						
					}
						// ���� ü�� 0�� ��
					else if (target_monster.hp <= 0)
					{
						DragonAttackPanel.insert_dialogue("<html>"+ MainFrame.dragon.name + "(��)�� ���͸�<br>"+ attack_count+"��° �����ߴ�.<br>  (���� ���� ü��: 0)</html>");
						JOptionPane.showMessageDialog(null, "<html>"+MainFrame.dragon.name + "<br>(��)�� �¸��߽��ϴ�.<br>  (ȣ����+2, ��ȭ������+1, ������-5)</html>");
						JOptionPane.showMessageDialog(null, "������ ���ư��ϴ�.");
						// �ൿ�� ���� �巡�� ���°� ����
						Reptile downcast_reptile = (Reptile) MainFrame.dragon;
						downcast_reptile.full -= 2;
						downcast_reptile.evolution += 3;
						downcast_reptile.likeable += 2;
						MainFrame.dragon = downcast_reptile;
						// ����г��� ���� �ٲ㼭 �ٽ� �׷��ֱ�

						PaintManager.background = BackGround.HOME;
						MainFrame.main_background.repaint();
						
						if(PaintManager.stage == Growth.HATCHLING) {
							MainFrame.todolist_hatchling.setVisible(true);
						}
						else if(PaintManager.stage == Growth.JUVENILE) {
							MainFrame.todolist_juvenile.setVisible(true);
						}
						// �ο� ����
						is_fight = false;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// �˸� �г� �Ⱥ��̰��ϱ�
			MainFrame.dragon_attack_panel.setVisible(false);

		}

	}
}
