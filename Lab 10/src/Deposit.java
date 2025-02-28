class Deposit implements IBankCommand {
    private BankAccount account;
    private double amount;

    public Deposit(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public void execute() {
        account.deposit(amount);
        System.out.println("Deposited " + amount + " ; New Balance " + account.getBalance());
    }
}
