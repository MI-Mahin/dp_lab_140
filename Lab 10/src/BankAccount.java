import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private List<String> transactions;

    public BankAccount(String accountNumber, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<String>();
        transactions.add("Account created with balance:" + initialDeposit);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited " + amount + " ; New Balance " + balance);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
            return false;
        }
        balance -= amount;
        transactions.add("Withdrawn " + amount + " ; New Balance " + balance);
        return true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactions() {
        return transactions;
    }
}
