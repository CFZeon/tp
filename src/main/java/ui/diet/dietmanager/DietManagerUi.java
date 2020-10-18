package ui.diet.dietmanager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DietManagerUi {

    public static void printOpening() {
        System.out.println("You're now in diet manager!");
    }

    public static void printBye() {
        System.out.println("Exiting diet manager!!");
    }

    public String getInput() {
        Scanner in = new Scanner(System.in);
        assert !in.nextLine().isEmpty();
        return in.nextLine();
    }

    /**
     * Extracts out date and time by looking for date strings in YYYY-MM-DD format.
     * @param input date string
     * @return date in MMM dd yyyy if the user inputs date in YYYY-MM-DD format;
     *     else returns original string
     * @throws DateTimeParseException if the date string input is not a valid date
     */
    public String extractDate(String input) throws DateTimeParseException, IllegalStateException {
        try {
            String dateString = input.split("/t")[0].split(" ", 2)[1].trim();
            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher matcher = pattern.matcher(dateString);
            boolean isValidDate = matcher.find();
            String match = matcher.group();
            return LocalDate.parse(match).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("I do not understand your date input!");
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        System.out.println("I've replaced it with today's date.");
        return dtf.format(now);
    }

    public String extractMeal(String input) {
        try {
            return input.split("/t")[1].trim();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify your diet session tag, i.e. breakfast, lunch, dinner");
        }
        System.out.println("Session is tagged as unspecified.");
        return "unspecified";
    }

    public void printLine() {
        System.out.println("-----------------------------------------");
    }

    public void printStartLoading() {
        System.out.println("Loading past diet sessions...");
    }

    public void printFinishLoading() {
        System.out.println("Loading completed!");
    }
}