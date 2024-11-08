public class Notification {
    private String platform;
    private String message;
    private boolean isRead;

    public Notification(String platform, String message, boolean isRead) {
        this.platform = platform;
        this.message = message;
        this.isRead = isRead;
    }

    public String getPlatform() {
        return platform;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
