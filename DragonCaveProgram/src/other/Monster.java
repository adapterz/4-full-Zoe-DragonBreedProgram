package other;

import java.util.Random;

import javax.swing.JOptionPane;

import Component.DragonAttackPanel;
import Component.MonsterAttackPanel;
import Dragon.Hatchling;
import Dragon.Reptile;
import Main.MainFrame;

//몬스터
public class Monster {
	// 체력
	public int hp;
	// 공격력
	public int attack;
	// 공격속도
	public int attack_speed;

	public Monster() {
		Random random = new Random();
		// 체력과 공격력 랜덤하게 생성
		hp = 100 + random.nextInt(30);
		attack = 20 + random.nextInt(30);
		attack_speed = 1 + random.nextInt(5);
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
			int attack_count = 0;
			while (is_fight) {
				try {
					// 공격속도 반영해서 공격
					Thread.sleep(5000 / attack_speed);
					// 몬스터 체력 0이면 종료
					if (hp <= 0) {
						// 싸움 종료
						is_fight = false;
					}
					// 몬스터의 공격력만큼 드래곤 체력 하락
					target_reptile.hp -= attack;
					attack_count++;
					// 드래곤 체력 체크
					// 드래곤의 체력 0 이상
					if (target_reptile.hp > 0) {

						MonsterAttackPanel.insert_dialogue("<html>몬스터가 " + attack_count
								+ " 번째 공격을 해왔다.<br> (남은 드래곤 체력: " + target_reptile.hp + ")</html>");
					}
					// 드래곤 체력 0일 때
					else if (target_reptile.hp <= 0) {
						MonsterAttackPanel
								.insert_dialogue("<html>몬스터가 " + attack_count + " 번째 공격해왔다.<br> (남은 드래곤 체력: 0)</html>");
						// 싸움 종료
						is_fight = false;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 알림 패널 안보이게하기
			MainFrame.monster_attack_panel.setVisible(false);

		}

	}
}