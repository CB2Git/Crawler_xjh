package net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import bean.Task;

/**
 * 消息队列
 */
public class Queue {
	// 队列中最大任务数量
	public int MAX_TASK = 100;
	// 最多线程数量
	public int MAX_THREAD = 4;
	// 线程池
	public final ExecutorService threadPool = Executors
			.newFixedThreadPool(MAX_THREAD);

	public Queue() {
	}

	/**
	 * 停止
	 */
	public void shutdown() {
		threadPool.shutdown();
	}

	/**
	 * 添加任务到队列
	 */
	public void addTask(Task task) {
		threadPool.execute(new WorkThread(task));
	}


}
