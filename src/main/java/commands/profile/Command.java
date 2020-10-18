package commands.profile;

import profile.Profile;
import storage.profile.Storage;

import java.util.logging.Logger;

/**
 * A base class for command.
 */
public abstract class Command {
    protected Logger logger;

    /**
     * Constructs abstract class Command.
     *
     * @param logger Logger to record of information during program execution.
     */
    public Command(Logger logger) {
        this.logger = logger;
    }

    /**
     * Executes a specific command requested by user's input.
     *
     * @param profile User's Profile object.
     * @param storage Storage to save data when required.
     * @return Result of command execution.
     */
    public abstract CommandResult execute(Profile profile, Storage storage);
}
