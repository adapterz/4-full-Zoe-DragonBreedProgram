package GuiTodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import Dragon.Hatchling;
import GuiRelatedClass.DragonStatus;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.BackGround;
import enums.Growth;

// 드래곤의 성장단계가 '유아기'상태일 때 행동 메서드를 호출할 수 있는 버튼들과 상태창 버튼을 포함한 패널
public class HatchlingTodoList extends TodoList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9188690795585243354L;
	// 누르면 Hatchling 클래스 행동 메서드 호출할 버튼 생성
	private JButton wash;
	private JButton stroke;
	private JButton fight;
	private JComboBox feed;
	// 진화요건 달성여부 (매 버튼이벤트 이후 진화요건 성립여부 체크)
	static private boolean is_evolution = false;

	@SuppressWarnings("unchecked")
	public HatchlingTodoList() {
		super();

		// 행동 메서드 호출할 버튼
		// Hatchling 클래스의 쓰다듬기 메서드 호출할 버튼
		stroke = new JButton("쓰다듬기");
		stroke.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		stroke.setBackground(SystemColor.controlHighlight);
		stroke.setBounds(56, 134, 98, 28);
		add(stroke);
		// Hatchling 클래스의 싸우기 메서드 호출할 버튼
		fight = new JButton("전투경험");
		fight.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		fight.setBackground(SystemColor.controlHighlight);
		fight.setBounds(56, 94, 98, 28);
		add(fight);
		// Hatchlign 클래스의 씻기기 메서드 호출할 버튼
		wash = new JButton("씻기기");
		wash.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		wash.setBackground(SystemColor.controlHighlight);
		wash.setBounds(56, 54, 98, 28);
		add(wash);
		// 상태창 버튼 (DragonStatus 인스턴스 호출)
		JButton status = new JButton("상태창");
		status.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		status.setBackground(SystemColor.controlHighlight);
		status.setBounds(56, 214, 98, 28);
		add(status);

		// Hathcling 클래스의 드래곤에게 밥주기 메서드를 호출할 콤보박스
		// 선택 요소를 메서드의 인자로 넘겨주기
		String[] kind_of_eat = { "먹이기", "과일", "피닉스웜", "귀뚜라미" };
		feed = new JComboBox(kind_of_eat);
		feed.setFont(new Font("210 맨발의청춘", Font.BOLD, 12));
		feed.setBounds(56, 174, 98, 28);
		add(feed);

		// 밥주기 메서드 호출할 콤보박스 이벤트
		feed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
					// 콤보박스 선택요소 인자로 넘겨  Hatchling 클래스의 feed 메서드 호출
					String what_eat = feed.getSelectedItem().toString();
					downcast_dragon_hatchling.feed(what_eat);
					// 진화조건 만족했는지 체크
					is_evolution = downcast_dragon_hatchling.is_evolution();
					// 진화조건 만족했으면 진화 메서드 호출
					if (is_evolution) {
						go_evolution();
					}
				}
			}

		});

		// 버튼 이벤트
		// 쓰다듬기 버튼의 이벤트
		stroke.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// Hatchling 클래스의 쓰다듬기 메서드 호출
				downcast_dragon_hatchling.stroke();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_hatchling.is_evolution();
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
				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// 해당 인스턴스 안보이게하기 (버튼 패널 안보이게하기)
				setVisible(false);
				// 싸우기 배경패널 보여주기
				PaintManager.background = BackGround.FIGHT;
				MainFrame.main_background.repaint();

				//  Hatchling 클래스의 싸우기 메서드 호출
				downcast_dragon_hatchling.fight();

				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_hatchling.is_evolution();
				// 진화조건 만족했을 때 진화 메서드 호출
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// 씻기기 버튼의 이벤트
		wash.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				//  Hatchling 클래스의 씻기기 메서드 호출
				downcast_dragon_hatchling.wash();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_hatchling.is_evolution();
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
	// 해당 TodoList 인스턴스 제거, 드래곤의 성장단계에 맞는 새로운 TodoList 인스턴스 생성
	private void go_evolution() {
		// 해당 인스턴스 안보이게하기
		setVisible(false);
		MainFrame.todolist_hatchling = null;
		// PaintManager 클래스에서 그려줄 드래곤 상태 바꿔주기(드래곤 성장)
		PaintManager.stage = Growth.JUVENILE;
		MainFrame.main_background.repaint();
		// 새 TodoList 생성(드래곤의 성장단계 성장기 상태일 때 패널로 갱신)
		MainFrame.todolist_juvenile = new JuvenileTodoList();
		MainFrame.main_background.add(MainFrame.todolist_juvenile);

		remove(this);
	}
}