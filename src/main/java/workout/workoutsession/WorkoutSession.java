package workout.workoutsession;

import storage.workout.Storage;
import workout.workoutsession.exercise.Exercise;
import ui.workout.workoutsession.WorkoutSessionUi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutSession {
    private String filePath = null;
    private boolean endWorkoutSession = false;
    public ArrayList<Exercise> exercise;

    public WorkoutSession(String filePath) {
        this.filePath = filePath;
        this.exercise = new ArrayList<>();
    }

    private void setEndWorkoutSessionF() {

        this.endWorkoutSession = false;
    }

    private void setEndWorkoutSessionT() {

        this.endWorkoutSession = true;
    }

    /**
     * Starts workout session.
     */
    public void workoutSessionStart() {

        setEndWorkoutSessionF();
        try {
            Storage.readFileContents(filePath, exercise);
        } catch (FileNotFoundException e) {
            WorkoutSessionUi.printError();
        }
        while (!endWorkoutSession) {
            try {
                workoutSessionProcessCommand();
            } catch (IOException e) {
                WorkoutSessionUi.printError();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private void workoutSessionProcessCommand() throws IOException, NullPointerException {

        Scanner in = new Scanner(System.in);
        String[] input = WorkoutSessionParser.workoutSessionParser(in.nextLine().trim());

        switch (input[0].toLowerCase()) {

        case "add":
            try {
                exercise.add(WorkoutSessionParser.addParser(input));
                Storage.writeToStorage(filePath, exercise);
            } catch (NumberFormatException e) {
                WorkoutSessionUi.addFormatError();
            }
            break;
        case "list":
            Storage.readFileContents(filePath, exercise);
            printList();
            Storage.writeToStorage(filePath, exercise);

            break;
        case "delete":
            exercise.remove(WorkoutSessionParser.deleteParser(input));
            Storage.writeToStorage(filePath, exercise);
            break;
        case "bye":
            setEndWorkoutSessionT();
            Storage.writeToStorage(filePath, exercise);
            break;
        default:
            WorkoutSessionUi.inputNotRecognisedError();
        }
    }

    private void printList() {
        if (exercise.size() <= 0) {
            WorkoutSessionUi.emptyListError();
        }
        for (int i = 0; i < exercise.size(); i++) {
            System.out.println((i + 1) + ": " + exercise.get(i).toString());
        }
    }

}
