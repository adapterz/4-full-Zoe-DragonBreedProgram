package Dragon;

import java.util.Random;

import Component.DialoguePanel;
import Main.MainFrame;

//1차 상속
public class Egg extends Dragon {

	// 집 온도조절
	// 온도올려주기
	public void increase_degree_control() {
		MainFrame.home.increase_degree();
		// 해당 이벤트에 대한 드래곤의 상태값 변화
		Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
		
		downcast_dragon_egg.likeable++;
		downcast_dragon_egg.evolution++;
		MainFrame.dragon = downcast_dragon_egg;
		// 안내문구
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)의 온도상승! degree: " + MainFrame.home.home_degree);

	}

	// 온도내리기
	public void decrease_degree_control() {
		MainFrame.home.decrease_degree();
		// 해당 이벤트에 대한 드래곤의 상태값 변화
		Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
		
		downcast_dragon_egg.likeable++;
		downcast_dragon_egg.evolution++;
		MainFrame.dragon = downcast_dragon_egg;
		// 안내문구
		DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)의 온도하락! degree: " + MainFrame.home.home_degree);

	}

	// (일방적으로) 말걸기
	public void talk_to() {
		Random random = new Random();
		int random_int = random.nextInt(3);

		// 해당 이벤트에 대해 랜덤한 드래곤의 반응(분기처리)
		if (random_int == 0) {
			// 안내문구
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)에게 말을 걸었더니 좋아한다! \n (호감도+3, 진화게이지+1)");
			// 해당 이벤트에 대한 드래곤의 상태값 변화
			Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
			downcast_dragon_egg.likeable+=3;
			downcast_dragon_egg.evolution++;
			MainFrame.dragon = downcast_dragon_egg;
		
		} else if (random_int == 1) {
			// 안내문구
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)에게 말을 걸었는데 반응이 없다 ㅠㅠ \n (호감도-2, 진화게이지+1)");
			// 해당 이벤트에 대한 드래곤의 상태값 변화
			Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
			downcast_dragon_egg.likeable-=2;
			downcast_dragon_egg.evolution++;
			MainFrame.dragon = downcast_dragon_egg;
			
		} else {
			// 안내문구
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)에게 말을 걸었더니 살짝 움직인다 \n (호감도+1, 진화게이지+1)");
			// 해당 이벤트에 대한 드래곤의 상태값 변화
			Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
			downcast_dragon_egg.likeable++;
			downcast_dragon_egg.evolution++;
			MainFrame.dragon = downcast_dragon_egg;
		
		}
	}

	// 진화조건 체크
	public boolean is_evolution() {
		Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
		byte likeable_7 = downcast_dragon_egg.likeable;
		byte evolution_4 = downcast_dragon_egg.evolution;
		// 호감도 7 이상이면 진화
		if (likeable_7 > 7) {
			// 메인 프레임의 dragon 변수가 가리키는 인스턴스 Hatchling 으로 바꿔주기
			// Egg 상태일 때 인스턴스의 상태값 반영하기 위해 인자로 넘겨주기
			MainFrame.dragon = new Hatchling(downcast_dragon_egg);
			// 안내문구
			DialoguePanel.insert_dialogue("호감도 " + likeable_7 + "로 성장!");
			// true 반환 시 드래곤 성장
			return true;
			// 진화게이지 4 이상이면 진화
		} else if (evolution_4 > 4) {

			MainFrame.dragon = new Hatchling(downcast_dragon_egg);
			// 안내문구
			DialoguePanel.insert_dialogue("진화게이지 " + evolution_4 + "로 성장!");
			return true;
		}
		return false;

	}

// 초기
	public Egg() {
		// 호감도
		likeable = 0;
		// 진화 게이지
		evolution = 0;
		// 이름
		name = null;
	}

}
