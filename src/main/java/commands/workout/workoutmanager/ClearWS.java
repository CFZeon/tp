package commands.workout.workoutmanager;

import commands.Command;
import storage.workout.WorkOutManagerStorage;
import ui.workout.workoutmanager.WorkoutManagerUi;

import java.util.Scanner;

public class ClearWS extends Command {

    @Override
    public ExecutionResult execute(String[] arg) {
        if (!checkConfirmation()) {
            System.out.println("The action aborted");
            return ExecutionResult.ABORTED;
        }
        WorkOutManagerStorage.clear();
        return ExecutionResult.OK;
    }

    @Override
    public void printResponse() {
        WorkoutManagerUi.printClearResponse();
    }

    private static boolean checkConfirmation() {
        Scanner in = new Scanner(System.in);
        System.out.println("Are you sure you want to clear all records? This action is irrevocable.");
        System.out.println("Key in YES to confirm.");
        String input = in.next();
        return  input.equals("YES");
    }
}
