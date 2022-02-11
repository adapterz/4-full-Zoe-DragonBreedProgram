package Dragon;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import GuiRelatedClass.DialoguePanel;
import GuiRelatedClass.DragonStatePanel;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.BackGround;
import other.MakeFileForLog;

/* 	�ֻ����θ�				Dragon (�߻�)
 * 	1����� 		Egg ��   		Reptile (�����,�߻�)
 *  2����� 				  Hatchling(����), Juvenile(�����)
 *  
 *  ����ܰ� Egg -> Hatchling -> Juvenile -> Adult
 */
// �巡���� ����� ���� Ŭ����
public class Juvenile extends Reptile {
	// ���� ���õ�
	public int flight_proficiency;

	// �ʱ�ȭ (�����), Hatchling ���¿��� �� �ν��Ͻ��� ���ڷ� �޾ƿͼ� �ʵ尪 �ݿ����ֱ�
	public Juvenile(Hatchling hatchling_dragon) {
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ����Ⱑ �ƾ��.");
		Random random = new Random();

		// �̸�
		name = hatchling_dragon.name;
		// ȣ����
		likeable = hatchling_dragon.likeable;
		// ��ȭ ������
		evolution = 0;
		// ü��
		hp = hatchling_dragon.hp + random.nextInt(5);
		// ���ݷ�
		attack = hatchling_dragon.attack + random.nextInt(20);
		// ���ݼӵ�
		attack_speed = hatchling_dragon.attack_speed + random.nextInt(10);
		// ������
		full = hatchling_dragon.full + 10;
		// ����
		gender = hatchling_dragon.gender;
		// ���� ���õ�
		flight_proficiency = 0;

		// ���ڰ� ����� �Ѿ�Դ��� �α׷� �����
		MainFrame.log_list.add("Juvenile ������ ȣ��_���ڰ� üũ, name: " + name);
		MainFrame.log_list.add("Juvenile ������ ȣ��_���ڰ� üũ, likeable: " + likeable);
		MainFrame.log_list.add("Juvenile ������ ȣ��_���ڰ� üũ, hp: " + hp);
		MainFrame.log_list.add("Juvenile ������ ȣ��_���ڰ� üũ, attack: " + attack);
		MainFrame.log_list.add("Juvenile ������ ȣ��_���ڰ� üũ, attack_speed: " + attack_speed);
		MainFrame.log_list.add("Juvenile ������ ȣ��_���ڰ� üũ, full: " + full);
		MainFrame.log_list.add("Juvenile ������ ȣ��_���ڰ� üũ, gender: " + gender);

		// �巡�� ���� ���̵� �г�
		DragonStatePanel.insert_dialogue("������ �ڶ� �� �� �־��!");

	}

