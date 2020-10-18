package diet;


import ui.diet.dietmanager.DietManagerUi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import diet.dietsession.Food;
import commands.Command;
import commands.diet.dietsession.FoodItemAdd;
import commands.diet.dietsession.FoodItemDelete;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import storage.diet.Storage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DietTest {

    @Test
    void extractDate_dateCorrectFormat_returnsDateString() {
        String input = "/d 2020-11-11 /t lunch";
        DietManagerUi ui = new DietManagerUi();
        String dateString = ui.extractDate(input);
        assertEquals(dateString, "11-11-2020");
    }

    @Test
    void extract_dateWrongFormat_expectsException() {
        String input = "/d 2020-22-22 /t lunch";
        DietManagerUi ui = new DietManagerUi();
        assertThrows(DateTimeParseException.class, () -> ui.extractDate(input));
    }

    @Test
    void extract_dateNoDate_returnsCurrentDate() {
        String input = "/d";
        DietManagerUi ui = new DietManagerUi();
        String dateString = ui.extractDate(input);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String outputString = dtf.format(now);
        assertEquals(dateString, outputString);
    }

    @Test
    void extractMeal_mealCorrectFormat_returnsMealString() {
        String input = "/d 2020-11-11 /t lunch";
        DietManagerUi ui = new DietManagerUi();
        String mealString = ui.extractMeal(input);
        assertEquals(mealString, "lunch");
    }

    @Test
    void extract_dateNoMeal_returnsUnspecified() {
        String input = "/d 2020-11-11 /t";
        DietManagerUi ui = new DietManagerUi();
        String mealString = ui.extractMeal(input);
        assertEquals(mealString, "unspecified");
    }

    @Test
    void toString_correctInput_returnsCorrectString() {
        Food fd = new Food("biscuit", 400.00);
        String output = fd.toString();
        assertEquals("biscuit with calories: 400.0", output);
    }

    ArrayList<Food> foodList = new ArrayList<>();
    Storage storage = new Storage();

    @Test
    void testAdd_correctInput_returnsMoreFood() {
        Command command = new FoodItemAdd();
        command.execute("melon /c 500", foodList, storage);
        assertEquals(1, foodList.size());
    }

    @Test
    void testDelete_correctInput_returnsFewerFood() {
        Command command = new FoodItemDelete();
        command.execute("1", foodList, storage);
        assertEquals(0, foodList.size());
    }

    private final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /*
    @Test
    void testAdd_noCalories_returnsWarning() {
        Command command = new FoodItemAdd();
        command.execute("melon", foodList, storage);
        String warning = "Please specify your food info.";
        assertEquals(warning, outputStreamCaptor.toString().trim());
    }



    @Test
    void testAdd_wrongFormatForCalories_returnsWarning() {
        Command command = new FoodItemAdd();
        command.execute("melon /c no calories", foodList, storage);
        String warning = "Please specify your food info.";
        assertEquals(warning, outputStreamCaptor.toString().trim());
    }

    @Test
    void testDelete_IndexTooLarge_returnsWarning() {
        Command command = new FoodItemDelete();
        command.execute(Integer.toString((foodList.size() + 4)), foodList, storage);
        String warning = "Sorry, the index is not found.";
        assertEquals(outputStreamCaptor.toString().trim(), warning);
    }

    @Test
    void testDelete_WrongFormatForIndex_returnsWarning() {
        Command command = new FoodItemDelete();
        command.execute("a", foodList, storage);
        String warning = "Sorry, the index is not found.";
        assertEquals(outputStreamCaptor.toString().trim(), warning);
    }

    @Test
    void processCommandForDietSession_WrongCommand_returnsWarning() {
        Command command = new FoodItemWrong();
        command.execute("hahaha", foodList, null);
        String warning = "I do not understand your date input!\n"
                + "I've replaced it with today's date.\n"
                + "Yay! You have added melon with calories: 500.0\n"
                + "Sorry! It seems like you've entered an invalid command!";
        assertEquals(warning, outputStreamCaptor.toString().trim());

    }

    @Test
    void processCommandForDietManager_WrongCommand_returnsWarning() {
        diet.dietmanager.command.Command command = new DietSessionWrong();
        command.execute("hahaha", null);
        String warning = "I do not understand your date input!\n" +
                "I've replaced it with today's date.\n" +
                "Yay! You have added melon with calories: 500.0\n" +
                "Sorry! It seems like you've entered an invalid command!\n" +
                "Sorry! It seems like you've entered an invalid command!";
        assertEquals(warning, outputStreamCaptor.toString().trim());
    }

     */
}