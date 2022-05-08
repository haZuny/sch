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

	// 입금 메소드
	synchronized void deposit(int amt) {
		int temp = balance + amt;
		System.out.print("+");
		balance = temp;
	}

	// 출금 메소드
	synchronized void withdraw(int amt) {
		int temp = balance - amt;
		System.out.print("-");
		balance = temp;
	}

	int getBalance() {
		return balance;
	}
}

//입금 스레드 클래스
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

//출금 스레드 클래스
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
