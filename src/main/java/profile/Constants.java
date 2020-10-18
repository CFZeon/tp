package profile;

import ui.CommonUi;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Constants used in profile package.
 */
public class Constants {
    public static final int AGE_LOWER_BOUND = 1;
    public static final int AGE_UPPER_BOUND = 123;
    public static final int COMMAND_ARGS_INDEX = 1;
    public static final int COMMAND_SPLIT_LIMIT = 2;
    public static final int COMMAND_TYPE_INDEX = 0;
    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_WORD_EXIT = "exit";
    public static final String COMMAND_WORD_VIEW = "view";
    public static final String DATA_FILE = "profile.txt";
    public static final String DATA_FOLDER = "saves";
    public static final String EMPTY_STRING = "";
    public static final int EXAMPLE_AGE = 30;
    public static final String EXAMPLE_BMI = "32.0 (Obesity Class 1)";
    public static final double EXAMPLE_EXPECTED_WEIGHT = 100;
    public static final int EXAMPLE_HEIGHT = 188;
    public static final String EXAMPLE_NAME = "Schwarzenegger";
    public static final double EXAMPLE_WEIGHT = 113;
    public static final String GREEDY_WHITE_SPACE = "\\s+";
    public static final int HEIGHT_LOWER_BOUND = 63;
    public static final int HEIGHT_UPPER_BOUND = 231;
    public static final String MESSAGE_ADD_TITLE = "Please enter your details to add a new profile.";
    public static final String MESSAGE_CREATE_PROFILE_ACK = "Got it. Here's a confirmation of your profile:"
            + CommonUi.LS + "%s";
    public static final String MESSAGE_DELETE_PROFILE = "I've deleted your profile.";
    public static final String MESSAGE_EXIT_PROFILE = "Exiting Profile Session...";
    public static final String MESSAGE_PROFILE_EXIST = "There's currently a profile in the database. "
            + "Please delete it using \"delete\" command before adding a new one.";
    public static final String MESSAGE_PROFILE_NOT_EXIST = "There's no profile to %s. "
            + "Please add a new one using \"add\" command :D";
    public static final String MESSAGE_VIEW_PROFILE = "Here's your profile:" + CommonUi.LS + "%s";
    public static final String MESSAGE_WELCOME = "Hi %s! How can I help you with your profile? :D";
    public static final String PROFILE_FOLDER = "profile";
    public static final String PROFILE_STRING_REPRESENTATION = "\tName: %s" + CommonUi.LS + "\tAge: %d" + CommonUi.LS
            + "\tHeight: %d cm" + CommonUi.LS + "\tWeight: %.1f kg" + CommonUi.LS + "\tExpected Weight: %.1f kg"
            + CommonUi.LS + "\tYour BMI: %s";
    public static final String EXAMPLE_PROFILE_STRING = String.format(PROFILE_STRING_REPRESENTATION, EXAMPLE_NAME,
            EXAMPLE_AGE, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT, EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_BMI);
    public static final String PROJECT_ROOT = System.getProperty("user.dir");
    public static final Path PATH_TO_PROFILE_FOLDER = Paths.get(PROJECT_ROOT, DATA_FOLDER, PROFILE_FOLDER);
    public static final Path PATH_TO_PROFILE_FILE = Paths.get(PROJECT_ROOT, DATA_FOLDER, PROFILE_FOLDER, DATA_FILE);
    public static final String QUESTION_AGE = "How old are you?";
    public static final String QUESTION_EXPECTED_WEIGHT = "What's your expected weight in kilograms?";
    public static final String QUESTION_HEIGHT = "What's your height in centimeters?";
    public static final String QUESTION_NAME = "It seems like you're new to the Schwarzenegger. How should I call you?";
    public static final String QUESTION_WEIGHT = "What's your weight in kilograms?";
    public static final String SAD_FACE = ":(  OOPS!!! ";
    public static final String MESSAGE_INVALID_COMMAND_WORD = SAD_FACE + "Sorry, but I don't know what that means.";
    public static final String MESSAGE_INVALID_SAVE_FORMAT = SAD_FACE + "Save format in "
            + "data\\profile\\profile.txt is invalid.";
    public static final String MESSAGE_INVALID_AGE = String.format(
            SAD_FACE + "Please input an integer from %d to %d for age.", AGE_LOWER_BOUND, AGE_UPPER_BOUND);
    public static final String MESSAGE_INVALID_HEIGHT = String.format(
            SAD_FACE + "Please input an integer from %d to %d for height.", HEIGHT_LOWER_BOUND, HEIGHT_UPPER_BOUND);
    public static final String MESSAGE_LOADING_ERROR = SAD_FACE + "An error has occurred while loading data."
            + CommonUi.LS + "%s";
    public static final String MESSAGE_SAVING_ERROR = SAD_FACE + "An error has occurred while saving data."
            + CommonUi.LS + "%s";
    public static final String MESSAGE_REDUNDANT_PARAM = SAD_FACE + "%s command does not take parameters.";
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final double WEIGHT_LOWER_BOUND = 2.1;
    public static final double WEIGHT_UPPER_BOUND = 635;
    public static final String MESSAGE_INVALID_WEIGHT = String.format(
            SAD_FACE + "Please input a number from %.1f to %.1f for weight.", WEIGHT_LOWER_BOUND, WEIGHT_UPPER_BOUND);
}
