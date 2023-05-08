package constants;

public enum MenuOption {
    REGISTER("1. Register host"),
    WHO_AM_I("2. Who am I?"),
    FIBONACCI("3. Calculate fibonacci"),
    COMMUNICATIONS("4. Send message or broadcast"),
    EXIT("exit");

    private final String option;
    MenuOption(String message) {
        this.option = message;
    }

    public String getOption() {
        return option;
    }

}
