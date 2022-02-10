package GuiTodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Dragon.Juvenile;
import GuiRelatedClass.DragonStatus;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.BackGround;
import enums.Growth;

// 드래곤의 성장단계가 '성강기'상태일 때 행동 메서드를 호출할 수 있는 버튼들과 상태창 버튼을 포함한 패널
public class JuvenileTodoList extends TodoList {

	// 누르면 Juvenile 클래스 메서드 호출할 버튼 생성
	private JButton stroke;
	private JButton flight;
	private JButton fight;
	private JComboBox feed;
	// 진화요건 달성여부 (매 버튼이벤트 이후 진화요건 성립여부 체크)
	static private boolean is_evolution = false;

	@SuppressWarnings("unchecked")
	public JuvenileTodoList() {
		super();
		// 행동 메서드 호출할 버튼
		// Juvenile 클래스의 비행연습 메서드 호출할 버튼
		flight = new JButton("비행연습");
		flight.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		flight.setBackground(SystemColor.controlHighlight);
		flight.setBounds(56, 134, 98, 28);
		add(flight);
		// Juvenile 클래스의 싸우기 메서드 호출할 버튼
		fight = new JButton("전투경험");
		fight.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		fight.setBackground(SystemColor.controlHighlight);
		fight.setBounds(56, 94, 98, 28);
		add(fight);
		// Juvenile 클래스의 쓰다듬기 메서드 호출할 버튼
		stroke = new JButton("쓰다듬기");
		stroke.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		stroke.setBackground(SystemColor.controlHighlight);
		stroke.setBounds(56, 54, 98, 28);
		add(stroke);
		// 상태창 버튼 (DragonStatus 인스턴스 호출)
		JButton status = new JButton("상태창");
		status.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		status.setBackground(SystemColor.controlHighlight);
		status.setBounds(56, 214, 98, 28);
		add(status);

		// Juvenile 클래스의 드래곤에게 밥주기 메서드를 호출할 콤보박스
		// 선택 요소를 메서드의 인자로 넘겨주기
		String[] kind_of_eat = { "먹이기", "과일", "슈퍼웜", "작은동물" };
		feed = new JComboBox(kind_of_eat);
		feed.setFont(new Font("210 맨발의청춘", Font.BOLD, 12));
		feed.setBounds(56, 174, 98, 28);
		add(feed);

		// 밥주기 메서드 호출할 콤보박스 이벤트
		feed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					Juvenile j_dragon = (Juvenile) MainFrame.dragon;

					// 콤보박스 선택요소 인자로 넘겨 메서드 호출
					String what_eat = feed.getSelectedItem().toString();
					j_dragon.feed(what_eat);
					// 진화조건 만족했는지 체크
					is_evolution = j_dragon.is_evolution();
					if (is_evolution) {
						// 진화조건 만족시 진화 메서드 호출
						go_evolution();
					}
				}
			}

		});

		// 버튼 이벤트
		// 비행연습 버튼의 이벤트
		flight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// 집 배경일때의 알림패널 안보이게하기
				MainFrame.pack_back.remove(MainFrame.dialogue_panel);
				MainFrame.pack_back.remove(MainFrame.dragon_state_panel);
				// 해당 인스턴스 안보이게 하기 (버튼 패널 안보이게 하기)
				setVisible(false);
				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// 비행 배경 그려주기
				PaintManager.background = BackGround.FLIGHT;
				MainFrame.main_background.repaint();

				// Juvenile 클래스의 비행연습 메서드 호출
				downcast_dragon_juvenile.flying_practice();

				// 비행종료 후 집 배경의 알림내용 보이게하기
				MainFrame.dialogue_panel.setVisible(true);

				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_juvenile.is_evolution();
				// 진화조건 만족했을 때 진화 메서드 호출
				if (is_evolution) {
					go_evolution();
				}

			}

		});
		// 싸우기 버튼의 이벤트
		fight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// 집 배경일때의 알림패널 안보이게하기
				MainFrame.pack_back.remove(MainFrame.dialogue_panel);
				MainFrame.pack_back.remove(MainFrame.dragon_state_panel);
				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// 해당 인스턴스 안보이게하기 (버튼 패널 안보이게하기)
				setVisible(false);
				// 싸우기 배경패널 보여주기
				PaintManager.background = BackGround.FIGHT;
				MainFrame.main_background.repaint();

				// Juenile 클래스의 fight 메서드 호출
				downcast_dragon_juvenile.fight();

				// 싸움 종료 후 집 배경의 알림내용 보이게하기
				MainFrame.dialogue_panel.setVisible(true);
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_juvenile.is_evolution();
				// 진화조건 만족했을 때 진화 메서드 호출
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// 쓰다듬기 버튼의 이벤트
		stroke.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// Juvenile 클래스의 쓰다듬기 메서드 호출
				downcast_dragon_juvenile.stroke();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_juvenile.is_evolution();
				// 진화조건 만족했을 때 진화 메서드 호출
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// 상태창 버튼 이벤트 (DragonStatus 클래스에 정의한 윈도우창을 띄워줄 버튼)
		status.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new DragonStatus();

			}

		});
	}

	// 진화 메서드
	// 진화요건 성립했을 때 호출할 메서드 (드래곤 성장)
	// 해당 TodoList 인스턴스 제거
	// 드래곤의 필드값에 따라 다른 엔딩 보여주기
	private void go_evolution() {

		// 집 배경의 알림내용 안보이게 하기
		MainFrame.pack_back.remove(MainFrame.dialogue_panel);
		MainFrame.pack_back.remove(MainFrame.dragon_state_panel);
		// 해당 인스턴스 안보이게하기
		setVisible(false);
		MainFrame.todolist_juvenile = null;
		// PaintManager 클래스에서 그려줄 드래곤 상태 바꿔주기(드래곤 성장)
		PaintManager.stage = Growth.ADULT;
		MainFrame.main_background.repaint();

		// 진화 알림창
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 어른이 됐어요!");
		// 드래곤 객체의 호감도에 따라 다른 엔딩 분기처리
		// 해피엔딩(호감도 23 초과)
		if (MainFrame.dragon.likeable > 23) {
			// 로그
			MainFrame.LOG.info("Adult - 해피엔딩");
			// PaintManager의 그려줄 배경 상태 변경
			PaintManager.background = BackGround.HAPPY_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 계속 함께하고 싶다네요! 해피엔딩~");
		}
		// 세드엔딩(호감도 7 미만)
		else if (MainFrame.dragon.likeable < 7) {

			// 로그
			MainFrame.LOG.info("Adult - 세드엔딩");
			// PaintManager의 그려줄 배경 상태 변경
			PaintManager.background = BackGround.SAD_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 아무말 없이 떠났어요. 세드엔딩");
		}
		// 노말엔드(호감도 7~23)
		else {

			// 로그
			MainFrame.LOG.info("Adult - 노말엔딩");
			// PaintManager의 그려줄 배경 상태 변경
			PaintManager.background = BackGround.NORMAL_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 키워줘서 고맙고 세상구경을 하고 싶대요. 노말엔딩!");
		}

		// 해당 인스턴스 제거
		remove(this);

	}
}