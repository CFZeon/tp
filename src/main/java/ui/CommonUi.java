package ui;

public class CommonUi {
    protected static final String LINE_PREFIX = "\t ";
    public static final String LS = System.lineSeparator() + LINE_PREFIX;
    protected static final String HORIZONTAL_LINE = LINE_PREFIX
            + "____________________________________________________________";
    protected static final String MESSAGE_FORMAT_GENERAL = HORIZONTAL_LINE + LS + "%s" + System.lineSeparator()
            + HORIZONTAL_LINE + System.lineSeparator();

    /**
     * Shows a message to the user.
     *
     * @param result Message to be displayed.
     */
    public void showToUser(String result) {
        System.out.println(String.format(MESSAGE_FORMAT_GENERAL, result));
    }

    public static void printHelpFormater(String commandName, String commandFormat, String description) {
        System.out.format("%-8s - %s\n", commandName, commandFormat);
        System.out.format("%-8s %s\n", "", description);
    }
}
