package commands.diet.dietmanager;

import diet.dietsession.DietSession;
import storage.diet.Storage;
import commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

public class DietSessionLoad extends Command {
    static final String FILEPATH = "saves/diet/";

    @Override
    public void execute(String input, Storage storage) {
        File folder = new File(FILEPATH);
        File[] listOfFiles = folder.listFiles();

        try {
            DietSession ds = null;

            assert listOfFiles != null : "List of files should not be null";
            ds = storage.readDietSession(listOfFiles[Integer.parseInt(input) - 1].getName());
            ds.start();
        } catch (NullPointerException e) {
            System.out.println("Sorry, there is no file at that index.");
            logger.log(Level.INFO, "No file found at array index");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, there is no file at that index.");
            logger.log(Level.INFO, "No file found at array index");
        } catch (IOException e) {
            logger.log(Level.INFO, "failed to execute diet session");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, there is no file at that index.");
            logger.log(Level.INFO, "wrong index for loading");
        }
    }
}
