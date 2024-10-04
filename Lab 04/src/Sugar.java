class Sugar extends CondimentDecorator {
    public Sugar(IBeverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return beverage.getDescription() + ", Sugar";
    }

    public double cost() {
        return 10.00 + beverage.cost(); // Add the cost of sugar
    }
}