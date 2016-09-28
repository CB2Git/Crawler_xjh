package xjh;

import java.io.IOException;
import net.HelpThread;
import net.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		Queue queue = new Queue();
		new HelpThread(queue).start();
	}
}
