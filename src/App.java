import java.util.Scanner;

public class App {
    public static void printMenu() {
        System.out.println("Please pick an action: ");
        System.out.println("1. Add account");
        System.out.println("2. Find account");
        System.out.println("3. Transfer");
        System.out.println("4. Exit");
    }
    // SavingsAccount CJS = new SavingsAccount("164119188501", "CJS", 10_000, 5);
    // CurrentAccount JavaGlobal = new CurrentAccount("164119188502", "JavaGlobal", 10_000, 1000);
    // CJS.addInterest();
    // JavaGlobal.withdraw(10_000);
    // bank.transferFunds("164119188501", "164119188509", 500);
    // CJS.checkBalance();
    // JavaGlobal.checkBalance();
    // CJS.checkTransactionHistory();
    // JavaGlobal.checkTransactionHistory();

    /*
        1. add acc
        2. find acc
        3. transfer fund
        4. exit
    */
    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();
        
        Scanner scanner = new Scanner(System.in);
        printMenu();
        Integer selection = scanner.nextInt();
        while(true) {
            switch (selection) {
                case 1:
                    bank.addAccount();
                    printMenu();
                    break;
                case 2:
                    bank.displayAccount();
                    printMenu();
                    break;
                case 3:
                    bank.transferFunds();
                    printMenu();
                    break;
                case 4:
                    scanner.close();
                    System.exit(1);
                default:
                    System.err.println("Invalid action.");
                    printMenu();
                    selection = scanner.nextInt();
            }
        }
    }
}
