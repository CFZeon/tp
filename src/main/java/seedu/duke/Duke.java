package seedu.duke;

import diet.dietmanager.DietManager;
import ui.diet.dietmanager.DietManagerUi;
import logger.SchwarzeneggerLogger;
import profile.ProfileSession;
import workout.workoutmanager.WorkoutManager;

/**
 * The Schwarzenegger program implements an application that keeps track of the user's gym and diet record.
 */
public class Duke {
    private final DietManagerUi dietManagerUI;
    private final DietManager dietManager;
    private SchwarzeneggerLogger schwarzeneggerLogger;

    public Duke() {
        dietManagerUI = new DietManagerUi();
        dietManager = new DietManager();
        schwarzeneggerLogger = new SchwarzeneggerLogger();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     *
     * @param args Unused in Duke.
     */

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs Schwarzenegger.
     */
    private void run() {
        System.out.println("Greetings from Schwarzenegger!");
        String response = dietManagerUI.getInput();
        while (!response.equals("exit")) {
            if (response.equals("diet")) {
                dietManager.start();
            }

            if (response.equals("profile")) {
                System.out.println("Entering Profile Session...");

                ProfileSession profileSession = new ProfileSession(schwarzeneggerLogger);

                while (!profileSession.hasExit) {
                    profileSession.run();
                }
            }

            if (response.equals("workout")) {
                System.out.println("Entering Workout Session...");
                WorkoutManager workoutManager = new WorkoutManager(schwarzeneggerLogger);
                workoutManager.start();
            }
            response = dietManagerUI.getInput();
        }
        System.out.println("Bye, you have exited the Schwarzenegger.");
    }
}