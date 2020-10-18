package commands.profile;

import profile.Profile;
import exceptions.profile.RedundantParamException;
import exceptions.SchwarzeneggerException;
import storage.profile.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static profile.Constants.COMMAND_WORD_DELETE;
import static profile.Constants.MESSAGE_DELETE_PROFILE;

/**
 * A representation of the command for deleting user profile.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs DeleteCommand object inheriting abstract class Command.
     *
     * @param commandArgs Command arguments from user's input.
     * @param logger Logger to record of information during program execution.
     * @throws RedundantParamException If parameters are provided to Delete Command.
     */
    public DeleteCommand(String commandArgs, Logger logger) throws RedundantParamException {
        super(logger);
        if (!commandArgs.isEmpty()) {
            throw new RedundantParamException(COMMAND_WORD_DELETE);
        }
    }

    /**
     * Overrides execute method of class Command to execute the delete profile command requested by user's input.
     *
     * @param profile User's Profile object.
     * @param storage Storage to delete data.
     * @return Result of command execution.
     */
    @Override
    public CommandResult execute(Profile profile, Storage storage) {
        try {
            logger.log(Level.INFO, "executing DeleteCommand");
            profile.isDeleted = true;
            storage.saveData(profile);
            return new CommandResult(MESSAGE_DELETE_PROFILE);
        } catch (SchwarzeneggerException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
