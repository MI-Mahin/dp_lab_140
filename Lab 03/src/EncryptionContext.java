public class EncryptionContext {
    private EncryptionStrategy strategy;

    public void setStrategy(EncryptionStrategy strategy) {
        this.strategy = strategy;
    }

    public String encrypt(String data) throws Exception {
        return strategy.encrypt(data);
    }

    public String decrypt(String data) throws Exception {
        return strategy.decrypt(data);
    }
}

