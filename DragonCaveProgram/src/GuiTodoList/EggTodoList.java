package GuiTodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Dragon.Egg;
import GuiRelatedClass.DragonStatus;
import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.Growth;

// 드래곤의 성장단계가 '알'상태일 때 행동 메서드를 호출할 수 있는 버튼들과 상태창 버튼을 포함한 패널
public class EggTodoList extends TodoList {
	// 누르면 Egg 클래스의 행동 메서드 호출할 버튼 생성
	private JButton decrease_degree_control;
	private JButton increase_degree_control;
	private JButton talk_to;
	// 진화요건 달성여부 (매 버튼이벤트 이후 진화요건 성립여부 체크)
	static private boolean is_evolution = false;

	public EggTodoList() {
		super();

		// 행동 메서드 호출할 버튼
		// Egg클래스의 알에게 말하기 메서드 호출할 버튼
		talk_to = new JButton("말하기");
		talk_to.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		talk_to.setBackground(SystemColor.controlHighlight);
		talk_to.setBounds(56, 134, 98, 28);
		add(talk_to);
		// 집 온도 조절 버튼
		// Egg 클래스의 집 온도 상승 메서드 호출할 버튼
		increase_degree_control = new JButton("온도 상승");
		increase_degree_control.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		increase_degree_control.setBackground(SystemColor.controlHighlight);
		increase_degree_control.setBounds(56, 94, 98, 28);
		add(increase_degree_control);
		// Egg 클래스의 집 온도 하락 메서드 호출할 버튼
		decrease_degree_control = new JButton("온도 하락");
		decrease_degree_control.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		decrease_degree_control.setBackground(SystemColor.controlHighlight);
		decrease_degree_control.setBounds(56, 54, 98, 28);
		add(decrease_degree_control);

		// 상태창 버튼 (DragonStatus 인스턴스 호출)
		JButton dragon_status = new JButton("상태창");
		dragon_status.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		dragon_status.setBackground(SystemColor.controlHighlight);
		dragon_status.setBounds(56, 174, 98, 28);
		add(dragon_status);

		// 버튼들의 이벤트 정의
		// 말하기 버튼의 이벤트
		talk_to.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.talk_to();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_egg.is_evolution();
				// 진화 조건 만족하면 진화 메서드 호출
				if (is_evolution) {
					go_evolution();
				}

			}

		});
		// 집 온도 조절
		// 온도 상승 버튼의 이벤트
		increase_degree_control.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.increase_degree_control();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_egg.is_evolution();
				// 진화조건 만족했을 때 진화 메서드 호출
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// 온도 하락 버튼의 이벤트
		decrease_degree_control.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.decrease_degree_control();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_egg.is_evolution();
				// 진화조건 만족했을 때 진화 메서드 호출
				if (is_evolution) {
					go_evolution();
				}
			}

		});

		// 상태창 버튼 이벤트(DragonStatus 클래스에 정의한 윈도우창을 띄워줄 버튼)
		dragon_status.addActionListener(new ActionListener() {

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
		MainFrame.todolist_egg = null;
		// PaintManager 클래스에서 그려줄 드래곤 상태 바꿔주기(드래곤 성장)
		PaintManager.stage = Growth.HATCHLING;
		MainFrame.main_background.repaint();
		// 새 TodoList 생성(드래곤의 성장단계 유아기 상태일 때 패널로 갱신)
		MainFrame.todolist_hatchling = new HatchlingTodoList();
		MainFrame.main_background.add(MainFrame.todolist_hatchling);

		remove(this);
	}
}
