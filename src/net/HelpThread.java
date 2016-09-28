package net;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.Task;

/**
 * �����̣߳������Ժ���������棬Ȼ�������ύ����Ϣ����
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
		// һֱѭ����ֱ��û����һҳ
		while (prasePage(nowPage)) {
			nowPage++;
		}
		System.out.println("�������,��������:" + count);
		mQueue.shutdown();
	}

	private boolean prasePage(int page) {
		// ��ʹ��Jousp.connect()��������Ϊ���ܳ�������
		Document doc = null;
		try {
			doc = Jsoup.parse(new URL(url + page).openStream(), "utf-8",
					baseUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ����table�µ�tr
		Elements trs = doc.select(".table tr");
		// ��������tr
		for (Element tr : trs) {
			Task task = initTask(tr);
			if (task != null) {
				mQueue.addTask(task);
				count++;
			}
		}
		//System.out.println("����ҳ��:" + page + "count = " + count);
		return hasNextPage(doc);
	}

	/**
	 * �����Ƿ�����һҳ
	 */
	private boolean hasNextPage(Document doc) {
		boolean hasNext = doc.select("li.next").size() > 0;
		return hasNext;
	}

	private Task initTask(Element tr) {
		// �ҳ�ѧУ ��˾ ������λ�� ��˾�����ַ
		Elements detail = tr.select("td.cxxt-title");
		Elements result = detail.select("a");
		if (result.size() <= 0)
			return null;
		// ���Ե�ַ
		String url = result.attr("abs:href");
		String title = result.attr("title");
		String[] split = title.split("\\s");
		// �ҳ�ʱ��
		String time = tr.select("span.hold-ymd").text();
		return new Task(url, split[0], split[1].replace(" ѧУ��", ""),
				split[2].replace("�ص㣺", ""),time);
	}
}
