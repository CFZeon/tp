package diet.dietsession.command;

import diet.dietsession.Food;
import storage.DietSessionStorage;

import java.util.ArrayList;

public class FoodItemShow implements Command {

    @Override
    public void execute(String input, ArrayList<Food> foodList, DietSessionStorage storage) {

        storage.list(input, foodList);
    }


}