package commands.diet.dietmanager;

import storage.diet.Storage;
import commands.Command;

import java.util.logging.Level;

public class DietSessionWrong extends Command {
    @Override
    public void execute(String input, Storage storage) {
        System.out.println("Sorry! It seems like you've entered an invalid command!");
        logger.log(Level.INFO,"Invalid command");
    }
}
