package Component.TodoList;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Component.DragonStatus;
import Component.PaintManager;
import Dragon.Juvenile;
import Main.MainFrame;
import other.BackGround;
import other.Growth;
// Juvenile 상태일 때 할 수 있는 일 패널
public class JuvenileTodoList extends TodoList {
	
	// 누르면 Juvenile 클래스 메서드 호출할 버튼 생성
	private JButton stroke;
	private JButton flight;
	private JButton fight;
	private JComboBox feed;
	static private boolean is_evolution = false;

	@SuppressWarnings("unchecked")
	public JuvenileTodoList() {
		super();
		// 할일버튼 만들기
		// 비행연습
		flight = new JButton("비행연습");
		flight.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		flight.setBackground(SystemColor.controlHighlight);
		flight.setBounds(56, 134, 98, 28);
		add(flight);
		// 전투
		fight = new JButton("전투경험");
		fight.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		fight.setBackground(SystemColor.controlHighlight);
		fight.setBounds(56, 94, 98, 28);
		add(fight);
		// 쓰다듬기
		stroke = new JButton("쓰다듬기");
		stroke.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		stroke.setBackground(SystemColor.controlHighlight);
		stroke.setBounds(56, 54, 98, 28);
		add(stroke);
		// 상태창
		JButton status = new JButton("상태창");
		status.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 12));
		status.setBackground(SystemColor.controlHighlight);
		status.setBounds(56, 214, 98, 28);
		add(status);

		// 먹이기
		String[] kind_of_eat = { "먹이기", "과일", "슈퍼웜", "작은동물" };
		feed = new JComboBox(kind_of_eat);
		feed.setFont(new Font("210 맨발의청춘", Font.BOLD, 12));
		feed.setBounds(56, 174, 98, 28);
		add(feed);

		// 콤보박스 이벤트
		feed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					Juvenile j_dragon = (Juvenile) MainFrame.dragon;

					// 콤보박스에서 선택한 항목 String으로 가져오기
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
		// 비행연습
		flight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// 알림문구 안보이게하기
				MainFrame.pack_back.remove(MainFrame.dialogue_panel);
				// 할일목록 안보이게 하기
				setVisible(false);
				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// 배경패널이 비행 배경 그려주게 하기
				PaintManager.background = BackGround.FLIGHT;
				MainFrame.main_background.repaint();
				
				downcast_dragon_juvenile.flying_practic();

				// 알림문구 보이게하기
				MainFrame.dialogue_panel.setVisible(true);

				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_juvenile.is_evolution();
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

				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				// 할일목록 안보이게 하기
				setVisible(false);
				// 배경 패널이 비행 배경 그려주게 하기
				PaintManager.background = BackGround.FIGHT;
				MainFrame.main_background.repaint();
				
				downcast_dragon_juvenile.fight();

				// 할일목록 보이게 하기
				// setVisible(true);
				// 알림문구 보이게하기
				MainFrame.dialogue_panel.setVisible(true);
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_juvenile.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// 쓰다듬기
		stroke.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Juvenile downcast_dragon_juvenile = (Juvenile) MainFrame.dragon;
				downcast_dragon_juvenile.stroke();
				// 진화조건 만족했는지 체크
				is_evolution = downcast_dragon_juvenile.is_evolution();
				if (is_evolution) {
					go_evolution();
				}
			}

		});
		// 상태창
		status.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// 상태창 새 윈도우로 띄워주기
				new DragonStatus();

			}

		});
	}

	private void go_evolution() {

		// 알림문구 안보이게하기
		MainFrame.pack_back.remove(MainFrame.dialogue_panel);
		// 이 클래스의 할일 목록 안보이게하기
		setVisible(false);
		MainFrame.todolist_juvenile = null;
		// 어른 드래곤 그려주기
		PaintManager.stage = Growth.ADULT;

		// 진화 알림창
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 어른이 됐어요!");
		// 드래곤 객체의 호감도에 따라 다른 엔딩 분기처리
		// 해피엔딩
		if (MainFrame.dragon.likeable > 23) {
			PaintManager.background = BackGround.HAPPY_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 계속 함께하고 싶다네요! 해피엔딩~");
		}
		// 세드엔드
		else if (MainFrame.dragon.likeable < 7) {
			PaintManager.background = BackGround.SAD_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 아무말 없이 떠났어요. 세드엔딩");
		}
		// 노말엔드
		else {
			PaintManager.background = BackGround.NORMAL_END;
			MainFrame.main_background.repaint();
			JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 키워줘서 고맙고 세상구경을 하고 싶대요. 노말엔딩!");
		}

		remove(this);

	}
}