package commands.diet.dietsession;

import diet.dietsession.Food;
import diet.dietsession.DietSessionParser;
import storage.diet.Storage;
import commands.Command;


import java.util.ArrayList;
import java.util.logging.Level;

public class FoodItemAdd extends Command {

    @Override
    public void execute(String input, ArrayList<Food> foodList, Storage storage) {
        DietSessionParser parser = new DietSessionParser();
        try {
            assert !input.isEmpty();
            Food temp = new Food(parser.processFoodName(input), parser.processFoodCalories(input));
            foodList.add(temp);
            System.out.println("Yay! You have added " + temp.toString());
            logger.log(Level.INFO, "Added food to arraylist");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify food info.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a number for calories.");
        }
    }
}
