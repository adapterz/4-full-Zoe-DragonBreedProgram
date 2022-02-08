package Dragon;

import javax.swing.JOptionPane;

import GuiRelatedClass.DialoguePanel;
import GuiRelatedClass.DragonAttackPanel;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.BackGround;
import enums.Gender;
import enums.Growth;
import other.Monster;

/* 	최상위부모				Dragon (추상)
 * 	1차상속 		Egg 알   		Reptile (파충류,추상)
 *  2차상속 				  Hatchling(유아), Juvenile(성장기)
 *  
 *  드래곤 성장단계: Egg -> Hatchling -> Juvenile -> Adult
	참고사항: Adult 클래스는 따로 구현하지 않음
 */

// 드래곤이 알에서 부화한 이후 파충류 상태일 때를 정의한 추상클래스
abstract public class Reptile extends Dragon {
// 필드
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

// 행동메서드
	// 드래곤을 쓰다듬기
	public void stroke() {
		// 알림내용
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)를 쓰다듬어줬더니 좋아하네요! (호감도+2, 진화게이지+1, 포만감-1)");

		// 행동에 따른 드래곤 상태값 변경
		Reptile downcast_reptile = (Reptile) MainFrame.dragon;
		downcast_reptile.likeable += 2;
		downcast_reptile.evolution++;
		downcast_reptile.full--;
		MainFrame.dragon = downcast_reptile;
	}

	// 드래곤을 씻기기
	public void wash() {
		// 알림문구

		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)를 씻겨줬어요!(호감도+1, 진화게이지+2, 포만감-2)");

		// 행동에 따른 드래곤 상태값 변경
		Reptile downcast_reptile = (Reptile) MainFrame.dragon;
		downcast_reptile.likeable++;
		downcast_reptile.evolution += 2;
		downcast_reptile.full -= 2;
		MainFrame.dragon = downcast_reptile;

	}

	// 드래곤에게 밥주기(오버라이딩)
	// 콤보박스 선택 요소를 인자로 받아와서 분기처리
	abstract public void feed(String what_eat);

	// 몬스터(클래스)와 싸우기
	// 몬스터가 드래곤을 공격하는 스레드와 드래곤이 몬스터를 공격하는 스레드 따로 호출 (각 객체의 공격속도에 따라 공격간격 정해짐)
	// 매 공격시 공격력만큼 체력 깎음
	public void fight() {
		// 몬스터 마주침 (해당 메서드의 지역변수)
		Monster monster = new Monster();
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)는 야생의 몬스터를 만났다.");

		// 몬스터가 드래곤을 공격하는 스레드 호출하는 메서드 호출 (공격대상 인자로 넘겨주기)
		monster.MonsterAttack((Reptile) MainFrame.dragon);
		// 드래곤이 몬스터를 공격하는 스레드 호출하는 메서드 호출 (공격대상 인자로 넘겨주기)
		DragonAttack(monster);

	}

	// 드래곤이 몬스터를 공격하는 스레드 호출하는 메서드
	private void DragonAttack(Monster monster) {
		Thread dragon_attack = new Thread(new DragonAttackThread(monster));
		dragon_attack.start();
	}

// 드래곤이 몬스터를 공격하는 스레드
	class DragonAttackThread implements Runnable {
		// 공격대상
		Monster target_monster;

		public DragonAttackThread(Monster monster) {
			target_monster = monster;
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
					// 드래곤의 공격속도 반영해서 공격간격 정해짐
					Thread.sleep(5000 / attack_speed);
					// 드래곤 체력 0일 때 (패배, 게임 종료)
					if (hp <= 0) {

						JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 패배했습니다.");
						System.exit(0);
						// 싸움 종료
						is_fight = false;
					}
					// 드래곤의 공격력만큼 몬스터 체력 하락
					target_monster.hp -= attack;
					attack_count++;
					// 몬스터 체력 체크
					// 몬스터의 체력 0 이상
					if (target_monster.hp > 0) {
						// 드래곤-> 몬스터 공격 알림내용
						DragonAttackPanel.insert_dialogue("<html>" + MainFrame.dragon.name + "(이)는 몬스터를 <br>"
								+ attack_count + "번째 공격했다.<br> (남은 몬스터 체력: " + target_monster.hp + ")</html>");

					}
					// 몬스터 체력 0일 때(승리, 해당 메서드 종료)
					else if (target_monster.hp <= 0) {
						// 승리 알림내용
						DragonAttackPanel.insert_dialogue("<html>" + MainFrame.dragon.name + "(이)는 몬스터를<br>"
								+ attack_count + "번째 공격했다.<br>  (남은 몬스터 체력: 0)</html>");
						JOptionPane.showMessageDialog(null, "<html>" + MainFrame.dragon.name
								+ "<br>(이)가 승리했습니다.<br>  (호감도+2, 진화게이지+1, 포만감-5)</html>");
						JOptionPane.showMessageDialog(null, "집으로 돌아갑니다.");
						// 행동에 따른 드래곤 상태값 변경
						Reptile downcast_reptile = (Reptile) MainFrame.dragon;
						downcast_reptile.full -= 2;
						downcast_reptile.evolution += 3;
						downcast_reptile.likeable += 2;
						MainFrame.dragon = downcast_reptile;
						// 싸우기 메서드 종료 - 집 배경으로 변경
						PaintManager.background = BackGround.HOME;
						MainFrame.main_background.repaint();

						// 유아기 상태일 때 유아기 드래곤 그려주기
						if (PaintManager.stage == Growth.HATCHLING) {
							MainFrame.todolist_hatchling.setVisible(true);
						}
						// 성장기일때 성장기 드래곤 그려주기
						else if (PaintManager.stage == Growth.JUVENILE) {
							MainFrame.todolist_juvenile.setVisible(true);
						}
						// 싸움 종료 ( 반복문 종료)
						is_fight = false;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 싸우기 메서드 관련 알림 패널 안보이게하기
			MainFrame.dragon_attack_panel.setVisible(false);

		}

	}
}
