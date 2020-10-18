package ui.workout.workoutsession;

public class WorkoutSessionUi {
    public static void printError() {
        System.out.println("There is an error, please restart the session");
    }

    public static void addFormatError() {
        System.out.println("Wrong format, please enter in the format: \n"
                + "add [workout] /n [number of sets] /w "
                + "[weight]");
    }

    public static void inputNotRecognisedError() {
        System.out.println("Command not recognised.");
    }

    public static void emptyListError() {
        System.out.println("List is empty. Please enter something.");
    }
}
