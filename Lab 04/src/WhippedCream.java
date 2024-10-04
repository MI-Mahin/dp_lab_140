class WhippedCream extends CondimentDecorator {
    public WhippedCream(IBeverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return beverage.getDescription() + ", Whipped Cream";
    }

    public double cost() {
        return 30 + beverage.cost(); // Add the cost of whipped cream
    }
}