package diet.dietsession;

import commands.CommandLib;
import commands.Command;
import storage.diet.Storage;
import ui.diet.dietsession.DietSessionUi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DietSession {
    private final ArrayList<Food> foodList;

    private final String dateInput;
    private final String typeInput;
    private final LocalDate date;

    private final DietSessionUi dietSessionUI;
    private transient CommandLib cl;
    private final Storage storage;
    private final DietSessionParser parser = new DietSessionParser();
    public boolean endDietSession = false;

    private static Logger logger = Logger.getLogger("java.diet.dietsession");

    public DietSession(String typeInput, String dateInput) {
        this.cl = new CommandLib();
        cl.initDietSessionCL();
        this.dateInput = dateInput;
        this.date = parser.parseDate(dateInput);
        this.typeInput = typeInput;
        this.foodList = new ArrayList<>();
        storage = new Storage();
        dietSessionUI = new DietSessionUi();
    }

    public String getDateInput() {
        return dateInput;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTypeInput() {
        return typeInput;
    }

    public void setEndDietSession(Boolean hasEnded) {
        this.endDietSession = hasEnded;
    }

    public void start() throws IOException {
        logger.log(Level.INFO, "starting diet session");

        // command library is initialised again on start as it is transient variable
        this.cl = new CommandLib();
        cl.initDietSessionCL();
        dietSessionUI.printOpening();
        setEndDietSession(false);
        String input = dietSessionUI.getInput();
        dietSessionInputLoop(input);
        setEndDietSession(true);

        logger.log(Level.INFO, "saving profile session to file");
        saveToFile();
        dietSessionUI.printExit();
    }

    private void dietSessionInputLoop(String input) {
        while (!input.equals("end")) {

            try {
                processCommand(input);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
                break;
            }
            input = dietSessionUI.getInput();
        }
    }

    public void saveToFile() {
        try {
            storage.init(typeInput + " " + date.toString());
            storage.writeToStorageDietSession(typeInput + " " + date.toString(), this);
        } catch (IOException e) {
            logger.log(Level.WARNING, "save profile session failed");
            System.out.println("Failed to save your diet session!");
        }
    }

    public void processCommand(String input) throws NullPointerException {
        String[] commParts = parser.parse(input);
        Command command = cl.get(commParts[0]);
        command.execute(commParts[1], foodList, storage);
    }
}
