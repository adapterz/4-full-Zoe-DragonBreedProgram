package Dragon;

import javax.swing.JOptionPane;

import Component.DialoguePanel;
import Component.FightDialoguePanel;
import Component.PaintManager;
import Main.MainFrame;
import other.BackGround;
import other.Gender;
import other.Growth;
import other.Monster;

//1차 상속
abstract public class Reptile extends Dragon {
//필드
	// 포만감
	public int full;
	// 공격력
	public int attack;
	// 공격속도
	public int attack_speed;
	// 체력
	public int hp;
	// 성별
	// enum으로 성별 정의
	public Gender gender;

//메서드
	// 쓰다듬기
	public void stroke() {
		// 알림문구
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)를 쓰다듬어줬더니 좋아하네요! (호감도+2, 진화게이지+1, 포만감-1)");

		// 행동에 따른 드래곤 상태값 변경
		Reptile downcast_reptile = (Reptile) MainFrame.dragon;
		downcast_reptile.likeable += 2;
		downcast_reptile.evolution++;
		downcast_reptile.full--;
		MainFrame.dragon = downcast_reptile;
	}

	// 씻기기
	public void wash() {
		// 알림문구\
		
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)를 씻겨줬어요!(호감도+1, 진화게이지+2, 포만감-2)");

		// 행동에 따른 드래곤 상태값 변경
		Reptile downcast_reptile = (Reptile) MainFrame.dragon;
		downcast_reptile.likeable++;
		downcast_reptile.evolution += 2;
		downcast_reptile.full -= 2;
		MainFrame.dragon = downcast_reptile;

	}

	// 밥주기 (오버라이딩 해주기)
	abstract public void feed(String what_eat);

	// 싸우기
	public void fight() {
		// 몬스터 마주침
		Monster monster = new Monster();
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)는 야생의 몬스터를 만났다.");

		// 몬스터가 공격하는 스레드 호출하는 메서드
		monster.MonsterAttack((Reptile)MainFrame.dragon);
		// 드래곤이 공격하는 스레드 호출하는 메서드
		DragonAttack(monster);

		
	}

// 전투에 필요한 서브 스레드 및 드래곤 공격 메서드
	private void DragonAttack(Monster monster) {
		Thread dragon_attack = new Thread(new DragonAttackThread(monster));
		dragon_attack.start();
	}
// 전투시 드래곤이 공격하는 스레드
	class DragonAttackThread implements Runnable {
		Monster target_monster;
		
		public DragonAttackThread(Monster monster) {
			target_monster = monster;
		}

		@Override
		public void run() {
			boolean is_fight = true;
			while (is_fight) {
				try {
					// 공격속도 반영해서 공격
					Thread.sleep(5000 / attack_speed);
					// 드래곤 체력 0이면 패배
					if(hp <= 0) {
						
						JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 패배했습니다.");
						System.exit(0);
						// 싸움 종료
						is_fight = false;
						// 알림 패널 안보이게하기
						MainFrame.fight_dialogue_panel.setVisible(false);
					}
					// 드래곤의 공격력만큼 몬스터 체력 하락
					target_monster.hp -= attack;
					// 몬스터 체력 체크
						// 몬스터의 체력 0 이상
					if(target_monster.hp >0) {
						FightDialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)는 몬스터를 공격했다. (남은 몬스터 체력: " + target_monster.hp + ")");
						
					}
						// 몬스터 체력 0일 때
					else if (target_monster.hp <= 0)
					{
						FightDialoguePanel.insert_dialogue( MainFrame.dragon.name + "(이)는 몬스터를 공격했다. (남은 몬스터 체력: 0)");
						JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 승리했습니다. (호감도+2, 진화게이지+1, 포만감-5)");
						JOptionPane.showMessageDialog(null, "집으로 돌아갑니다.");
						// 행동에 따른 드래곤 상태값 변경
						Reptile downcast_reptile = (Reptile) MainFrame.dragon;
						downcast_reptile.full -= 2;
						downcast_reptile.evolution += 3;
						downcast_reptile.likeable += 2;
						MainFrame.dragon = downcast_reptile;
						// 배경패널의 상태 바꿔서 다시 그려주기

						PaintManager.background = BackGround.HOME;
						MainFrame.main_background.repaint();
						
						if(PaintManager.stage == Growth.HATCHLING) {
							MainFrame.todolist_hatchling.setVisible(true);
						}
						else if(PaintManager.stage == Growth.JUVENILE) {
							MainFrame.todolist_juvenile.setVisible(true);
						}
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
