package JavaMonitor2;

public class BounedeBuffer<E> {
	private static final int BUFFER_SIZE = 5;

	private int count, in, out;
	private E[] buffer;

	public BounedeBuffer() {
		count = 0;
		in = 0;
		out = 0;
		buffer = (E[]) new Object[BUFFER_SIZE];
	}

	public synchronized void insert(E item) {
		while (count == BUFFER_SIZE) {
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		}

		buffer[in] = item;
		in = (in + 1) % BUFFER_SIZE;
		count++;

		notify();
		System.out.println("����: " + item);
	}

	public synchronized E remove() {
		E item;

		while (count == 0) {
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		}

		item = buffer[out];
		out = (out + 1) % BUFFER_SIZE;
		count--;

		notify();
		System.out.println("\t\t�Һ�: " + item);

		return item;
	}

	public static void main(String[] args) {
		BounedeBuffer<Integer> buf = new BounedeBuffer<>();
		Producer prod = new Producer(buf);
		Consumer cons = new Consumer(buf);

		prod.start();
		cons.start();
	}

}

//������ ������
class Producer extends Thread {
	BounedeBuffer<Integer> buffer;
	int num;

	// ������
	Producer(BounedeBuffer<Integer> buf) {
		this.buffer = buf;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			buffer.insert((int) (Math.random() * 100));
		}
	}
}

// �Һ��� ������
class Consumer extends Thread {
	BounedeBuffer<Integer> buffer;

	// ������
	Consumer(BounedeBuffer<Integer> buf) {
		this.buffer = buf;
	}

	public void run() {
		for (int i = 0; i < 20; i++)
			buffer.remove();
	}
}
