public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if((balance + overdraftLimit) < amount) {
            System.out.println("Withdrawal exceeds overdraft limit.");
            System.exit(1);
        }
        balance -= amount;
        transactionHistory.add("-" + amount);
        System.out.println(amount + " withdrawn. Current balance: " + balance);
    }
}
