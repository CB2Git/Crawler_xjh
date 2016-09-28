package net;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import util.FileOutputUtil;
import bean.Task;

/**
 * �����߳� ��Ϣ����ȡ������Ϣ��ִ����
 */
public class WorkThread extends Thread {
	private Task mTask;
	public static String baseUrl = "http://xjh.haitou.cc";
	public static String target = ".*(android|Android|��׿|�ƶ�����).*";

	// public static String target = ".*(ǰ�����|webǰ��|ǰ�˹���ʦ|��ҳ���|ǰ��).*";

	public WorkThread(Task mTask) {
		this.mTask = mTask;
	}

	@Override
	public void run() {
		try {
			Document doc = Jsoup.parse(new URL(mTask.url).openStream(),
					"utf-8", baseUrl);
			String context = doc.select("div.panel-body").text();
			if (context.matches(target)) {
				FileOutputUtil.outputTask(mTask, context);
				System.out.println("����+1");
			}
		} catch (IOException e) {
			FileOutputUtil.outputError(mTask.toString() + "" + e.getMessage()
					+ "\n---------end---------\n");
		}
	}
}
