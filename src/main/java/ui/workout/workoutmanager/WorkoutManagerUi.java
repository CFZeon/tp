package ui.workout.workoutmanager;

public class WorkoutManagerUi {

    public static void printOpening() {
        System.out.println("Let's manage your workout sessions!");
    }

    public static void printBye() {
        System.out.println("Bye!");
    }

    public static void printStartLoading() {
        System.out.println("Loading history records...");
    }

    public static void printFinishLoading() {
        System.out.println("Loading completed!");
    }

    public static void printSeperationLine() {
        System.out.println("-----------------------------------------");
    }

    public static void printDeleteResponse() {
        System.out.println("You have deleted that record!");
    }

    public static void printClearResponse() {
        System.out.println("You have cleared all records!");
    }

    public static void printStartNewSessionResponse() {
        System.out.println("You have started a new workout session!");
    }

    public static void printFinishNewSessionResponse() {
        System.out.println("Congratulations! You have finished today's workout!");
    }

    public static void commandNotFoundResponse() {
        System.out.println("The command not recognised!");
    }
}
