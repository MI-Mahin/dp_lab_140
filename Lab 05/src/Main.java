//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Individual products
        Product laptop = new Product("Laptop", "High performance laptop", 1500);
        Product phone = new Product("Phone", "Latest smartphone", 800);
        Product headphones = new Product("Headphones", "Noise-cancelling headphones", 200);

        //Bundle with a discount
        Bundle giftBasket = new Bundle("Holiday Gift Basket", 10);
        giftBasket.addProduct(laptop);
        giftBasket.addProduct(phone);

        // Bundle that contains a bundle
        Bundle ultimateBundle = new Bundle("Ultimate Tech Bundle", 15);
        ultimateBundle.addProduct(giftBasket);
        ultimateBundle.addProduct(headphones);

        ultimateBundle.displayProductInfo();
        System.out.println("Total Price of Ultimate Bundle: $" + ultimateBundle.getPrice());
    }
}