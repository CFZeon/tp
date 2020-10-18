package profile.parser;

import profile.Profile;
import commands.profile.AddCommand;
import commands.profile.Command;
import commands.profile.DeleteCommand;
import commands.profile.ExitCommand;
import commands.profile.ViewCommand;
import exceptions.profile.InvalidCommandWordException;
import exceptions.profile.RedundantParamException;

import java.util.logging.Logger;

import static profile.Constants.AGE_LOWER_BOUND;
import static profile.Constants.AGE_UPPER_BOUND;
import static profile.Constants.COMMAND_ARGS_INDEX;
import static profile.Constants.COMMAND_SPLIT_LIMIT;
import static profile.Constants.COMMAND_TYPE_INDEX;
import static profile.Constants.COMMAND_WORD_ADD;
import static profile.Constants.COMMAND_WORD_DELETE;
import static profile.Constants.COMMAND_WORD_EXIT;
import static profile.Constants.COMMAND_WORD_VIEW;
import static profile.Constants.EMPTY_STRING;
import static profile.Constants.GREEDY_WHITE_SPACE;
import static profile.Constants.HEIGHT_LOWER_BOUND;
import static profile.Constants.HEIGHT_UPPER_BOUND;
import static profile.Constants.WEIGHT_LOWER_BOUND;
import static profile.Constants.WEIGHT_UPPER_BOUND;

/**
 * A class that deals with making sense of user's command inside Profile Session.
 */
public class ProfileParser {
    private Logger logger;

    /**
     * Constructs ProfileParser object.
     *
     * @param logger Logger to record of information during program execution.
     */
    public ProfileParser(Logger logger) {
        this.logger = logger;
    }

    /**
     * Parses and returns the Command associated with the user input.
     *
     * @param userInputString User's raw input string.
     * @return Associated command.
     * @throws InvalidCommandWordException If command word is invalid.
     * @throws RedundantParamException If redundant parameters are provided.
     */
    public Command parseCommand(String userInputString) throws InvalidCommandWordException, RedundantParamException {
        String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        String commandType = commandTypeAndParams[COMMAND_TYPE_INDEX].toLowerCase();
        String commandArgs = commandTypeAndParams[COMMAND_ARGS_INDEX];

        switch (commandType) {
        case COMMAND_WORD_ADD:
            return new AddCommand(commandArgs, logger);
        case COMMAND_WORD_DELETE:
            return new DeleteCommand(commandArgs, logger);
        case COMMAND_WORD_VIEW:
            return new ViewCommand(commandArgs, logger);
        case COMMAND_WORD_EXIT:
            return new ExitCommand(commandArgs, logger);
        default:
            throw new InvalidCommandWordException();
        }
    }

    /**
     * Splits raw user's input into command word and command arguments string.
     *
     * @param rawUserInput User's raw input.
     * @return Size 2 array; first element is the command type and second element is the arguments string.
     */
    private String[] splitCommandWordAndArgs(String rawUserInput) {
        String[] split = rawUserInput.trim().split(GREEDY_WHITE_SPACE,
                COMMAND_SPLIT_LIMIT);

        return split.length == COMMAND_SPLIT_LIMIT ? split : new String[]{split[COMMAND_TYPE_INDEX], EMPTY_STRING};
    }

    /**
     * Verifies if user's input when creating profile is valid.
     *
     * @param profile User Profile object.
     * @return Whether input profile is valid.
     */
    public static boolean checkValidProfile(Profile profile) {
        return (checkValidName(profile.getName())
                && checkValidAge(profile.getAge())
                && checkValidHeight(profile.getHeight())
                && checkValidWeight(profile.getWeight())
                && checkValidWeight(profile.getExpectedWeight()));
    }

    /**
     * Verifies if user's input name is not empty string.
     *
     * @param name User's input name.
     * @return Whether input name is valid.
     */
    public static boolean checkValidName(String name) {
        return !name.isEmpty();
    }

    /**
     * Verifies if user's input age is in the valid range
     * (between {@link AGE_LOWER_BOUND} and {@link AGE_UPPER_BOUND} inclusive).
     *
     * @param age User's input age.
     * @return Whether input age is valid.
     */
    public static boolean checkValidAge(int age) {
        return (age >= AGE_LOWER_BOUND && age <= AGE_UPPER_BOUND);
    }

    /**
     * Verifies if user's input height is in the valid range
     * (between {@link HEIGHT_LOWER_BOUND} and {@link HEIGHT_UPPER_BOUND} inclusive).
     *
     * @param height User's input height.
     * @return Whether input height is valid.
     */
    public static boolean checkValidHeight(int height) {
        return (height >= HEIGHT_LOWER_BOUND && height <= HEIGHT_UPPER_BOUND);
    }

    /**
     * Verifies if user's input weight is in the valid range
     * (between {@link WEIGHT_LOWER_BOUND} and {@link WEIGHT_UPPER_BOUND} inclusive).
     *
     * @param weight User's input weight.
     * @return Whether input weight is valid.
     */
    public static boolean checkValidWeight(double weight) {
        return (weight >= WEIGHT_LOWER_BOUND && weight <= WEIGHT_UPPER_BOUND);
    }
}
