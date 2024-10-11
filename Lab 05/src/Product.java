public class Product implements IProductComponent {
    private String name;
    private String description;
    private double price;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Product: " + name + " | Description: " + description + " | Price: $" + price);
    }

    @Override
    public double getPrice() {
        return price;
    }
}