	// ���� ���� �޼���
	public void flying_practice() {
		// �˸�����
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(��)�� ���࿬���� �����!");
		// Ÿ�̸�(2�ʰ� ���� �� �˸� �޼���)
		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				// �����ϰ� ������õ� ����
				Random random = new Random();
				int random_int = 1 + random.nextInt(5);

				// �ൿ�� ���� �巡�� ���°� ����
				Juvenile downcast_juvenile = (Juvenile) MainFrame.dragon;
				downcast_juvenile.flight_proficiency += random_int;
				downcast_juvenile.likeable += 3;
				downcast_juvenile.evolution++;
				MainFrame.dragon = downcast_juvenile;

				// �˸�����
				JOptionPane.showMessageDialog(null,
						"���� �������... ������ �� �� ��������?(ȣ����+3, ��ȭ������+1, ������õ�+" + random_int + ")");

				// ���� ��� ��� �� ��� �����ֵ��� ���� �ٲ��ֱ�
				PaintManager.background = BackGround.HOME;
				MainFrame.main_background.repaint();

				// ���ϸ�� ���̰� �ϱ�
				MainFrame.todolist_juvenile.setVisible(true);

			}
		};
		Timer timer = new Timer("Timer");
		timer.schedule(task, 2000L);

	}

	// �巡�￡�� ���ֱ�(�������̵�)
	// �޺��ڽ� ���� ��Ҹ� ���ڷ� �޾ƿͼ� �б�ó��
	// �޺��ڽ� ���: "���̱�", "����", "���ۿ�", "��������"
	@Override
	public void feed(String what_eat) {
		// ���ڰ� ����� �Ѿ�Դ��� �α׷� �����
		MainFrame.log_list.add("Juvenile Ŭ����_feed �޼��� ȣ��_���ڰ� üũ, what_eat: " + what_eat);

		Juvenile downcast_juvenile = (Juvenile) MainFrame.dragon;
		if (what_eat.equals("����")) {
			// �ൿ�� ���� �巡�� ���°� ����
			downcast_juvenile.likeable += 2;
			downcast_juvenile.evolution++;
			downcast_juvenile.full += 1;
			downcast_juvenile.hp += 10;

			// �˸�����
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)���� ������ ����! (ȣ����+2, ��ȭ������+1, ������+1, ü��+10)");
			DragonStatePanel.insert_dialogue("<html>" + MainFrame.dragon.name + "<br>(��)�� �����ϳ׿�!</html>");
		} else if (what_eat.equals("���ۿ�")) {
			// �ൿ�� ���� �巡�� ���°� ����
			downcast_juvenile.likeable -= 3;
			downcast_juvenile.evolution++;
			downcast_juvenile.full += 2;
			downcast_juvenile.hp += 20;

			// �˸�����
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)���� ���ۿ��� ����! (ȣ����-3, ��ȭ������+1, ������+4, ü��+20)");
			DragonStatePanel
					.insert_dialogue("<html>" + MainFrame.dragon.name + "<br>(��)�� ¥�����ٴ� ������<br> ����� �ٶ�þ��!</html>");
		} else if (what_eat.equals("��������")) {
			// �ൿ�� ���� �巡�� ���°� ����
			downcast_juvenile.likeable += 5;
			downcast_juvenile.evolution += 3;
			downcast_juvenile.full += 5;
			downcast_juvenile.hp += 70;
			// �˸�����
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(��)���� ���������� ����! (ȣ����+5, ��ȭ������+2, ������+5, ü��+70)");
			DragonStatePanel.insert_dialogue("<html>" + MainFrame.dragon.name + "<br>(��)�� �ſ� �����ϳ׿�!</html>");
		}
		MainFrame.dragon = downcast_juvenile;
	}

	// ��ȭ ���� ���� üũ Ȥ�� ������� üũ(��� �ൿ �޼��� ���� �� �޼��带 ȣ���ؼ� ��ȭ ���� �����ϸ� ���� or ���)
	@Override
	public boolean is_evolution() {

		Juvenile downcast_juvenile = (Juvenile) MainFrame.dragon;
		byte likeable_23 = downcast_juvenile.likeable;
		byte evolution_15 = downcast_juvenile.evolution;
		// �������� ����
		// ü���� 0 ���ϸ� ���� ����
		if (downcast_juvenile.hp <= 0) {
			// ���α׷� ���� �� �α� ���Ϸ� �����
			MakeFileForLog.makeFile("dragon_breeding_log", MainFrame.log_list);

			JOptionPane.showMessageDialog(null, "ü���� 0 ���϶� " + downcast_juvenile.name + "(��)�� �׾����ϴ�. ������ �����մϴ�");
			System.exit(0);
		}
		// ������ 0�̸� ���� ����
		if (downcast_juvenile.full <= 0) {
			// ���α׷� ���� �� �α� ���Ϸ� �����
			MakeFileForLog.makeFile("dragon_breeding_log", MainFrame.log_list);

			JOptionPane.showMessageDialog(null, "�������� 0 ���϶� " + downcast_juvenile.name + "(��)�� �׾����ϴ�. ������ �����մϴ�");
			System.exit(0);
		}
		// ��ȭ���� ����
		// ȣ���� 23 �̻��̸� ��ȭ
		if (likeable_23 > 23) {

			// �˸�����
			DialoguePanel.insert_dialogue("ȣ���� " + likeable_23 + "(��)�� ����!");
			// ��ȭ���� ���� �� true ��ȯ
			return true;

			// ��ȭ������ 15 �̻��̸� ��ȭ
		} else if (evolution_15 > 15) {
			// �˸�����
			DialoguePanel.insert_dialogue("��ȭ������ " + evolution_15 + "(��)�� ����!");

			return true;
		}
		return false;
	}

}
