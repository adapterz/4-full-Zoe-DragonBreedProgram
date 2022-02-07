package other;

import java.util.Random;

import Component.PaintManager;
import Main.MainFrame;

// ��
public class Home {
	// �µ�
	public byte home_degree;
	
	// �µ�����
	// �µ��÷��ֱ�
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
					// ������°� Hatchling�� �� ������ ����
					if (PaintManager.stage == Growth.HATCHLING) {
						MainFrame.home = null;
						break;
					}
					// 0.5 �ʿ� �ѹ��� �µ� ��� Ȥ�� �϶�
					Thread.sleep(500);
					byte random_byte = (byte) random.nextInt(2);
						// �µ� �϶�
					if (random_byte == 0) {
						home_degree -= 2;
					
						// �µ� ���
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