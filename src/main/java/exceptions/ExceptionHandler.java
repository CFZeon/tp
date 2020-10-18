package exceptions;

/**
 * A class that deals with handling exceptions.
 */
public class ExceptionHandler {

    /**
     * Handles checked exceptions.
     *
     * @param e Checked exceptions in The Schwarzenegger.
     * @return Error message.
     */
    public String handleCheckedExceptions(SchwarzeneggerException e) {
        return e.getMessage();
    }

    /**
     * Handles unchecked exceptions.
     *
     * @param e Unchecked exception in Duke.
     * @return Error message.
     */
    public String handleUncheckedExceptions(Exception e) {
        return e.toString();
    }
}
