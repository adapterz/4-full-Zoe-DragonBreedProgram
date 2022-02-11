package Dragon;

/* 	�ֻ����θ�				Dragon (�߻�)
 * 	1����� 		Egg ��   		Reptile (�����,�߻�)
 *  2����� 				  Hatchling(����), Juvenile(�����)
 *  
 *  �巡�� ����ܰ�: Egg -> Hatchling -> Juvenile -> Adult
	�������: Adult Ŭ������ ���� �������� ����
 */

// �巡�� Ű��� ���α׷��� �巡�� ��ü�� �������� �ֻ��� Ŭ�����Դϴ�. 
// Dragon Ŭ������ ��ӹ��� Ŭ�������� ��� Dragon�� ����ܰ踦 ��Ÿ���ϴ�.
abstract public class Dragon {
	// �̸�
	public String name;
	// ȣ����
	public byte likeable;
	// ��ȭ ������
	public byte evolution;

	// ��ȭ ���� ���� üũ(��� �ൿ �޼��� ���� �� �޼��带 ȣ���ؼ� ��ȭ ���� �����ϸ� ����)
	abstract public boolean is_evolution();

}
