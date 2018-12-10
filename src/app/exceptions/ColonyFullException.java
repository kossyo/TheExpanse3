package app.exceptions;

public class ColonyFullException extends IllegalArgumentException {

    private static final String EXCEPTION_MESSAGE = "colony is full\n";

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
