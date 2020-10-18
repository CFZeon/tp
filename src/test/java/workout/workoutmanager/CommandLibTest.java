package workout.workoutmanager;

import commands.CommandLib;
import commands.workout.workoutmanager.WrongWS;
import org.junit.jupiter.api.Test;
import commands.workout.workoutmanager.ByeWS;
import commands.workout.workoutmanager.DeleteWS;
import commands.workout.workoutmanager.ListWS;
import commands.workout.workoutmanager.NewWS;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandLibTest {

    @Test
    void get_WorkoutManagerUnrecognisedCommand_returnNull() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCL();
        assertTrue(cl.get("asdf") instanceof WrongWS);
    }

    @Test
    void get_list_returnListCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCL();
        assertTrue(cl.get("list") instanceof ListWS);
    }

    @Test
    void get_delete_returnDeleteCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCL();
        assertTrue(cl.get("delete") instanceof DeleteWS);
    }

    @Test
    void get_new_returnNewCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCL();
        assertTrue(cl.get("new") instanceof NewWS);
    }

    @Test
    void get_bye_returnByeCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCL();
        assertTrue(cl.get("end") instanceof ByeWS);
    }
}