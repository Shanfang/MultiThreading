import java.util.Random;

public class ReadWriteNonexclusiveTest {
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
	public void set(int data) {
		System.out.println(Thread.currentThread().getName() + " will set data");
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = data;
		System.out.println(Thread.currentThread().getName() + " set the data: " + this.data);
	}	
	public void get() {
		System.out.println(Thread.currentThread().getName() + " will read data");
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " read out the data: " + this.data);
	}
}