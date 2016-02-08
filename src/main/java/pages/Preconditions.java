package pages;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.Selenide.open;

public class Preconditions {

    public static String pageURL = "http://todomvc.com/examples/troopjs_require/";

    public enum Status {ACTIVE, COMPLETED}

    public enum Filter {
        ALL("#/"), ACTIVE("#/active"), COMPLETED("#/completed");

        Filter(String subURL) {
            url = pageURL + subURL;
        }

        public String url() {
            return url;
        }

        private String url;
    }

    public static class Task {
        public Status status;

        public String name;

        public Task(String name, Status status) {
            this.name = name;
            this.status = status;
        }

        public static Task[] aTasks(Status status, String... names) {
            Task[] tasks = new Task[names.length];

            for (int i = 0; i < names.length; i++) {
                tasks[i] = new Task(names[i], status);
            }
            return tasks;
        }

        public static Task aTask(String name) {
            return new Task(name, Status.ACTIVE);
        }

        public static Task aTask(String name, Status status) {
            return new Task(name, status);
        }

        public String toJSON() {
            String taskItemPattern = "{'completed':%s, 'title':'%s'}".replace("'", "\\\"");

            return String.format(
                    taskItemPattern,
                    this.status == Status.COMPLETED,
                    this.name);
        }
    }

    public static class Storage {

        public static void build(Task... tasks) {
            setData(formatAsStorageString(tasks));
        }

        private static void setData(String tasksJS) {
            String setCommandPattern = "localStorage.setItem('todos-troopjs', '[%s]')";
            executeJavaScript(String.format(setCommandPattern, tasksJS));
        }

        private static String formatAsStorageString(Task... tasks) {

            String tasksJS = "";

            for (Task task : tasks) {
                if (tasksJS.length() > 0) {
                    tasksJS += ",";
                }

                tasksJS += task.toJSON();
            }
            return tasksJS;
        }
    }
}
