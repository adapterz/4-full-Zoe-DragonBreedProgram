package Component.TodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Component.DragonStatus;
import Component.PaintManager;
import Dragon.Egg;
import Main.MainFrame;
import other.Growth;

// Egg 상태일 때 할 수 있는 일 패널
public class EggTodoList extends TodoList {
	// 누르면 Egg 클래스 메서드 호출할 버튼 생성
	private JButton decrease_degree_control;
	private JButton increase_degree_control;
	private JButton talk_to;
	static private boolean is_evolution = false;

	public EggTodoList() {

		// 부모 생성자 호출
		super();

		// 할일 버튼
		// 말하기 버튼
		talk_to = new JButton("말하기");
		talk_to.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		talk_to.setBackground(SystemColor.controlHighlight);
		talk_to.setBounds(56, 134, 98, 28);
		add(talk_to);
		// 온도 조절 버튼
		// 온도 상승
		increase_degree_control = new JButton("온도 상승");
		increase_degree_control.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		increase_degree_control.setBackground(SystemColor.controlHighlight);
		increase_degree_control.setBounds(56, 94, 98, 28);
		add(increase_degree_control);
		// 온도하락
		decrease_degree_control = new JButton("온도 하락");
		decrease_degree_control.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		decrease_degree_control.setBackground(SystemColor.controlHighlight);
		decrease_degree_control.setBounds(56, 54, 98, 28);
		add(decrease_degree_control);

		// 상태창 버튼
		JButton status = new JButton("상태창");
		status.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		status.setBackground(SystemColor.controlHighlight);
		status.setBounds(56, 174, 98, 28);
		add(status);

		// 버튼 이벤트 정의
		// 말하기
		talk_to.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.talk_to();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_egg.is_evolution();
				if (is_evolution) {
					// 진화 조건 만족하면 진화 메서드 호출
					go_evolution();
				}

			}

		});
		// 온도 조절
		// 온도 상승
		increase_degree_control.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.increase_degree_control();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_egg.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// 온도 하락
		decrease_degree_control.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Egg downcast_dragon_egg = (Egg) MainFrame.dragon;
				downcast_dragon_egg.decrease_degree_control();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_egg.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});

		// 상태창
		status.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DragonStatus();

			}

		});
	}

	// 진화 메서드
	private void go_evolution() {
		// 이 클래스의 할일 목록 안보이게하기
		setVisible(false);
		MainFrame.todolist_egg = null;
		// 그려줄 드래곤 상태 바꿔주기(드래곤 성장)
		PaintManager.stage = Growth.HATCHLING;
		MainFrame.main_background.repaint();
		// 새 할일 목록 생성(헤츨링 상태일 때 할 수 있는 일 목록으로 갱신)
		MainFrame.todolist_hatchling = new HatchlingTodoList();
		MainFrame.main_background.add(MainFrame.todolist_hatchling);

		remove(this);
	}
}
