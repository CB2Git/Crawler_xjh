package bean;

/**
 * 网络任务类
 */
public class Task {
	// 公司详情url
	public String url;
	// 公司名称
	public String name;
	// 学校名称
	public String school;
	// 学校具体地点
	public String addr;
	// 宣讲会时间
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

		return String.format("公司:%s\n详情地址:%s\n学校:%s\n具体地点:%s\n时间:%s\n", name,
		url, school, addr, time);
	}
}
