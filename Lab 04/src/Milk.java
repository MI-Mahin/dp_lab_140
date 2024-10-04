class Milk extends CondimentDecorator {
    public Milk(IBeverage beverage) {
        super(beverage); // Pass the beverage being decorated
    }

    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    public double cost() {
        return 40.00 + beverage.cost(); // Add the cost of milk
    }
}
