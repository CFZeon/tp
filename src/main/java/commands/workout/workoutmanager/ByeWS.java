package commands.workout.workoutmanager;

import commands.Command;
import workout.workoutmanager.ExitException;
import ui.workout.workoutmanager.WorkoutManagerUi;

public class ByeWS extends Command {

    @Override
    public ExecutionResult execute(String[] args) {
        logger.info("bye command carried out");
        throw new ExitException();
    }

    @Override
    public void printResponse() {
        WorkoutManagerUi.printBye();
    }
}
