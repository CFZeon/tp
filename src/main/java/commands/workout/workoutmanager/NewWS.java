package commands.workout.workoutmanager;

import commands.Command;
import storage.workout.WorkOutManagerStorage;
import ui.workout.workoutmanager.WorkoutManagerUi;
import workout.workoutsession.WorkoutSession;

import java.util.ArrayList;

import static commands.workout.workoutmanager.ExecutionResult.OK;
import static workout.workoutmanager.WorkoutManagerParser.parseTags;

public class NewWS extends Command {

    @Override
    public ExecutionResult execute(String[] args) {
        /**
         * When starting a new workout session, a new file is created first.
         * The file path is passed into workoutSession constructor to instantiate
         * a new workoutSession instance.
         */
        ArrayList<String> tags = parseTags(args);
        String filePath = WorkOutManagerStorage.add(tags);
        WorkoutSession ws = new WorkoutSession(filePath);
        logger.info("new workout session created");

        WorkoutManagerUi.printStartNewSessionResponse();

        ws.workoutSessionStart();

        logger.info("ended workout session");
        return OK;
    }

    @Override
    public void printResponse() {
        WorkoutManagerUi.printFinishNewSessionResponse();
    }
}
