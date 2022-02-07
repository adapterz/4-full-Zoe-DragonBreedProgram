package Dragon;

//최상위 부모
abstract public class Dragon {
	// 이름
	public String name;
	// 호감도
	public byte likeable;
	// 진화 게이지
	public byte evolution;

	// 진화 조건 만족 체크(진화 조건 만족하면 true 반환)
	abstract public boolean is_evolution();

}
