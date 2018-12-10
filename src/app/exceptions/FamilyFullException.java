package app.exceptions;

public class FamilyFullException extends IllegalArgumentException {

    private static final String EXCEPTION_MESSAGE = "family is full\n";

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
