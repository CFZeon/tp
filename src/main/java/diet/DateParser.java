package diet;

import exceptions.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateParser {
    private static final List<DateTimeFormatter> dtFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyyMMdd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy MM dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyyMMdd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy MM dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")
    );
    private static final List<DateTimeFormatter> dFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy MM dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd MM yyyy"),
            DateTimeFormatter.ofPattern("ddMMyyyy")
    );


    /**
     * Parses a given string following one of the accepted format into date-time format.
     *
     * @param targetString String input to be parsed.
     * @return Parsed result in the form of LocalDateTime or null if no value parsing is found.
     */
    public static LocalDateTime parseDate(String targetString) throws InvalidDateFormatException {
        for (DateTimeFormatter dtf : dtFormatters) {
            try {
                return LocalDateTime.parse(targetString, dtf);
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (DateTimeFormatter dtf : dFormatters) {
            try {
                return LocalDate.parse(targetString, dtf).atStartOfDay();
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new InvalidDateFormatException();
    }
}
