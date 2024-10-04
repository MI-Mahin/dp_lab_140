public class Main {
    public static void main(String[] args) {
        // Create an Espresso with Milk and Whipped Cream
        IBeverage beverage1 = new Espresso();
        beverage1 = new Milk(beverage1); // Add Milk
        beverage1 = new WhippedCream(beverage1); // Add Whipped Cream
        System.out.println(beverage1.getDescription() + " $" + beverage1.cost());

        // Create a Latte with double Sugar
        IBeverage beverage2 = new Latte();
        beverage2 = new Sugar(beverage2); // Add Sugar
        beverage2 = new Sugar(beverage2); // Add another Sugar
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
    }
}
