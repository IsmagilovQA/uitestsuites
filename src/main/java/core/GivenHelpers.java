package core;

import java.util.ArrayList;
import java.util.List;

public class GivenHelpers {
    public static class Task {
        private String name;
        private TaskType type;

        public Task(TaskType taskType,String name) {
            this.name = name;
            this.type = taskType;
        }

        @Override
        public String toString() {
            return String.format("{\\\"completed\\\":%s,\\\"title\\\":\\\"%s\\\"}", type, name);
        }
    }

    public enum TaskType {
        ACTIVE(false), COMPLETED(true);

        private boolean status;

        TaskType(boolean status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return String.valueOf(status);
        }
    }

    public static Task aTask(TaskType status,String name) {
        return new Task(status,name);
    }

    public static String buildJsString(Task... tasks) {
        List<String> taskScripts = new ArrayList<>();

        for (Task task : tasks) {
            taskScripts.add(task.toString());
        }

        return "localStorage.setItem(\"todos-troopjs\", \"[" + String.join(", ", taskScripts) + "]\")";
    }


    public static Task[] aTasks(TaskType taskType, String... taskNames) {
        Task[] tasks = new Task[taskNames.length];
        for (int i = 0; i < taskNames.length; i++) {
            tasks[i] = aTask(taskType,taskNames[i]);
        }
        return tasks;
    }

}
