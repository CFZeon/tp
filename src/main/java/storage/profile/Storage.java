package storage.profile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import profile.Profile;
import exceptions.profile.InvalidSaveFormatException;
import exceptions.profile.LoadingException;
import exceptions.profile.SavingException;
import ui.profile.ProfileUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import static profile.Constants.EMPTY_STRING;
import static profile.Constants.PATH_TO_PROFILE_FILE;
import static profile.Constants.PATH_TO_PROFILE_FOLDER;
import static profile.parser.ProfileParser.checkValidProfile;

/**
 * A class that saves and loads user profile data on local hard disk.
 */
public class Storage {
    private boolean hasExistingProfile;
    private Gson gson;
    private Logger logger;

    /**
     * Constructs Storage object where data file is assumed to be existed.
     *
     * @param logger Logger to record of information during program execution.
     */
    public Storage(Logger logger) {
        this.logger = logger;
        hasExistingProfile = true;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Returns boolean stating if data file is existed.
     *
     * @return Boolean stating if data file is existed.
     */
    public boolean getHasExistingProfile() {
        return hasExistingProfile;
    }

    /**
     * Loads user profile from data file.
     *
     * @param profileUi Ui to show exception message to user if any.
     * @return User profile.
     * @throws LoadingException If there are failed or interrupted I/O operations.
     */
    public Profile loadData(ProfileUi profileUi) throws LoadingException {
        Profile profile = null;

        if (Files.exists(PATH_TO_PROFILE_FOLDER)) {
            try {
                logger.log(Level.INFO, "starting to decode profile data");
                profile = decodeProfile(PATH_TO_PROFILE_FILE.toString());
                assert profile != null : "profile should not be null";
                logger.log(Level.INFO, "finishing profile data decoding");
            } catch (FileNotFoundException e) {
                createDataFile(PATH_TO_PROFILE_FILE);
            } catch (InvalidSaveFormatException e) {
                profileUi.showToUser(e.getMessage());
                hasExistingProfile = false;
            }
        } else {
            createDataFolder(PATH_TO_PROFILE_FOLDER);
            createDataFile(PATH_TO_PROFILE_FILE);
        }

        return profile;
    }

    /**
     * Decodes user profile save data to a profile object.
     *
     * @param filePath Path to data file.
     * @return Profile object.
     * @throws InvalidSaveFormatException If the saving format is invalid.
     * @throws FileNotFoundException If data file is not found.
     */
    public Profile decodeProfile(String filePath) throws InvalidSaveFormatException, FileNotFoundException {
        try {
            logger.log(Level.INFO, "decoding profile data");
            Type profileType = new TypeToken<Profile>() {
            }.getType();
            File file = new File(filePath);
            JsonReader reader = new JsonReader(new FileReader(file.getPath()));
            Profile profile = gson.fromJson(reader, profileType);

            if (profile == null || !checkValidProfile(profile)) {
                logger.log(Level.WARNING, "processing invalid profile data");
                throw new InvalidSaveFormatException();
            }

            return profile;
        } catch (JsonSyntaxException e) {
            logger.log(Level.WARNING, "processing invalid syntax in data file", e);
            throw new InvalidSaveFormatException();
        }
    }

    /**
     * Creates data file.
     *
     * @param pathToDataFile Path to data file.
     * @throws LoadingException If there are failed or interrupted I/O operations.
     */
    private void createDataFile(Path pathToDataFile) throws LoadingException {
        try {
            hasExistingProfile = false;
            Files.createFile(pathToDataFile);
            logger.log(Level.INFO, "created data/profile/profile.txt");
        } catch (IOException e) {
            throw new LoadingException(e.getMessage());
        }
    }

    /**
     * Creates data folder.
     *
     * @param pathToDataFolder Path to data folder.
     * @throws LoadingException If there are failed or interrupted I/O operations.
     */
    private void createDataFolder(Path pathToDataFolder) throws LoadingException {
        try {
            Files.createDirectories(pathToDataFolder);
            logger.log(Level.INFO, "created data/profile");
        } catch (IOException e) {
            throw new LoadingException(e.getMessage());
        }
    }

    /**
     * Saves user profile data to hard disk after profile changes.
     *
     * @param profile User's profile.
     * @throws SavingException If there are failed or interrupted I/O operations.
     */
    public void saveData(Profile profile) throws SavingException {
        try {
            logger.log(Level.INFO, "starting to save profile data");
            FileWriter fw = new FileWriter(PATH_TO_PROFILE_FILE.toString());

            if (profile.isDeleted) {
                fw.write(EMPTY_STRING);
            } else {
                gson.toJson(profile, fw);
                fw.flush();
            }

            fw.close();
            logger.log(Level.INFO, "finishing data saving");
        } catch (IOException e) {
            logger.log(Level.WARNING, "processing IOException while saving data", e);
            throw new SavingException(e.getMessage());
        }
    }
}
