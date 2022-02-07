package other;

import java.util.Random;

import javax.swing.JOptionPane;

import Component.FightDialoguePanel;
import Dragon.Hatchling;
import Dragon.Reptile;
import Main.MainFrame;

//몬스터
public class Monster {
	// 체력
	public byte hp;
	// 공격력
	public byte attack;
	// 공격속도
	public byte attack_speed;

	public Monster() {
		Random random = new Random();
		// 체력과 공격력 랜덤하게 생성
		hp = (byte) (100 + (byte) random.nextInt(30));
		attack = (byte) (20 + (byte) random.nextInt(10));
		attack_speed = (byte) random.nextInt(10);
	}

	// 공격 메서드

	public void MonsterAttack(Reptile reptile) {
		// 몬스터 공격 서브 스레드
		Thread monster_attack = new Thread(new MonsterAttackThread(reptile));
		monster_attack.start();
	}

	// 전투시 몬스터가 공격하는 스레드
	class MonsterAttackThread implements Runnable {
		Reptile target_reptile;

		public MonsterAttackThread(Reptile reptile) {
			target_reptile = reptile;
		}

		@Override
		public void run() {
			boolean is_fight = true;
			while (is_fight) {
				try {
					// 공격속도 반영해서 공격
					Thread.sleep(5000 / attack_speed);
					// 몬스터 체력 0이면 종료
					if (hp <= 0) {
						// 싸움 종료
						is_fight = false;
						// 알림 패널 안보이게하기
						MainFrame.fight_dialogue_panel.setVisible(false);
					}
					// 몬스터의 공격력만큼 드래곤 체력 하락
					target_reptile.hp -= attack;
					// 드래곤 체력 체크
					// 드래곤의 체력 0 이상
					if (target_reptile.hp > 0) {
						
						FightDialoguePanel.insert_dialogue("몬스터가 공격해왔다. (남은 드래곤 체력: " + target_reptile.hp + ")");
					}
					// 드래곤 체력 0일 때
					else if (target_reptile.hp <= 0) {
						FightDialoguePanel.insert_dialogue("몬스터가 공격해왔다. (남은 드래곤 체력: 0)");
						// 싸움 종료
						is_fight = false;
						// 알림 패널 안보이게하기
						MainFrame.fight_dialogue_panel.setVisible(false);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}
}