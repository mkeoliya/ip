package duke.task;

public abstract class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        assert !description.isEmpty() : "Task description should not be empty";
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toDBString() {
        return String.format("%s~%s", isDone ? 1 : 0, description);
    }
}
