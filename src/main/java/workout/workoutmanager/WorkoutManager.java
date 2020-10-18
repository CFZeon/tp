package workout.workoutmanager;

import commands.CommandLib;
import commands.workout.workoutmanager.ExecutionResult;
import logger.SchwarzeneggerLogger;
import storage.workout.WorkOutManagerStorage;
import ui.workout.workoutmanager.WorkoutManagerUi;
import commands.Command;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkoutManager {

    private Logger logger;
    private final CommandLib cl;

    public WorkoutManager() {
        WorkOutManagerStorage.init();
        cl = new CommandLib();
        cl.initWorkoutManagerCL();
    }

    public WorkoutManager(SchwarzeneggerLogger schwarzeneggerLogger) {
        this();
        this.logger = schwarzeneggerLogger.getLogger();
        logger.log(Level.INFO, "initialisd workout manager command library");
    }

    public void start() {
        logger.log(Level.INFO, "entered workout manager");
        WorkoutManagerUi.printOpening();
        Scanner sc = new Scanner(System.in);
        while (true) {

            String command = sc.nextLine();
            logger.log(Level.FINE, "received input" + command);
            WorkoutManagerUi.printSeperationLine();
            String[] commParts = WorkoutManagerParser.parse(command);

            try {
                processCommand(commParts);
            } catch (ExitException e) {
                logger.log(Level.INFO, "exiting workout manager");
                break;
            }
            WorkoutManagerUi.printSeperationLine();
        }
    }

    private void processCommand(String[] commands) throws ExitException {
        Command command = cl.get(commands[0]);
        ExecutionResult result = command.execute(Arrays.copyOfRange(commands, 1, commands.length));
        if (result == ExecutionResult.OK) {
            command.printResponse();
        }
    }
}
