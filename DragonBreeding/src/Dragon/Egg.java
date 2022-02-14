package Dragon;

import java.util.Random;

import GuiRelatedClass.DialoguePanel;
import GuiRelatedClass.DragonStatePanel;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.Growth;

/* 	�ֻ����θ�				Dragon (�߻�)
 * 	1����� 		Egg ��   		Reptile (�����,�߻�)
 *  2����� 				  Hatchling(����), Juvenile(�����)
 *  
 *  ����ܰ� Egg -> Hatchling -> Juvenile -> Adult
 */

// Dragon Ŭ������ ��ӹ��� Egg Ŭ����, �巡���� �� ������ ���� ��Ÿ���� Ŭ����
public class Egg extends Dragon {

	// �� �µ����� (ȯ�� Ŭ������ Home Ŭ������ home_degree�� ������ �޼���)
	// ���� �µ�(home_degree)�� ���� ������ ��ȭ�� �� �ִ���, ���� ��ȭ���� �� �巡���� ������ ������ ��Ĩ�ϴ�
	// �µ� �÷��ֱ�
	public void increase_degree_control() { 
		// Home Ŭ������ �µ����� �޼��� ȣ��
		MainFrame.home.increase_degree();
		// �ش� �̺�Ʈ�� ���� �巡���� ���°� ��ȭ
		Egg downcast_dragon_egg = (Egg) MainFrame.dragon;

		downcast_dragon_egg.likeable++;
		downcast_dragon_egg.evolution++;
		MainFrame.dragon = downcast_dragon_egg;
		// �� �µ� ��� �ȳ�����
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)�� �µ����! degree: " + MainFrame.home.home_degree);

	}

	// �µ�������
	public void decrease_degree_control() {
		// Home Ŭ������ �µ����� �޼��� ȣ��
		MainFrame.home.decrease_degree();
		// �ش� �̺�Ʈ�� ���� �巡���� ���°� ��ȭ
		Egg downcast_dragon_egg = (Egg) MainFrame.dragon;

		downcast_dragon_egg.likeable++;
		downcast_dragon_egg.evolution++;
		MainFrame.dragon = downcast_dragon_egg;
		// �� �µ� �϶� �ȳ�����
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)�� �µ��϶�! degree: " + MainFrame.home.home_degree);

	}

	// (�Ϲ�������) �˿��� ���ɱ�
	public void talk_to() {
		// �α�

		Random random = new Random();
		int random_int = random.nextInt(3);

		// �ش� �̺�Ʈ�� ���� ������ �巡���� ����(�б�ó��)
		// ���� �����ϴ� ���
		if (random_int == 0) {
			// �ȳ�����
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)���� ���� �ɾ����� �����Ѵ�! \n (ȣ����+3, ��ȭ������+1)");
			// �ش� �̺�Ʈ�� ���� �巡���� ���°� ��ȭ
			Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
			downcast_dragon_egg.likeable += 3;
			downcast_dragon_egg.evolution++;
			MainFrame.dragon = downcast_dragon_egg;
			// ���� ������ ���� ���
		} else if (random_int == 1) {
			// �ȳ�����
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)���� ���� �ɾ��µ� ������ ���� �Ф� \n (ȣ����-2, ��ȭ������+1)");
			// �ش� �̺�Ʈ�� ���� �巡���� ���°� ��ȭ
			Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
			downcast_dragon_egg.likeable -= 2;
			downcast_dragon_egg.evolution++;
			MainFrame.dragon = downcast_dragon_egg;
			// ���� ��¦ �����̴� ���
		} else {
			// �ȳ�����
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)���� ���� �ɾ����� ��¦ �����δ� \n (ȣ����+1, ��ȭ������+1)");
			// �ش� �̺�Ʈ�� ���� �巡���� ���°� ��ȭ
			Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
			downcast_dragon_egg.likeable++;
			downcast_dragon_egg.evolution++;
			MainFrame.dragon = downcast_dragon_egg;

		}
	}

	// ��ȭ ���� ���� üũ Ȥ�� ������� üũ(��� �ൿ �޼��� ���� �� �޼��带 ȣ���ؼ� ��ȭ ���� �����ϸ� ���� or ���)
	public boolean is_evolution() {
		// �α�
		Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
		byte likeable_7 = downcast_dragon_egg.likeable;
		byte evolution_4 = downcast_dragon_egg.evolution;
		// ȣ���� 7 �̻��̸� ��ȭ
		if (likeable_7 > 7) {
			// �α�
			PaintManager.stage = Growth.EGG;
			// ���� �������� dragon ������ ����Ű�� �ν��Ͻ� Hatchling ���� �ٲ��ֱ�
			// Egg ������ �� �ν��Ͻ��� ���°� �ݿ��ϱ� ���� ���ڷ� �Ѱ��ֱ�
			MainFrame.dragon = new Hatchling(downcast_dragon_egg);
			// �ȳ�����
			DialoguePanel.insert_dialogue("ȣ���� " + likeable_7 + "�� ����!");
			// true ��ȯ �� �巡�� ����
			return true;
			// ��ȭ������ 4 �̻��̸� ��ȭ
		} else if (evolution_4 > 4) {
			// �α�
			PaintManager.stage = Growth.EGG;
			MainFrame.dragon = new Hatchling(downcast_dragon_egg);
			// �ȳ�����
			DialoguePanel.insert_dialogue("��ȭ������ " + evolution_4 + "�� ����!");
			return true;
		}
		return false;

	}

// �ʱ�
	public Egg() {
		// ȣ����
		likeable = 0;
		// ��ȭ ������
		evolution = 0;
		// �̸�
		name = null;

	}

}
