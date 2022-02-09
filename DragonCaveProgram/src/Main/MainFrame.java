package Main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dragon.Dragon;
import Dragon.Egg;
import GuiRelatedClass.DialoguePanel;
import GuiRelatedClass.DragonAttackPanel;
import GuiRelatedClass.MonsterAttackPanel;
import GuiRelatedClass.PackBackGround;
import GuiRelatedClass.PaintManager;
import enums.BackGround;
import enums.Growth;
import other.Home;

public class MainFrame {

	// 드래곤
	// 다형성 이용할 변수
	public static Dragon dragon;
	// 환경 변수
	public static Home home;
	// 메인프레임 및 배경패널
	public static JFrame main_frame;
	public static PaintManager main_background;
	public static DialoguePanel dialogue_panel;
	public static DragonAttackPanel dragon_attack_panel;
	public static MonsterAttackPanel monster_attack_panel;
	// 배경컴포넌트들 하나로 묶을 컨테이너
	public static PackBackGround pack_back;
	// 할일 메뉴창
	public static GuiTodoList.EggTodoList todolist_egg;
	public static GuiTodoList.HatchlingTodoList todolist_hatchling;
	public static GuiTodoList.JuvenileTodoList todolist_juvenile;
	// 로그 남기기 위한 변수
	public static final Logger LOG = Logger.getGlobal();

	// 프로그램 시작
	public static void main(String[] args) {
		// 메인 스레드
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// 로그
				LOG.info("프로그램 시작");
				try {
					MainFrame window = new MainFrame();
					window.main_frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	// @SuppressWarnings("removal")
	private void initialize() {
		// 메인프레임
		main_frame = new JFrame();
		// 컨테이너 생성
		pack_back = new PackBackGround();
		main_frame.add(pack_back);
		// 배경 패널
		main_background = new PaintManager();
		main_background.setVisible(true);
		main_background.setLayout(null);
		// 대화 패널
		dialogue_panel = new DialoguePanel("");
		dialogue_panel.setVisible(false);
		
		// 싸움알림 패널
		dragon_attack_panel = new DragonAttackPanel("");
		dragon_attack_panel.setVisible(false);
		monster_attack_panel = new MonsterAttackPanel("");
		monster_attack_panel.setVisible(false);
	
		// 할일목록 패널
		todolist_egg = new GuiTodoList.EggTodoList();
		todolist_egg.setVisible(false);
		main_background.add(todolist_egg);

		// 메인프레임 설정
		main_frame.setResizable(false);
		main_frame.setTitle("Dragon Cave - fan program");
		main_frame.setBounds(100, 100, 1280, 750);
		main_frame.setLocationRelativeTo(null);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 컨테이너에 컴포넌트 추가 시 순서 설정
		pack_back.add(main_background, new Integer(0), 0);
		pack_back.add(dialogue_panel, new Integer(1), 0);
		pack_back.add(dragon_attack_panel, new Integer(1), 0);

		main_frame.pack();

		// KeyListner 이벤트 생성
		main_frame.addKeyListener(new KeyListner());
		// 초기화
		dragon = null;
		home = new Home();
		
	}
}

// frame에 부착할 키 이벤트
class KeyListner extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		// esc 누르면 종료
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		// 로딩창일때 엔터 누르면 다음 단계로 넘어가기
		// 로딩 배경일 때만 키 이벤트 발생
		case KeyEvent.VK_ENTER:
			if (PaintManager.background == BackGround.LOADING) {

				// 이름설정 창
				new SetName();
				// dragon 변수가 Egg 인스턴스 가리키게 하기
				MainFrame.dragon = new Egg();
				// 알 그림 그려주게 하기
				PaintManager.stage = Growth.EGG;
			}
			break;
		}

	}

// 로딩창->메인 게임화면 넘어갈 때 호출할 이름입력 창 
	class SetName extends JFrame {

		// 생성자
		public SetName() {
			// 기본 프레임 설정
			setVisible(true);
			setTitle("Set Dragon's Name");
			setResizable(false);
			setSize(300, 150);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			// 판넬(라벨+텍스트필드)
			JPanel name_panel = new JPanel();
			JLabel name_label = new JLabel("드래곤 이름:");
			JTextField name_field = new JTextField(10);
			JButton name_input = new JButton("입력");

			name_input.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// 텍스트 필드에서 입력한 이름 가져와서 dragon 변수가 가리키는 인스턴스의 name 필드에 할당
					MainFrame.dragon.name = name_field.getText().toString();
					// dragon_main.change_status();

					// 그려줄 상태 바꾸고 repaint().
					PaintManager.background = BackGround.HOME;
					MainFrame.main_background.repaint();
					MainFrame.todolist_egg.setVisible(true);
					// 집 환경
					MainFrame.home = new Home();
					// 창 종료
					dispose();
				}

			});

			name_panel.add(name_label);
			name_panel.add(name_field);
			name_panel.add(name_input);
			add(name_panel);
		}

	}
}