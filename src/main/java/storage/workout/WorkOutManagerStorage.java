package storage.workout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import seedu.duke.Constant;
import ui.workout.workoutmanager.WorkoutManagerUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WorkOutManagerStorage {

    /**
     * This list contains all the past record files' file name and some other relevant info.
     * It is saved in a file called history.json.
     * Each time it initilises, it will read history.json file and save it to this list.
     * Each time adding/removing record will update this list and write again to local storage.
     */
    private static List<PastWorkoutSessionRecord> pastFiles;

    private static Gson gson;

    /**
     * This variable keeps track of file name.
     * It should be replaced with something else,
     * TODO:e.g. a hashcode that can identify each file distinctly.
     */
    private static int recordCount = 0;

    public static void init() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        readPastRecords();
    }

    public static void list(String[] args) {
        int index = 1;
        System.out.println("the length of list is " + pastFiles.size());
        for (PastWorkoutSessionRecord wsr : pastFiles) {
            System.out.print(index + ". ");
            System.out.println(wsr);
            index += 1;
        }
    }

    public static String add(ArrayList<String> tags) {
        String newFilePath = Constant.WORKOUTSESSIONFOLDER + recordCount + ".json";
        int code = createfile(newFilePath);
        PastWorkoutSessionRecord newRecord = new PastWorkoutSessionRecord(newFilePath, tags);
        pastFiles.add(newRecord);
        recordCount = pastFiles.size();
        writePastRecords();

        return  newFilePath;
    }

    public static void delete(int index) {
        PastWorkoutSessionRecord deletedRecord;
        try {
            deletedRecord = pastFiles.get(index - 1);
            pastFiles.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The index is out of bound!");
            return;
        }
        File myFile = new File(deletedRecord.getFilePath());
        myFile.delete();
        recordCount = pastFiles.size();
        // todo: actually delete the file in the folder based on
        // the information in the deletedRecord
        writePastRecords();
    }

    public static String edit(int index) {
        PastWorkoutSessionRecord editedRecord;
        editedRecord = pastFiles.get(index - 1);
        PastWorkoutSessionRecord newRecord = editedRecord.edit();
        pastFiles.set(index - 1, newRecord);
        recordCount = pastFiles.size();
        // todo: actually delete the file in the folder based on
        // the information in the deletedRecord
        writePastRecords();
        return newRecord.getFilePath();
    }

    public static void clear() {
        while (pastFiles.size() != 0) {
            delete(1);
        }
    }

    private static void readPastRecords() {
        File file = new File(Constant.WORKOUTSESSIONHISTORY);
        WorkoutManagerUi.printStartLoading();
        Type taskListType = new TypeToken<ArrayList<PastWorkoutSessionRecord>>(){}.getType();
        try {
            JsonReader reader = new JsonReader(new FileReader(file.getPath()));
            pastFiles = gson.fromJson(reader, taskListType);
        } catch (FileNotFoundException e) {
            createfile(Constant.WORKOUTSESSIONHISTORY);
            pastFiles = new ArrayList<>();
        }
        recordCount = pastFiles.size();
        WorkoutManagerUi.printFinishLoading();
    }

    private static void writePastRecords() {
        File file = new File(Constant.WORKOUTSESSIONHISTORY);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file.getPath());
            gson.toJson(pastFiles, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occured when saving the progress...");
        }
    }

    private static int createfile(String path) {
        File file = new File(path);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The local storage file cannot be created at " + path);
            return 1;
        }
    }
}
