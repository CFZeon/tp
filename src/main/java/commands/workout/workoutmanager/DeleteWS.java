package commands.workout.workoutmanager;

import commands.Command;
import storage.workout.WorkOutManagerStorage;
import ui.workout.workoutmanager.WorkoutManagerUi;

public class DeleteWS extends Command {

    @Override
    public ExecutionResult execute(String[] args) {
        logger.info("entering delete command");
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
        WorkOutManagerStorage.delete(index);
        logger.info("deleted successfully");
        return ExecutionResult.OK;
    }

    @Override
    public void printResponse() {
        WorkoutManagerUi.printDeleteResponse();
    }
}
