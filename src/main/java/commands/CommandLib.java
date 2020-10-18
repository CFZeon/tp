package commands;

import commands.diet.dietmanager.DietSessionCreate;
import commands.diet.dietmanager.DietSessionDelete;
import commands.diet.dietmanager.DietSessionList;
import commands.diet.dietmanager.DietSessionLoad;
import commands.diet.dietmanager.DietSessionWrong;
import commands.diet.dietsession.FoodItemAdd;
import commands.diet.dietsession.FoodItemDelete;
import commands.diet.dietsession.FoodItemShow;
import commands.diet.dietsession.FoodItemWrong;
import commands.workout.workoutmanager.ByeWS;
import commands.workout.workoutmanager.ClearWS;
import commands.workout.workoutmanager.DeleteWS;
import commands.workout.workoutmanager.EditWS;
import commands.workout.workoutmanager.HelpWS;
import commands.workout.workoutmanager.ListWS;
import commands.workout.workoutmanager.NewWS;
import commands.workout.workoutmanager.WrongWS;

import java.util.Hashtable;

public class CommandLib {

    public Hashtable<String, Command> library;

    public CommandLib() {
        library = new Hashtable<>();
    }

    public void initWorkoutManagerCL() {
        this.library.put("list", new ListWS());
        this.library.put("new", new NewWS());
        this.library.put("delete", new DeleteWS());
        this.library.put("end", new ByeWS());
        this.library.put("edit", new EditWS());
        this.library.put("clear", new ClearWS());
        this.library.put("help", new HelpWS());
        this.library.put("wrong", new WrongWS());
    }

    public void initDietManagerCL() {
        this.library.put("list", new DietSessionList());
        this.library.put("meal", new DietSessionCreate());
        this.library.put("load", new DietSessionLoad());
        this.library.put("delete", new DietSessionDelete());
        this.library.put("wrong", new DietSessionWrong());
    }

    public void initDietSessionCL() {
        this.library.put("add", new FoodItemAdd());
        this.library.put("delete", new FoodItemDelete());
        this.library.put("show", new FoodItemShow());
        this.library.put("wrong", new FoodItemWrong());
    }

    public Command get(String keyword) {
        if (library.containsKey(keyword)) {
            return library.get(keyword);
        } else {
            return library.get("wrong");
        }
    }
}
