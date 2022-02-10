package enums;

// PaintManager에서 그려줄 배경 상태를 정의한 enum

// 순서대로: 로딩창때 보여줄 배경, 집일때 보여줄 배경, 싸우기메서드 호출했을 때 보여줄 배경, 비행연습 메서드 호출시 보여줄 배경
// 해피엔딩시 보여줄 배경, 노말엔딩시 보여줄 배경, 세드엔딩시 보여줄 배경
public enum BackGround {
	LOADING, HOME, FIGHT, FLIGHT, HAPPY_END, NORMAL_END, SAD_END
}
