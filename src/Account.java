import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private String accountHolder;
    protected double balance;
    protected ArrayList<String> transactionHistory;

    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("+" + amount);
        System.out.println(amount + " deposited. Current balance: " + balance);
    }

    public void withdraw(double amount) {
        if(balance < amount) {
            System.out.println("Insufficient balance to withdraw");
        }
        balance -= amount;
        transactionHistory.add("-" + amount);
        System.out.println(amount + " withdrawn. Current balance: " + balance);
    }

    public void checkBalance() {
        System.out.println("Current balance: " + balance);;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void checkTransactionHistory() {
        System.out.println("-----Transaction History---");
        for(String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("---------------------------");
    }
}
