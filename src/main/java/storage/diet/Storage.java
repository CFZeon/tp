package storage.diet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import diet.dietsession.DietSession;
import diet.dietsession.Food;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class holds the data loaded during runtime and read and writes to the local storage.
 */
public class Storage {

    private static final String FILEPATH = "saves/diet";
    private static Gson gson;
    private static File file = null;


    /**
     * Initialise the database with locally stored data.
     * If the local file is not found. It creates the relevant file and folder.
     * @throws IOException If director or file cannot be created.
     */
    public void init(String filePath) throws IOException {

        gson = new GsonBuilder().setPrettyPrinting()
                .create();

        //creates the file
        String fileName = "saves/diet/" + filePath;
        file = new File(fileName);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Write the content in TaskList to a local file.
     * If the local file is not found. It creates the relevant file and folder.
     * @throws IOException If director or file cannot be created.
     */
    public void writeToStorage(String filePath, ArrayList<Food> taskList) throws IOException {
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file.getPath());
        gson.toJson(taskList, writer);
        writer.flush();
        writer.close();
    }

    /**
     * Write the content in dietSession to a local file.
     * If the local file is not found. It creates the relevant file and folder.
     * @throws IOException If director or file cannot be created.
     */
    public void writeToStorageDietSession(String filePath, DietSession dietSession) throws IOException {
        File file = new File(FILEPATH + filePath + ".json");
        FileWriter writer = new FileWriter(file.getPath());
        gson.toJson(dietSession, writer);
        writer.flush();
        writer.close();
    }

    public void readFileContents(String filePath, ArrayList<Food> taskList) throws FileNotFoundException {
        File file = new File(filePath);

        Type taskListType = new TypeToken<ArrayList<Food>>(){}.getType();

        JsonReader reader = new JsonReader(new FileReader(file.getPath()));
        taskList.clear();
        taskList.addAll(gson.fromJson(reader, taskListType));
    }


}