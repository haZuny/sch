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
	boolean d_turn = true;	// ����� ���� �÷���

	// �Ա� �޼ҵ�
	synchronized void deposit(int amt) {
		int temp = balance + amt;
		
		System.out.print("+");
		balance = temp;
		
		notify();	// ���Ǵ�� ��� ������ ����
		d_turn = false;	// ��� ���� ����
		try {	// ���� ���
			wait();
		}catch(InterruptedException e) {}
	}

	// ��� �޼ҵ�
	synchronized void withdraw(int amt) {
		while(d_turn) {	// �Ա� �����̸� ���� ���
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

//�Ա� ������ Ŭ����
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

//��� ������ Ŭ����
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
