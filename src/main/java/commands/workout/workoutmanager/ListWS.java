package commands.workout.workoutmanager;

import commands.Command;
import storage.workout.WorkOutManagerStorage;

import static commands.workout.workoutmanager.ExecutionResult.OK;

public class ListWS extends Command {
    @Override
    public ExecutionResult execute(String[] args) {
        WorkOutManagerStorage.list(args);
        logger.info("listed successfully");
        return OK;
    }
}
