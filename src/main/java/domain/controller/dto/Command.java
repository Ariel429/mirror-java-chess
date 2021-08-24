package domain.controller.dto;

public enum Command {

    START,
    END,
    MOVE,
    UNKNOWN;

    public static Command of(String command) {
        try {
            return valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ie) {
            return UNKNOWN;
        }
    }
}
