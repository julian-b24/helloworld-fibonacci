package constants;

public enum RegisterResponseMessage {
    SUCCESSFUL("Successful  register of "),
    FAIL_HOST_EXISTS("Host is already registered");

    private String message;
    RegisterResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
