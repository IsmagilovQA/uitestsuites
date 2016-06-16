package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.GivenHelpers;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static core.GivenHelpers.aTasks;
import static core.GivenHelpers.buildJsString;
import static core.GivenHelpers.Task;
import static core.GivenHelpers.TaskType;

public class TodoMVCPage {

    public ElementsCollection tasks = $$("#todo-list>li");
    public SelenideElement newTodo = $("#new-todo");

    @Step
    public void add(String... taskTexts) {
        for (String taskText : taskTexts) {
            newTodo.setValue(taskText).pressEnter();
        }
    }
    @Step
    public void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    public void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    public void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    public void assertItemsLeft(Integer itemsLeft) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeft)));
    }

    @Step
    public void assertVisibleTasks(String... taskTexts) {
        tasks.filter(visible).shouldHave(exactTexts(taskTexts));
    }

    @Step
    public void assertTasks(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    public SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        return tasks.find(cssClass("editing")).find(".edit").setValue(newTaskText);
    }

    @Step
    public void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    @Step
    public void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEscape();
    }

    @Step
    public void editByPressTab(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressTab();
    }

    @Step
    public void editByClickOutOfTask(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText);
        newTodo.click();
    }

    @Step
    public void assertNoVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Step
    public void givenAtActive(Task... tasks) {
        givenAtAll(tasks);
        filterActive();
    }

    @Step
    public void givenAtCompleted(Task... tasks) {
        givenAtAll(tasks);
        filterCompleted();
    }

    @Step
    public static void ensureTodoMVCPageOpened() {
        if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
            open("https://todomvc4tasj.herokuapp.com/");
        }
    }

    @Step
    public void givenAtCompleted(TaskType taskType, String... taskTexts) {
        givenAtCompleted(aTasks(taskType, taskTexts));
    }

    @Step
    public void givenAtActive(TaskType taskType, String... taskTexts) {
        givenAtActive(aTasks(taskType, taskTexts));
    }

    @Step
    public void givenAtAll(TaskType taskType, String... taskTexts) {
        givenAtAll(aTasks(taskType, taskTexts));
    }

    @Step
    public void givenAtAll(Task... tasks) {
        ensureTodoMVCPageOpened();
        executeJavaScript(buildJsString(tasks));
        refresh();
    }
}