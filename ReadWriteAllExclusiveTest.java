import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Data data = new Data();
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 3; j++) {
						data.set(new Random().nextInt(100));
					}
				}
			}).start();
		}		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 3; j++) {
						data.get();
					}
				}
			}).start();
		}
	}
}
class Data {	
	private int data;	
	public synchronized void set(int data) {
		System.out.println(Thread.currentThread().getName() + " is writing data: " + data);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = data;
		System.out.println(Thread.currentThread().getName() + " set the data: " + this.data);
	}	
	public synchronized void get() {
		System.out.println(Thread.currentThread().getName() + " is reading data: " + this.data);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " read out the data: " + this.data);
	}
}