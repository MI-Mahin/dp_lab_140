import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static List<BankAccount> accounts = new ArrayList<>();
    private static BankCommandProcessor processor = new BankCommandProcessor();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Inquiry");
            System.out.println("5. Transactions History");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double deposit = scanner.nextDouble();

        IBankCommand command = new CreateAccount(accounts, accountNumber, deposit);
        processor.executeCommand(command);
    }

    private static void deposit() {
        BankAccount account = getAccount();
        if (account == null) return;

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        IBankCommand command = new Deposit(account, amount);
        processor.executeCommand(command);
    }

    private static void withdraw() {
        BankAccount account = getAccount();
        if (account == null) return;

        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();

        IBankCommand command = new Withdraw(account, amount);
        processor.executeCommand(command);
    }

    private static void checkBalance() {
        BankAccount account = getAccount();
        if (account == null) return;

        System.out.println("Current balance: $" + account.getBalance());
    }

    private static void viewTransactionHistory() {
        BankAccount account = getAccount();
        if (account == null) return;

        System.out.println("Transaction History:");
        for (String transaction : account.getTransactions()) {
            System.out.println(transaction);
        }
    }

    private static BankAccount getAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        System.out.println("Account not found!");
        return null;
    }
}