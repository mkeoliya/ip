package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    LocalDate at;
    static final String TASK_TYPE = "E";

    public EventTask(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public EventTask(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Converts task to string - task type, tick/check and description
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", TASK_TYPE, getStatusIcon(), description,
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Converts task to string representation in database- task type, 1 for done/0 for not done, and description
     */
    public String toDBString() {
        return TASK_TYPE + "~" + super.toDBString() + "~" + at.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
    }
}
