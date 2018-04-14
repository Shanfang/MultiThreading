import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public static void main(String[] args) {
		final TestPrinter tPrinter = new TestPrinter();
		new Thread() {
			public void run() {
				tPrinter.print("Hello, Shanfang!");
			};
		}.start();		
		new Thread() {
			public void run() {
				tPrinter.print("Go gators!");
			};
		}.start();
	}
}
class TestPrinter {
	private Lock lock = new ReentrantLock();
	public void print(String str) {
		lock.lock();
		try {
			for(int i = 0; i < str.length(); i++) {
				System.out.print(str.charAt(i));
			}
            System.out.println("\n");
		} finally {
			lock.unlock();
		}
	}
}