package app.exceptions;

public class FamilyDoesNotExistException extends IllegalArgumentException{

    private static final String EXCEPTION_MESSAGE = "family does not exist\r\n";

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
