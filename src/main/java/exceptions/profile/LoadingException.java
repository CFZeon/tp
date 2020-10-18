package exceptions.profile;

import exceptions.SchwarzeneggerException;

import static profile.Constants.MESSAGE_LOADING_ERROR;

/**
 * Represents exception while loading data.
 */
public class LoadingException extends SchwarzeneggerException {

    /**
     * Constructs LoadingException object inheriting abstract class SchwarzeneggerException.
     *
     * @param message The cause of the error.
     */
    public LoadingException(String message) {
        super(String.format(MESSAGE_LOADING_ERROR, message));
    }
}
