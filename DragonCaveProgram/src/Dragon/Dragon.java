package Dragon;

//�ֻ��� �θ�
abstract public class Dragon {
	// �̸�
	public String name;
	// ȣ����
	public byte likeable;
	// ��ȭ ������
	public byte evolution;

	// ��ȭ ���� ���� üũ(��ȭ ���� �����ϸ� true ��ȯ)
	abstract public boolean is_evolution();

}
