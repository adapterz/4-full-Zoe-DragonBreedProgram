package Dragon;

/* 	최상위부모				Dragon (추상)
 * 	1차상속 		Egg 알   		Reptile (파충류,추상)
 *  2차상속 				  Hatchling(유아), Juvenile(성장기)
 *  
 *  드래곤 성장단계: Egg -> Hatchling -> Juvenile -> Adult
	참고사항: Adult 클래스는 따로 구현하지 않음
 */

// 드래곤 키우기 프로그램의 드래곤 객체를 관리해줄 최상위 클래스입니다. 
// Dragon 클래스를 상속받은 클래스들은 모두 Dragon의 성장단계를 나타냅니다.
abstract public class Dragon {
	// 이름
	public String name;
	// 호감도
	public byte likeable;
	// 진화 게이지
	public byte evolution;

	// 진화 조건 만족 체크(모든 행동 메서드 이후 이 메서드를 호출해서 진화 조건 만족하면 성장)
	abstract public boolean is_evolution();

}
