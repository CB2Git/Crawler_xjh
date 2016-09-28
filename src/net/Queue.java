package net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import bean.Task;

/**
 * ��Ϣ����
 */
public class Queue {
	// �����������������
	public int MAX_TASK = 100;
	// ����߳�����
	public int MAX_THREAD = 4;
	// �̳߳�
	public final ExecutorService threadPool = Executors
			.newFixedThreadPool(MAX_THREAD);

	public Queue() {
	}

	/**
	 * ֹͣ
	 */
	public void shutdown() {
		threadPool.shutdown();
	}

	/**
	 * ������񵽶���
	 */
	public void addTask(Task task) {
		threadPool.execute(new WorkThread(task));
	}


}
