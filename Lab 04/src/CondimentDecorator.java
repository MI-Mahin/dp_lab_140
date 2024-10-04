abstract class CondimentDecorator implements IBeverage {
    protected IBeverage beverage;
    public CondimentDecorator(IBeverage beverage) {
        this.beverage = beverage;
    }

    public abstract String getDescription();
    public abstract double cost();
}
