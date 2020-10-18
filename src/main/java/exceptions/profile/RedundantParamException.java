package exceptions.profile;

import exceptions.SchwarzeneggerException;

import static profile.Constants.MESSAGE_REDUNDANT_PARAM;

/**
 * Represents exception when input parameters are redundant.
 */
public class RedundantParamException extends SchwarzeneggerException {

    /**
     * Constructs RedundantParamException object inheriting abstract class SchwarzeneggerException.
     *
     * @param commandWord Command with redundant input parameters.
     */
    public RedundantParamException(String commandWord) {
        super(String.format(MESSAGE_REDUNDANT_PARAM, commandWord.toUpperCase()));
    }
}
