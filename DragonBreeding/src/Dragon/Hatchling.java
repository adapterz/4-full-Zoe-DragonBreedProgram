package Dragon;

import java.util.Random;

import javax.swing.JOptionPane;

import GuiRelatedClass.DialoguePanel;
import GuiRelatedClass.DragonStatePanel;
import Main.MainFrame;
import enums.Gender;
import other.Home;
import other.MakeFileForLog;

/* 	최상위부모				Dragon (추상)
 * 	1차상속 		Egg 알   		Reptile (파충류,추상)
 *  2차상속 				  Hatchling(유아), Juvenile(성장기)
 *  
 *  성장단계 Egg -> Hatchling -> Juvenile -> Adult
 */
// 드래곤의 유아기 상태 클래스
public class Hatchling extends Reptile {
	// 초기화 (유아기), Egg 상태였을 때 인스턴스를 인자로 받아와서 필드값 반영해주기
	public Hatchling(Egg egg_dragon) {

		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 부화를 시작합니다.");
		Random random = new Random();
		// 이름
		name = egg_dragon.name;
		// 호감도
		likeable = egg_dragon.likeable;
		// 진화 게이지
		evolution = 0;
		// 체력
		hp = 100 + random.nextInt(30);
		// 공격력
		attack = 20 + random.nextInt(20);
		// 공격속도
		attack_speed = 1 + random.nextInt(10);
		// 포만감
		full = 7;
		// 인자값 제대로 넘어왔는지 로그로 남기기
		MainFrame.log_list.add("Hatchling 생성자 호출_인자값 체크, name: " + name);
		MainFrame.log_list.add("Hatchling 생성자 호출_인자값 체크, likeable: " + likeable);

		// 집의 온도 25도 이하 시 부화 실패, 게임종료
		if (MainFrame.home.home_degree < 25) {
			// 프로그램 종료 전 로그 파일로 남기기
			MakeFileForLog.makeFile("dragon_breeding_log", MainFrame.log_list);

			JOptionPane.showMessageDialog(null, "온도가 25도 이하라 부화에 실패했습니다. 게임을 종료합니다");
			System.exit(0);
		}
		// 집의 온도에 따라 gender값 결정
		// 암컷
		else if (25 <= MainFrame.home.home_degree && MainFrame.home.home_degree <= 30) {
			gender = Gender.WOMAN;
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)는 여아에요~!");
			DragonStatePanel.insert_dialogue("<html>" + MainFrame.dragon.name + "<br>(이)는 과일을 좋아해요!</html>");

		} // 성별 랜덤
		else if (30 < MainFrame.home.home_degree && MainFrame.home.home_degree <= 34) {
			if (random.nextBoolean()) {
				gender = Gender.MAN;
				JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)는 남아에요~!");
				DragonStatePanel.insert_dialogue("<html>체력 보충 후 전투를 <br>나가보는건 어떨까요?</html>");
				attack += 5;
			} else {
				gender = Gender.WOMAN;
				JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)는 여아에요~!");
				DragonStatePanel.insert_dialogue("<html>" + MainFrame.dragon.name + "<br>(이)는 과일을 좋아해요!</html>");

			}
			// 수컷
		} else if (34 < MainFrame.home.home_degree) {
			gender = Gender.MAN;
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)는 남아에요~!");
			DragonStatePanel.insert_dialogue("<html>체력 보충 후 전투를 <br>나가보는건 어떨까요?</html>");
			attack += 5;
		}

	}

	// 행동 메서드
	// 드래곤에게 밥주기(오버라이딩)
	// 콤보박스 선택 요소를 인자로 받아와서 분기처리
	// 콤보박스 요소: "과일","피닉스웜","귀뚜라미"
	public void feed(String what_eat) {
		// 인자값 제대로 넘어왔는지 로그로 남기기
		MainFrame.log_list.add("Hatchling 클래스_feed 메서드 호출_인자값 체크, what_eat: " + what_eat);

		Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
		if (what_eat.equals("과일")) {
			// 행동에 따른 드래곤의 상태값 변경
			downcast_dragon_hatchling.likeable += 2;
			downcast_dragon_hatchling.evolution++;
			downcast_dragon_hatchling.full += 2;
			downcast_dragon_hatchling.hp += 30;

			// 알림문구
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)에게 과일을 줬어요! (호감도+2, 진화게이지+1, 포만감+2, 체력+30)");
			DragonStatePanel.insert_dialogue("<html>" + MainFrame.dragon.name + "<br>(이)가 좋아하네요!</html>");
		} else if (what_eat.equals("피닉스웜")) {
			// 행동에 따른 드래곤의 상태값 변경
			downcast_dragon_hatchling.likeable -= 2;
			downcast_dragon_hatchling.evolution++;
			downcast_dragon_hatchling.full += 4;
			downcast_dragon_hatchling.hp += 20;

			// 알림문구
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)에게 피닉스웜을 줬어요! (호감도-2, 진화게이지+1, 포만감+4, 체력+20)");
			DragonStatePanel.insert_dialogue("포만감이 많이 찼어요!");
		} else if (what_eat.equals("귀뚜라미")) {
			// 행동에 따른 드래곤의 상태값 변경
			downcast_dragon_hatchling.likeable -= 3;
			downcast_dragon_hatchling.evolution += 3;
			downcast_dragon_hatchling.full += 2;
			downcast_dragon_hatchling.hp += 50;
			// 알림문구
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)에게 귀뚜라미를 줬어요! (호감도-3, 진화게이지+3, 포만감+2, 체력+50)");
			DragonStatePanel.insert_dialogue("체력이 많이 찼어요!");
		}
		MainFrame.dragon = downcast_dragon_hatchling;
	}

	// 진화 조건 만족 체크 혹은 사망여부 체크(모든 행동 메서드 이후 이 메서드를 호출해서 진화 조건 만족하면 성장 or 사망)
	@Override
	public boolean is_evolution() {

		Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
		byte likeable_14 = downcast_dragon_hatchling.likeable;
		byte evolution_7 = downcast_dragon_hatchling.evolution;
		// 체력이 0 이하면 게임 종료
		if (downcast_dragon_hatchling.hp <= 0) {
			// 프로그램 종료 전 로그 파일로 남기기
			MakeFileForLog.makeFile("dragon_breeding_log", MainFrame.log_list);

			JOptionPane.showMessageDialog(null,
					"체력이 0 이하라 " + downcast_dragon_hatchling.name + "(이)가 죽었습니다. 게임을 종료합니다");
			System.exit(0);
		}
		// 포만감 0 이면 게임 종료
		if (downcast_dragon_hatchling.full <= 0) {
			// 프로그램 종료 전 로그 파일로 남기기
			MakeFileForLog.makeFile("dragon_breeding_log", MainFrame.log_list);

			JOptionPane.showMessageDialog(null,
					"포만감이 0 이하라 " + downcast_dragon_hatchling.name + "(이)가 죽었습니다. 게임을 종료합니다");
			System.exit(0);
		}
		// 호감도 14 이상이면 진화
		if (likeable_14 > 14) {
			// 다음 성장단계 쥬버나일 생성자 호출
			MainFrame.dragon = new Juvenile(downcast_dragon_hatchling);
			// 알림문구
			DialoguePanel.insert_dialogue("호감도 " + likeable_14 + "(으)로 성장!");

			return true;
			// 진화게이지 7 이상이면 진화
		} else if (evolution_7 > 7) {
			// 다음 성장단계 쥬버나일 생성자 호출
			MainFrame.dragon = new Juvenile(downcast_dragon_hatchling);
			// 알림문구
			DialoguePanel.insert_dialogue("진화게이지 " + evolution_7 + "(으)로 성장!");
			return true;
		}
		return false;
	}

}
