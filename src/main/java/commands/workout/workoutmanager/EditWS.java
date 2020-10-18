package commands.workout.workoutmanager;

import commands.Command;
import storage.workout.WorkOutManagerStorage;
import ui.workout.workoutmanager.WorkoutManagerUi;
import workout.workoutsession.WorkoutSession;

public class EditWS extends Command {

    @Override
    public ExecutionResult execute(String[] args) {
        logger.info("entering edit command");
        int index = -1;
        try {
            index = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            logger.warning("Number format exception caught");
            System.out.println("Input is not a number");
            return ExecutionResult.FAILED;
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.warning("ArrayIndexOutOfBoundsException caught");
            System.out.println("Insufficient arguments are given.");
            return ExecutionResult.FAILED;
        }
        String filePath;
        try {
            filePath = WorkOutManagerStorage.edit(index);
        } catch (IndexOutOfBoundsException e) {
            logger.warning("Index Out Of Bounds Exception caught");
            System.out.println("Could not found that record. Give an index within the range.");
            return ExecutionResult.FAILED;
        }
        WorkoutSession ws = new WorkoutSession(filePath);
        logger.info("editing workout session created");
        ws.workoutSessionStart();

        logger.info("edit successfully");
        return ExecutionResult.OK;
    }

    @Override
    public void printResponse() {
        WorkoutManagerUi.printFinishNewSessionResponse();
    }
}
