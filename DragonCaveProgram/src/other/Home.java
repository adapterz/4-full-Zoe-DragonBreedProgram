package other;

import java.util.Random;

import Component.PaintManager;
import Main.MainFrame;

// 집
public class Home {
	// 온도
	public byte home_degree;
	
	// 온도조절
	// 온도올려주기
	public void increase_degree() {
		home_degree += 2;
	}

	public void decrease_degree() {
		home_degree -= 2;
	}

	public Home() {
		home_degree = 33;
		Thread home_thread = new Thread (new HomeWithThread());
		home_thread.start();
	}
	
	class HomeWithThread implements Runnable {
		@Override
		public void run() {
			Random random = new Random();
			while (true) {

				try {
					// 성장상태가 Hatchling일 때 스레드 종료
					if (PaintManager.stage == Growth.HATCHLING) {
						MainFrame.home = null;
						break;
					}
					// 0.5 초에 한번씩 온도 상승 혹은 하락
					Thread.sleep(500);
					byte random_byte = (byte) random.nextInt(2);
						// 온도 하락
					if (random_byte == 0) {
						home_degree -= 2;
					
						// 온도 상승
					} else if (random_byte == 1) {
						home_degree += 1;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			;
		}
	}
}