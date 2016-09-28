package net;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import util.FileOutputUtil;
import bean.Task;

/**
 * 工作线程 消息队列取出的消息的执行者
 */
public class WorkThread extends Thread {
	private Task mTask;
	public static String baseUrl = "http://xjh.haitou.cc";
	public static String target = ".*(android|Android|安卓|移动开发).*";

	// public static String target = ".*(前端设计|web前端|前端工程师|网页设计|前端).*";

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
				System.out.println("发现+1");
			}
		} catch (IOException e) {
			FileOutputUtil.outputError(mTask.toString() + "" + e.getMessage()
					+ "\n---------end---------\n");
		}
	}
}
