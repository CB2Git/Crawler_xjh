package net;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.Task;

/**
 * 辅助线程，启动以后解析主界面，然后将任务提交到消息队列
 */
public class HelpThread extends Thread {
	public String url = "http://xjh.haitou.cc/wh/page-";
	public String baseUrl = "http://xjh.haitou.cc";
	public int count = 0;
	private Queue mQueue;

	public HelpThread(Queue queue) {
		this.mQueue = queue;
	}

	@Override
	public void run() {
		super.run();
		int nowPage = 1;
		// 一直循环，直到没有下一页
		while (prasePage(nowPage)) {
			nowPage++;
		}
		System.out.println("搜索完毕,搜索总数:" + count);
		mQueue.shutdown();
	}

	private boolean prasePage(int page) {
		// 不使用Jousp.connect()方法，因为可能出现乱码
		Document doc = null;
		try {
			doc = Jsoup.parse(new URL(url + page).openStream(), "utf-8",
					baseUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 查找table下的tr
		Elements trs = doc.select(".table tr");
		// 遍历所有tr
		for (Element tr : trs) {
			Task task = initTask(tr);
			if (task != null) {
				mQueue.addTask(task);
				count++;
			}
		}
		//System.out.println("搜索页码:" + page + "count = " + count);
		return hasNextPage(doc);
	}

	/**
	 * 返回是否有下一页
	 */
	private boolean hasNextPage(Document doc) {
		boolean hasNext = doc.select("li.next").size() > 0;
		return hasNext;
	}

	private Task initTask(Element tr) {
		// 找出学校 公司 宣讲会位置 公司详情地址
		Elements detail = tr.select("td.cxxt-title");
		Elements result = detail.select("a");
		if (result.size() <= 0)
			return null;
		// 绝对地址
		String url = result.attr("abs:href");
		String title = result.attr("title");
		String[] split = title.split("\\s");
		// 找出时间
		String time = tr.select("span.hold-ymd").text();
		return new Task(url, split[0], split[1].replace(" 学校：", ""),
				split[2].replace("地点：", ""),time);
	}
}
