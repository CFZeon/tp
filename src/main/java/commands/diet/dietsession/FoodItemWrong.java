package commands.diet.dietsession;

import diet.dietsession.Food;
import storage.diet.Storage;
import commands.Command;

import java.util.ArrayList;

public class FoodItemWrong extends Command {
    @Override
    public void execute(String input, ArrayList<Food> foodList, Storage storage) {
        System.out.println("Sorry! It seems like you've entered an invalid command!");
    }
}
