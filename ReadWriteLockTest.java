import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
	private ReadWriteLock rwLock = new ReentrantReadWriteLock();

	public void set(int data) {
		System.out.println(Thread.currentThread().getName() + " will write data");
		rwLock.writeLock().lock();
		try {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " sets the data: " + this.data);

		} finally {
			rwLock.writeLock().unlock();
		}
	}	
	public void get() {
		rwLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " will read data");
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " reads out the data: " + this.data);
		} finally {
			rwLock.readLock().unlock();
		}
	}
}