package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ToDoMVCPage {

    ElementsCollection tasks = $$("#todo-list>li");
    SelenideElement clearCompleted = $("#clear-completed");

    public void openToDoMVC(){ open("https://todomvc4tasj.herokuapp.com/"); }

    public void clearData() {
        executeJavaScript("localStorage.clear()");
        //open("http://todomvc.com");
    }

    @Step
    public void add(String... taskTexts) {
        for (String text:taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().find(".destroy").shouldBe(visible).click();
    }

    @Step
    public void toggle(String taskText) {
        tasks.find(exactText(taskText)).find(".toggle").click();
    }

    @Step
    public void clearCompleted() {
        clearCompleted.click();
        clearCompleted.shouldBe(hidden);
    }

    @Step
    public void toggleAll() {
        $("#toggle-all").click();
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
    public void edit(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).find(".view label").doubleClick();
        tasks.find(cssClass("editing")).find(".edit").setValue(newTaskText).pressEnter();
    }

    public void assertVisibleTasksAreEmpty() {
        tasks.filterBy(visible).shouldBe(empty);
    }

    public void assertTasksAreEmpty(){
        tasks.shouldBe(empty);
    }

    public void assertItemsLeftCounter(int countTasks) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(countTasks)));
    }

    public void assertTasks(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    public void assertVisibleTasks(String... taskTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(taskTexts));
    }

}
