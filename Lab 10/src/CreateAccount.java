import java.util.List;

class  CreateAccount implements IBankCommand {
    private List<BankAccount> accounts;
    private String accountNumber;
    private double initialDeposit;

    public CreateAccount(List<BankAccount> accounts, String accountNumber, double initialDeposit) {
        this.accounts = accounts;
        this.accountNumber = accountNumber;
        this.initialDeposit = initialDeposit;
    }

    @Override
    public void execute() {
        accounts.add(new BankAccount(accountNumber, initialDeposit));
        System.out.println("Account " + accountNumber + " created with deposit " + initialDeposit);
    }
}
