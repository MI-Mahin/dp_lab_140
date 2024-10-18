public class PostalMailAdapter implements INotification {
    private PostalMailAPI postalMailAPI;

    public PostalMailAdapter(PostalMailAPI postalMailAPI) {
        this.postalMailAPI = postalMailAPI;
    }

    @Override
    public void send(String recipient, String message) {
        // For simplicity, let's assume the recipient string is formatted as "name, address, zipcode"
        String[] details = recipient.split(",");
        String name = details[0].trim();
        String address = details[1].trim();
        String zipcode = details[2].trim();

        postalMailAPI.sendPostalMail(name, address, zipcode, message);
    }
}
