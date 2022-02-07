package Component.TodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import Component.DragonStatus;
import Component.PaintManager;
import Dragon.Hatchling;
import Main.MainFrame;
import other.BackGround;
import other.Growth;
// Hatchling 상태일 때 할 수 있는 일 패널
public class HatchlingTodoList extends TodoList {

	// 누르면 Hatchling 클래스 메서드 호출할 버튼 생성
	private JButton wash;
	private JButton stroke;
	private JButton fight;
	private JComboBox feed;
	static private boolean is_evolution = false;

	@SuppressWarnings("unchecked")
	public HatchlingTodoList() {
		super();

		// 할일버튼 만들기
		// 쓰다듬기
		stroke = new JButton("쓰다듬기");
		stroke.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		stroke.setBackground(SystemColor.controlHighlight);
		stroke.setBounds(56, 134, 98, 28);
		add(stroke);
		// 전투
		fight = new JButton("전투경험");
		fight.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		fight.setBackground(SystemColor.controlHighlight);
		fight.setBounds(56, 94, 98, 28);
		add(fight);
		// 씻기기
		wash = new JButton("씻기기");
		wash.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		wash.setBackground(SystemColor.controlHighlight);
		wash.setBounds(56, 54, 98, 28);
		add(wash);
		// 상태창
		JButton status = new JButton("상태창");
		status.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		status.setBackground(SystemColor.controlHighlight);
		status.setBounds(56, 214, 98, 28);
		add(status);

		// 먹이기
		String[] kind_of_eat = { "먹이기", "과일", "피닉스웜", "귀뚜라미" };
		feed = new JComboBox(kind_of_eat);
		feed.setFont(new Font("210 맨발의청춘", Font.BOLD, 12));
		feed.setBounds(56, 174, 98, 28);
		add(feed);

		// 콤보박스 이벤트
		feed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// 편집모드에서 선택하면 2번 이벤트 전달됨->
				// 아래같이 하면 방지하기 위해 조건문(선택되는 경우만 이벤트 처리)
				if (e.getStateChange() == 1) {
					Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
					String what_eat = feed.getSelectedItem().toString();
					downcast_dragon_hatchling.feed(what_eat);
					// 진화조건 만족했는지 체크
					is_evolution = downcast_dragon_hatchling.is_evolution();
					if (is_evolution) {
						// 진화조건 만족했으면 진화 메서드 호출 
						go_evolution();
					}
				}
			}

		});

		// 버튼 이벤트
		// 쓰다듬기
		stroke.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// 쓰다듬기 함수 호출
				downcast_dragon_hatchling.stroke();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_hatchling.is_evolution();
				if (is_evolution) {
					go_evolution();
				}

			}

		});
		// 싸우기
		fight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 알림문구 안보이게하기
				MainFrame.pack_back.remove(MainFrame.dialogue_panel);
				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// 할일목록 안보이게하기
				setVisible(false);
				// 배경패널의 상태 바꿔서 다시 그려주기
				PaintManager.background = BackGround.FIGHT;
				MainFrame.main_background.repaint();

				// 싸움함수 호출
				downcast_dragon_hatchling.fight();

				// 할일목록 보이게하기
				// setVisible(true);
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_hatchling.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// 씻기기
		wash.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Hatchling downcast_dragon_hatchling = (Hatchling) MainFrame.dragon;
				// 씻기기함수 호출
				downcast_dragon_hatchling.wash();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_hatchling.is_evolution();
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

	private void go_evolution() {
		// 이 클래스의 할일 목록 안보이게하기
		setVisible(false);
		MainFrame.todolist_hatchling = null;
		// 그려줄 드래곤 상태 바꿔주기(드래곤 성장)
		PaintManager.stage = Growth.JUVENILE;
		MainFrame.main_background.repaint();
		// 새 할일 목록 생성(쥬버나일 상태일 때 할 수 있는 일 목록으로 갱신)
		MainFrame.todolist_juvenile = new JuvenileTodoList();
		MainFrame.main_background.add(MainFrame.todolist_juvenile);

		remove(this);
	}
}