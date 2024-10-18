
public class Main {
    public static void main(String[] args) {
        INotification emailNotification = new EmailNotification();
        INotification smsNotification = new SMSNotification();

        PostalMailAPI postalMailAPI = new PostalMailAPI();
        INotification postalMailAdapter = new PostalMailAdapter(postalMailAPI);

        // Using the notification system
        emailNotification.send("mahin@gmail.com", "Notification from Email");
        smsNotification.send("210042140", "Notification from SMS");
        postalMailAdapter.send("Mahin, IUT, Boardbazar", "Notification from postal mail");
    }
}