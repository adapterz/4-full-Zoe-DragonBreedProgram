package other;

import java.util.Random;

import javax.swing.JOptionPane;

import Dragon.Hatchling;
import Dragon.Reptile;
import GuiRelatedClass.DragonAttackPanel;
import GuiRelatedClass.MonsterAttackPanel;
import Main.MainFrame;

// 드래곤이 fight 메서드를 호출할 때 싸울 대상이 될 몬스터 클래스 
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

	// Reptile 클래스의 fight 메서드에 호출해줄 메서드
	// 몬스터가 드래곤을 공격하는 스레드를 호출해줍니다.
	public void MonsterAttack(Reptile reptile) {
		// 몬스터가 드래곤 공격하는 스레드
		// 인자로 싸울 대상이 될 드래곤 객체를 넘겨줍니다.
		Thread monster_attack = new Thread(new MonsterAttackThread(reptile));
		monster_attack.start();
	}

	// 몬스터가 드래곤 공격하는 스레드 정의
	class MonsterAttackThread implements Runnable {
		// 싸울 대상 드래곤
		Reptile target_reptile;

		public MonsterAttackThread(Reptile reptile) {
			target_reptile = reptile;
		}

		@Override
		public void run() {
			// 싸움 종료 조건 boolean
			boolean is_fight = true;
			// 공격횟수
			int attack_count = 0;
			// is_fight이 false가 되면 싸움 종료
			while (is_fight) {
				try {
					// 몬스터의 공격속도 반영해서 공격간격 정해짐
					Thread.sleep(5000 / attack_speed);
					// 몬스터 체력 0일 때 (드래곤의 승리, 해당 스레드 종료)
					if (hp <= 0) {
						// 싸움 종료
						JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 패배했습니다.");
						System.exit(0);
						is_fight = false;
					}
					// 몬스터의 공격력만큼 드래곤 체력 하락
					target_reptile.hp -= attack;
					attack_count++;
					// 드래곤 체력 체크
					// 드래곤의 체력 0 이상
					if (target_reptile.hp > 0) {
						// 몬스터-> 드래곤 공격 알림내용
						MonsterAttackPanel
								.insert_dialogue("<html>몬스터가 " + attack_count + " 번째 공격을 해왔다.<br> (남은 드래곤 체력: "
										+ target_reptile.hp + ")<br>공격력:" + attack + "</html>");
					}
					// 드래곤 체력 0일 때 (드래곤의 패배)
					else if (target_reptile.hp <= 0) {
						MonsterAttackPanel.insert_dialogue("<html>몬스터가 " + attack_count
								+ " 번째 공격해왔다.<br> (남은 드래곤 체력: 0)<br>공격력:" + attack + "</html>");
						// 싸움 종료
						is_fight = false;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 싸우기 메서드 관련 알림 패널 안보이게하기
			MainFrame.monster_attack_panel.setVisible(false);

		}

	}
}