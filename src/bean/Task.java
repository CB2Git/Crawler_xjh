package bean;

/**
 * ����������
 */
public class Task {
	// ��˾����url
	public String url;
	// ��˾����
	public String name;
	// ѧУ����
	public String school;
	// ѧУ����ص�
	public String addr;
	// ������ʱ��
	public String time;

	public Task(String url, String name, String school, String addr, String time) {

		this.url = url;
		this.name = name;
		this.school = school;
		this.addr = addr;
		this.time = time;
	}

	@Override
	public String toString() {

		return String.format("��˾:%s\n�����ַ:%s\nѧУ:%s\n����ص�:%s\nʱ��:%s\n", name,
		url, school, addr, time);
	}
}
