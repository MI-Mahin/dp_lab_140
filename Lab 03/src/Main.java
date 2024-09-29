import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Read the file
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();
            String data = new String(Files.readAllBytes(Paths.get(filePath)));

            // Choose the encryption algorithm
            System.out.println("Choose an encryption algorithm:");
            System.out.println("1. Caesar Cipher");
            System.out.println("2. AES");
            System.out.println("3. DES");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            EncryptionContext context = new EncryptionContext();

            if (choice == 1) {
                System.out.print("Enter the shift value: ");
                int shift = scanner.nextInt();
                context.setStrategy(new CaesarCipher(shift));
            } else if (choice == 2) {
                context.setStrategy(new AESEncryption());
            } else if (choice == 3) {
                context.setStrategy(new DESEncryption());
            } else {
                System.out.println("Invalid choice!");
                return;
            }

            // Encrypt the file content
            String encryptedData = context.encrypt(data);
            Files.write(Paths.get(filePath + ".enc"), encryptedData.getBytes());
            System.out.println("Encrypted data saved to " + filePath + ".enc");

            // Decrypt the file content
            //String decryptedData = context.decrypt(encryptedData);
            //System.out.println("Decrypted data: " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
