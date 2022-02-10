package other;

import java.util.Random;

import GuiRelatedClass.DragonStatePanel;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.Growth;

// 드래곤의 성장단계가 Egg 일 때 집의 온도를 관리해줄 Home 클래스
public class Home {
	// 집의 온도
	// 집의 온도는 드래곤의 성별과 알 상태일 때 사망 여부에 영향을 줍니다.
	public byte home_degree;

	// 집온도조절 메서드
	// 온도 올리기 메서드
	public void increase_degree() {
		home_degree += 2;
	}

	// 온도 내리기 메서드
	public void decrease_degree() {
		home_degree -= 2;
	}

	public Home() {
		home_degree = 30;
		// 집온도 변화 스레드 호출
		Thread home_thread = new Thread(new HomeWithThread());
		home_thread.start();
	}

	// 집 온도 변화 스레드
	// 0.5 초 간격으로 랜덤하게 온도가 내려가거나 올라갑니다.
	class HomeWithThread implements Runnable {
		@Override
		public void run() {
			Random random = new Random();
			boolean is_egg = true;
			while (is_egg) {

				try {
					// 0.5 초에 한번씩 집 온도 상승 혹은 하락
					Thread.sleep(500);
					// 성장상태가 Hatchling일 때 스레드 종료
					if (PaintManager.stage == Growth.HATCHLING) {
						is_egg = false;
						MainFrame.home = null;
						break;
					}
					byte random_byte = (byte) random.nextInt(2);
					// 온도 하락
					if (random_byte == 0) {
						home_degree -= 2;

						// 온도 상승
					} else if (random_byte == 1) {
						home_degree += 2;
					}
					if (MainFrame.home.home_degree <= 27) {
						DragonStatePanel.insert_dialogue("<html>"+MainFrame.dragon.name + "<br>(이)가 추워해요!</html>");
					} else if(MainFrame.home.home_degree>=33){
						DragonStatePanel.insert_dialogue("<html>"+MainFrame.dragon.name + "<br>(이)에게 말을 걸어주세요!</html>");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			;
		}
	}
}