package constants;

public enum CommunicationsResponseMessage {

    SUCCESSFUL("Message sent successfully"),
    FAIL("Was not possible to send your message, try it in a moment");

    private String message;
    CommunicationsResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
