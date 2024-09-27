public interface EncryptionStrategy {
    String encrypt(String data) throws Exception;
    String decrypt(String data) throws Exception;
}
