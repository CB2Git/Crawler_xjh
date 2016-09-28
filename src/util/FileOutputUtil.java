package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import bean.Task;

public class FileOutputUtil {
	// ������־����λ��
	public static String err_log = "D:\\��Ͷ��\\log.txt";
	//��˾��Ϣ����λ��
	public static String data = "D:\\��Ͷ��\\";
	/**
	 * ׷��text��ָ���ļ�
	 */
	public static boolean output(String filePath, String text) {
		boolean result = false;
		File file = new File(filePath);
		try {
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(file, true), "utf-8");
			writer.append(text);
			writer.close();
			result = true;
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return result;
	}

	/**
	 * ���������Ϣ
	 */
	public static boolean outputError(String errlog) {
		return output(err_log, errlog);
	}
	
	/**
	 * �������Ŀ��������ļ�
	 */
	public static boolean outputTask(Task task,String context)
	{
		String filePath = String.format("%s%s-%s-%s-%s.txt", data,task.time.replace(":", "��"),task.school,task.addr,task.name);
		//System.out.println(filePath);
		return output(filePath, "����:"+task.url + "\n\n\n\n"+context);
	}
}
