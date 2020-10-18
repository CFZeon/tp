package storage.workout;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PastWorkoutSessionRecord implements Comparable<PastWorkoutSessionRecord> {

    private final String filePath;
    private final LocalDateTime creationTime;
    private LocalDateTime lastEditTime;
    private final ArrayList<String> tags;

    public PastWorkoutSessionRecord(String filePath, ArrayList<String> tags) {
        this.filePath = filePath;
        creationTime = LocalDateTime.now();
        lastEditTime = LocalDateTime.now();
        this.tags = tags;
    }

    public PastWorkoutSessionRecord(String filePath, LocalDateTime creationTime,
                                    LocalDateTime lastEditTime, ArrayList<String> tags) {
        this.filePath = filePath;
        this.creationTime = creationTime;
        this.lastEditTime = lastEditTime;
        this.tags = tags;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return "Session created on " + creationTime.toLocalDate()
                + ", last editted on " + lastEditTime.toLocalDate();
    }

    @Override
    public int compareTo(PastWorkoutSessionRecord o) {
        if (creationTime.isBefore(o.creationTime)) {
            return -1;
        }
        return 1;
    }

    public PastWorkoutSessionRecord edit() {
        return new PastWorkoutSessionRecord(filePath, creationTime, LocalDateTime.now(), this.tags);
    }
}
