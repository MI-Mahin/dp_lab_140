import java.util.ArrayList;
import java.util.List;

public class Bundle implements IProductComponent {
    private String bundleName;
    private List<IProductComponent> components = new ArrayList<>();
    private double discount;

    public Bundle(String bundleName, double discount) {
        this.bundleName = bundleName;
        this.discount = discount;
    }

    public void addProduct(IProductComponent productComponent) {
        components.add(productComponent);
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Bundle: " + bundleName + " (Discount: " + discount + "%)");
        for (IProductComponent component : components) {
            component.displayProductInfo();
        }
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (IProductComponent component : components) {
            totalPrice += component.getPrice();
        }
        return totalPrice - (totalPrice * discount / 100);
    }
}
