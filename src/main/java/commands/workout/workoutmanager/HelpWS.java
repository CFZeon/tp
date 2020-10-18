package commands.workout.workoutmanager;

import commands.Command;

import static ui.CommonUi.printHelpFormater;

public class HelpWS extends Command {

    @Override
    public ExecutionResult execute(String[] args) {
        printHelpFormater("List", "list",
                "Show all past sessions");
        printHelpFormater("New", "new",
                "Create a new workout session");
        printHelpFormater("Delete", "delete x",
                "Delete the record indexed at x");
        printHelpFormater("Edit", "edit x",
                "Edit the record indexed at x");
        printHelpFormater("Clear", "clear",
                "Clear all past results");
        printHelpFormater("End", "end",
                "Go back to main menu");
        return ExecutionResult.OK;
    }
}
