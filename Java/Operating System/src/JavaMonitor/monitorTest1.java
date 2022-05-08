package JavaMonitor;

public class monitorTest1 {

	public static void main(String[] args) throws InterruptedException {
		BankAccount1 b = new BankAccount1();
		BankDeposit1 d = new BankDeposit1(b);
		BankWithdraw1 w = new BankWithdraw1(b);

		d.start();
		w.start();
		d.join();
		w.join();
		System.out.println("\nbalance = " + b.getBalance());
	}

}

class BankAccount1 {
	int balance;

	// �Ա� �޼ҵ�
	synchronized void deposit(int amt) {
		int temp = balance + amt;
		System.out.print("+");
		balance = temp;
	}

	// ��� �޼ҵ�
	synchronized void withdraw(int amt) {
		int temp = balance - amt;
		System.out.print("-");
		balance = temp;
	}

	int getBalance() {
		return balance;
	}
}

//�Ա� ������ Ŭ����
class BankDeposit1 extends Thread {
	BankAccount1 b;

	BankDeposit1(BankAccount1 b) {
		this.b = b;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			b.deposit(1000);
			;
		}
	}
}

//��� ������ Ŭ����
class BankWithdraw1 extends Thread {
	BankAccount1 b;

	BankWithdraw1(BankAccount1 b) {
		this.b = b;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			b.withdraw(1000);
		}
	}
}
