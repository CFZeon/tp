package workout.workoutmanager;

import java.util.ArrayList;

public class WorkoutManagerParser {

    public static String[] parse(String comm) {
        return comm.split(" ");
    }

    public static ArrayList<String> parseTags(String[] arr) {
        ArrayList<String> result = new ArrayList<>();
        try {
            if (!arr[0].equals("/t")) {
                return result;
            }
            for (int i = 1; i < arr.length; i++) {
                if (!result.contains(arr[i])) {
                    result.add(arr[i]);
                }
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return result;
    }


}
