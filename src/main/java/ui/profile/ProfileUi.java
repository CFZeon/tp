package ui.profile;

import profile.Profile;
import profile.parser.ProfileParser;
import ui.CommonUi;

import static profile.Constants.MESSAGE_CREATE_PROFILE_ACK;
import static profile.Constants.MESSAGE_INVALID_AGE;
import static profile.Constants.MESSAGE_INVALID_HEIGHT;
import static profile.Constants.MESSAGE_INVALID_WEIGHT;
import static profile.Constants.MESSAGE_WELCOME;
import static profile.Constants.QUESTION_AGE;
import static profile.Constants.QUESTION_EXPECTED_WEIGHT;
import static profile.Constants.QUESTION_HEIGHT;
import static profile.Constants.QUESTION_NAME;
import static profile.Constants.QUESTION_WEIGHT;
import static profile.Constants.SCANNER;

/**
 * A class that deals with interactions with user.
 */
public class ProfileUi extends CommonUi {

    /**
     * Greets user.
     *
     * @param name User's name.
     */
    public void greetUser(String name) {
        showToUser(String.format(MESSAGE_WELCOME, name));
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return Full line entered by the user.
     */
    public String getCommand() {
        System.out.print(">>>>> ");

        String inputLine = SCANNER.nextLine();

        // Silently consume all blank lines
        while (inputLine.trim().isEmpty()) {
            System.out.print(">>>>> ");
            inputLine = SCANNER.nextLine();
        }

        return inputLine;
    }

    /**
     * Prompts for user profile.
     *
     * @return Created profile.
     */
    public Profile getProfileConfig() {
        Profile profile = new Profile(getName(), getAge(), getHeight(), getWeight(false), getWeight(true));
        showToUser(String.format(MESSAGE_CREATE_PROFILE_ACK, profile.toString()));
        return profile;
    }

    /**
     * Gets user's name for first time config.
     *
     * @return User's name.
     */
    private String getName() {
        showToUser(QUESTION_NAME);
        return getCommand();
    }

    /**
     * Gets user's age for first time config.
     *
     * @return User's age.
     */
    private int getAge() {
        int age = 0;

        while (true) {
            try {
                showToUser(QUESTION_AGE);
                age = Integer.parseInt(getCommand());

                if (!ProfileParser.checkValidAge(age)) {
                    showToUser(MESSAGE_INVALID_AGE);
                } else {
                    return age;
                }
            } catch (NumberFormatException e) {
                showToUser(MESSAGE_INVALID_AGE);
            }
        }
    }

    /**
     * Gets user's height for first time config.
     *
     * @return User's height.
     */
    private int getHeight() {
        int height = 0;

        while (true) {
            try {
                showToUser(QUESTION_HEIGHT);
                height = Integer.parseInt(getCommand());

                if (!ProfileParser.checkValidHeight(height)) {
                    showToUser(MESSAGE_INVALID_HEIGHT);
                } else {
                    return height;
                }
            } catch (NumberFormatException e) {
                showToUser(MESSAGE_INVALID_HEIGHT);
            }
        }
    }

    /**
     * Gets user's weight for first time config.
     *
     * @param isExpected If the program is getting expected or current weight of user.
     * @return User's expected or current weight.
     */
    private double getWeight(boolean isExpected) {
        String question;
        if (isExpected) {
            question = QUESTION_EXPECTED_WEIGHT;
        } else {
            question = QUESTION_WEIGHT;
        }

        double weight = 0;

        while (true) {
            try {
                showToUser(question);
                weight = Double.parseDouble(getCommand());

                if (!ProfileParser.checkValidWeight(weight)) {
                    showToUser(MESSAGE_INVALID_WEIGHT);
                } else {
                    return weight;
                }
            } catch (NumberFormatException e) {
                showToUser(MESSAGE_INVALID_WEIGHT);
            }
        }
    }

}
