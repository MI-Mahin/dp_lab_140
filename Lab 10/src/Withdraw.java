class Withdraw implements IBankCommand {
    private BankAccount account;
    private double amount;

    public Withdraw(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public void execute() {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawn " + amount + " ; New Balance " + account.getBalance());
        }
    }
}
