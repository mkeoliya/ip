import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    File file;

    public Storage(String pathname) {
        file = new File(pathname);
    }

    public List<Task> load()  {
        List<Task> list = new ArrayList<>();
        try {
            file.createNewFile();
            Scanner sc = new Scanner(file);

            String record;
            while (sc.hasNextLine()) {
                record = sc.nextLine().trim();
                String tokens[] = record.split("~", 3);
                String taskType = tokens[0];
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                String remainingText = tokens[2];
                String description;

                switch(taskType) {
                case "T":
                    description = remainingText;
                    list.add(new TodoTask(description, isDone));
                    break;
                case "E":
                    String eventTokens[] = remainingText.split("~");
                    description = eventTokens[0];
                    LocalDate at = LocalDate.parse(eventTokens[1]);
                    list.add(new EventTask(description, at, isDone));
                    break;
                case "D":
                    String deadlineTokens[] = remainingText.split("~");
                    description = deadlineTokens[0];
                    LocalDate by = LocalDate.parse(deadlineTokens[1]);
                    list.add(new DeadlineTask(description, by, isDone));
                    break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            return list;
        }
    }

    public void write(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(file);
            file.createNewFile();
            for(Task task: tasks.getList()) {
                writer.write(task.toDBString() + System.lineSeparator());
            }
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}