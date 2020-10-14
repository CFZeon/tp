package diet.dietmanager.command;

import storage.diet.Storage;

import java.io.File;

public class DietSessionList implements Command {
    static final String FILEPATH = "./saves/diet";
    @Override
    public void execute(String input, Storage storage) {
        File folder = new File(FILEPATH);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            System.out.println("File " + listOfFiles[i].getName());
        }
    }
}
