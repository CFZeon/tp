package commands.workout.workoutmanager;

import commands.Command;
import ui.workout.workoutmanager.WorkoutManagerUi;

import java.util.logging.Level;

import static commands.workout.workoutmanager.ExecutionResult.MISSING;

public class WrongWS extends Command {
    @Override
    public ExecutionResult execute(String[] args) {
        logger.log(Level.WARNING, "command not recognised");
        WorkoutManagerUi.commandNotFoundResponse();
        return MISSING;
    }
}
