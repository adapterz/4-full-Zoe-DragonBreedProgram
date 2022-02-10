package Dragon;

import java.util.Random;

import javax.swing.JOptionPane;

import GuiRelatedClass.DialoguePanel;
import Main.MainFrame;
import enums.Gender;
import other.Home;

/* 	�ֻ����θ�				Dragon (�߻�)
 * 	1����� 		Egg ��   		Reptile (�����,�߻�)
 *  2����� 				  Hatchling(����), Juvenile(�����)
 *  
 *  ����ܰ� Egg -> Hatchling -> Juvenile -> Adult
 */
// �巡���� ���Ʊ� ���� Ŭ����
public class Hatchling extends Reptile {
	// �ʱ�ȭ (���Ʊ�), Egg ���¿��� �� �ν��Ͻ��� ���ڷ� �޾ƿͼ� �ʵ尪 �ݿ����ֱ�
	public Hatchling(Egg egg_dragon) {
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ��ȭ�� �����մϴ�.");
		Random random = new Random();

		// �̸�
		name = egg_dragon.name;
		// ȣ����
		likeable = egg_dragon.likeable;
		// ��ȭ ������
		evolution = 0;
		// ü��
		hp = 100 + random.nextInt(30);
		// ���ݷ�
		attack = 20 + random.nextInt(20);
		// ���ݼӵ�
		attack_speed = 1 + random.nextInt(10);
		// ������
		full = 7;

		// ���� �µ� 25�� ���� �� ��ȭ ����, ��������
		if (MainFrame.home.home_degree < 25) {
			// �α�
			MainFrame.LOG.info("Egg- ���");
			
			JOptionPane.showMessageDialog(null, "�µ��� 25�� ���϶� ��ȭ�� �����߽��ϴ�. ������ �����մϴ�");
			System.exit(0);
		}
		// ���� �µ��� ���� gender�� ����
		// ����
		else if (25 <= MainFrame.home.home_degree && MainFrame.home.home_degree <= 30) {
			gender = Gender.WOMAN;
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ���ƿ���~!");

		} // ���� ����
		else if (30 < MainFrame.home.home_degree && MainFrame.home.home_degree <= 34) {
			if (random.nextBoolean()) {
				gender = Gender.MAN;
				JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ���ƿ���~!");
			} else {
				gender = Gender.WOMAN;
				JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ���ƿ���~!");

			}
			// ����
		} else if (34 < MainFrame.home.home_degree) {
			gender = Gender.MAN;
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ���ƿ���~!");
		}
	}

	// �ൿ �޼���
	// �巡�￡�� ���ֱ�(�������̵�)
	// �޺��ڽ� ���� ��Ҹ� ���ڷ� �޾ƿͼ� �б�ó��
	// �޺��ڽ� ���: "����","�Ǵн���","�ͶѶ��"
	public void feed(String what_eat) {
		// �α�
		MainFrame.LOG.info("Hatchling - ���ֱ�");
		
		Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
		if (what_eat.equals("����")) {
			// �ൿ�� ���� �巡���� ���°� ����
			downcast_dragon_hatchling.likeable += 2;
			downcast_dragon_hatchling.evolution++;
			downcast_dragon_hatchling.full += 2;
			downcast_dragon_hatchling.hp += 3;

			// �˸�����
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)���� ������ ����! (ȣ����+2, ��ȭ������+1, ������+2, ü��+3)");

		} else if (what_eat.equals("�Ǵн���")) {
			// �ൿ�� ���� �巡���� ���°� ����
			downcast_dragon_hatchling.likeable -= 2;
			downcast_dragon_hatchling.evolution++;
			downcast_dragon_hatchling.full += 4;
			downcast_dragon_hatchling.hp += 5;

			// �˸�����
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)���� �Ǵн����� ����! (ȣ����-2, ��ȭ������+1, ������+4, ü��+5)");

		} else if (what_eat.equals("�ͶѶ��")) {
			// �ൿ�� ���� �巡���� ���°� ����
			downcast_dragon_hatchling.likeable -= 3;
			downcast_dragon_hatchling.evolution += 3;
			downcast_dragon_hatchling.full += 2;
			downcast_dragon_hatchling.hp += 7;
			// �˸�����
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)���� �ͶѶ�̸� ����! (ȣ����-3, ��ȭ������+3, ������+2, ü��+7)");
		}
		MainFrame.dragon = downcast_dragon_hatchling;
	}

	// ��ȭ ���� ���� üũ Ȥ�� ������� üũ(��� �ൿ �޼��� ���� �� �޼��带 ȣ���ؼ� ��ȭ ���� �����ϸ� ���� or ���)
	@Override
	public boolean is_evolution() {
		// �α�
		MainFrame.LOG.info("Hatchling - ��ȭ/��� ���� üũ");
		
		Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
		byte likeable_14 = downcast_dragon_hatchling.likeable;
		byte evolution_7 = downcast_dragon_hatchling.evolution;
		// ü���� 0 ���ϸ� ���� ����
		if (downcast_dragon_hatchling.hp <= 0) {
			// �α�
			MainFrame.LOG.info("Hatchling - ���");
			
			JOptionPane.showMessageDialog(null,
					"ü���� 0 ���϶� " + downcast_dragon_hatchling.name + "(��)�� �׾����ϴ�. ������ �����մϴ�");
			System.exit(0);
		}
		// ������ 0 �̸� ���� ����
		if (downcast_dragon_hatchling.full <= 0) {
			// �α�
			MainFrame.LOG.info("Hatchling - ���");
			JOptionPane.showMessageDialog(null,
					"�������� 0 ���϶� " + downcast_dragon_hatchling.name + "(��)�� �׾����ϴ�. ������ �����մϴ�");
			System.exit(0);
		}
		// ȣ���� 14 �̻��̸� ��ȭ
		if (likeable_14 > 14) {
			// �α�
			MainFrame.LOG.info("Hatchling - ��ȭ");
			// ���� ����ܰ� ������� ������ ȣ��
			MainFrame.dragon = new Juvenile(downcast_dragon_hatchling);
			// �˸�����
			DialoguePanel.insert_dialogue("ȣ���� " + likeable_14 + "(��)�� ����!");

			return true;
			// ��ȭ������ 7 �̻��̸� ��ȭ
		} else if (evolution_7 > 7) {
			// �α�
			MainFrame.LOG.info("Hatchling - ��ȭ");
			// ���� ����ܰ� ������� ������ ȣ��
			MainFrame.dragon = new Juvenile(downcast_dragon_hatchling);
			// �˸�����
			DialoguePanel.insert_dialogue("��ȭ������ " + evolution_7 + "(��)�� ����!");
			return true;
		}
		return false;
	}

}