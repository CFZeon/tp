package exceptions;

/**
 * A base class for the checked exceptions in Schwarzenegger.
 */
public abstract class SchwarzeneggerException extends Exception {

    /**
     * Constructs SchwarzeneggerException object inheriting class Exception.
     *
     * @param message Error message.
     */
    public SchwarzeneggerException(String message) {
        super(message);
    }
}