package JavaMonitor;

public class monitorTest2 {

	public static void main(String[] args) throws InterruptedException {
		BankAccount2 b = new BankAccount2();
		BankDeposit2 d = new BankDeposit2(b);
		BankWithdraw2 w = new BankWithdraw2(b);

		d.start();
		w.start();
		d.join();
		w.join();
		System.out.println("\nbalance = " + b.getBalance());
	}

}

class BankAccount2 {
	int balance;
	boolean d_turn = true;	// 입출금 순서 플래그

	// 입금 메소드
	synchronized void deposit(int amt) {
		int temp = balance + amt;
		
		System.out.print("+");
		balance = temp;
		
		notify();	// 조건대기 출금 스레드 깨움
		d_turn = false;	// 출금 차례 지정
		try {	// 조건 대기
			wait();
		}catch(InterruptedException e) {}
	}

	// 출금 메소드
	synchronized void withdraw(int amt) {
		while(d_turn) {	// 입금 차례이면 조건 대기
			try {
				wait();
			}catch(InterruptedException e) {}
		}
		
		int temp = balance - amt;
		System.out.print("-");
		balance = temp;
		
		notify();
		d_turn = true;
	}

	int getBalance() {
		return balance;
	}
}

//입금 스레드 클래스
class BankDeposit2 extends Thread {
	BankAccount2 b;

	BankDeposit2(BankAccount2 b) {
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
class BankWithdraw2 extends Thread {
	BankAccount2 b;

	BankWithdraw2(BankAccount2 b) {
		this.b = b;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			b.withdraw(1000);
		}
	}
}
