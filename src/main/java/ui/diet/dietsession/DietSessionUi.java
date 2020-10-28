package ui.diet.dietsession;

import java.util.Scanner;

import ui.CommonUi;

public class DietSessionUi extends CommonUi {

    public static void printHelp() {
        StringBuilder helpMessage = new StringBuilder();

        helpMessage.append(helpFormatter("Add", "add [FOOD_NAME] /c [CALORIES]",
                "Add a new food item"));
        helpMessage.append(helpFormatter("List", "list",
                "Show all food items"));
        helpMessage.append(helpFormatter("Delete", "delete [INDEX]",
                "Delete the food item at the input index"));
        helpMessage.append(helpFormatter("Clear", "clear",
                "Clear all food items"));
        helpMessage.append(helpFormatter("End", "end",
                "Go back to the Diet Menu."));
        showUser(helpMessage.toString().trim());
    }

    public void printOpening() {
        showUser("Starting Diet Session!");
    }

    public static String MESSAGE_NEGATIVE_CALORIES = "The amount of calories in a food cannot be negative!";

    public void printExit() {
        showUser("Exiting Diet Session!");
    }

    public String getInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
