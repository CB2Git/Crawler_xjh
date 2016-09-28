package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import bean.Task;

public class FileOutputUtil {
	// 错误日志保存位置
	public static String err_log = "D:\\海投网\\log.txt";
	//公司信息保存位置
	public static String data = "D:\\海投网\\";
	/**
	 * 追加text到指定文件
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
	 * 输出错误信息
	 */
	public static boolean outputError(String errlog) {
		return output(err_log, errlog);
	}
	
	/**
	 * 输出符合目标的任务到文件
	 */
	public static boolean outputTask(Task task,String context)
	{
		String filePath = String.format("%s%s-%s-%s-%s.txt", data,task.time.replace(":", "点"),task.school,task.addr,task.name);
		//System.out.println(filePath);
		return output(filePath, "链接:"+task.url + "\n\n\n\n"+context);
	}
}
