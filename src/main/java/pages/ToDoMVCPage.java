package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.SelenideElementWithAdditionalLogic;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static com.codeborne.selenide.WebDriverRunner.url;
import static core.AdditionalSelenideAPI.$_;
import static pages.Preconditions.*;

public class ToDoMVCPage {

    public static ElementsCollection tasks = $$(".todo-list>li");
    public static SelenideElement clearCompleted = $(".clear-completed");
    public static SelenideElement newTask = $(".new-todo");

    @Step
    public static void add(String... taskTexts) {
        for (String text:taskTexts) {
            newTask.setValue(text).pressEnter();
        }
    }

    @Step
    public static void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().find(".destroy").shouldBe(visible).click();
    }

    @Step
    public static void toggle(String taskText) {
        tasks.find(exactText(taskText)).find(".toggle").click();
    }

    @Step
    public static void clearCompleted() {
        clearCompleted.click();
        clearCompleted.shouldBe(hidden);
    }

    @Step
    public static void toggleAll() {
        $(".toggle-all").click();
    }

    @Step
    public static void filterAll() {
        $(By.linkText("All")).click();
    }


    @Step
    public static void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public static void filterCompleted() {
        $(By.linkText("Completed")).click();
    }


    @Step
    public static void dumpInputs() {
        System.out.println("DUMP Inputs ");
        for (SelenideElement sss:$$(".todo-list .edit")) //input
            System.out.println(sss.parent().toString()+" - "+sss.parent().getText()+" - "+sss.toString()+" - "+sss.getText()+" - "+(sss.isDisplayed() ? "visible" : "invisible"));
        System.out.println("");
    }

    @Step
    public static void edit(String oldTaskText, String newTaskText) {
        //dumpInputs();
        tasks.find(exactText(oldTaskText)).find("label").doubleClick();
        //dumpInputs();
        SelenideElementWithAdditionalLogic taskInput = $_(By.xpath("//input[contains(@class, 'edit')][parent::*[contains(@class, 'editing')]][ancestor::*[contains(@class, 'todo-list')]]"));
        taskInput.setValueWithoutChangeEvent(newTaskText);
        //dumpInputs();
        taskInput.pressEnterWithChangeEvent();
        //dumpInputs();
    }

    public static void assertVisibleTasksAreEmpty() {
        tasks.filterBy(visible).shouldBe(empty);
    }

    public static void assertTasksAreEmpty(){
        tasks.shouldBe(empty);
    }

    public static void assertItemsLeft(int countTasks) {
        $(".todo-count>strong").shouldHave(exactText(Integer.toString(countTasks)));
    }

    public static void assertTasks(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    public static void assertVisibleTasks(String... taskTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(taskTexts));
    }


    public static void givenAtAll(Preconditions.Task... tasks) {
        givenAt(Preconditions.Filter.ALL, tasks);
    }

    public static void givenAtActive(Task... tasks) {
        givenAt(Filter.ACTIVE, tasks);
    }

    public static void givenAtCompleted(Task... tasks) {
        givenAt(Filter.COMPLETED, tasks);
    }

    public static void givenAtAll(Status status, String... names) {
        givenAt(Filter.ALL, Task.aTasks(status, names));
    }

    public static void givenAtActive(Status status, String... names) {
        givenAt(Filter.ACTIVE, Task.aTasks(status, names));
    }

    public static void givenAtCompleted(Status status, String... names) {
        givenAt(Filter.COMPLETED, Task.aTasks(status, names));
    }

    private static void givenAt(Filter filter, Task... tasks) {
        if(!hasWebDriverStarted()) {
            Configuration.browser = System.getProperty("driver.browser");
        }

        if (!url().equals(filter.url())) {
            open(filter.url());
        }
        Storage.build(tasks);
        executeJavaScript("location.reload()");

        newTask.shouldBe(enabled);
    }

}
