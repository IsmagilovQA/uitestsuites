package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ToDoMVCPage {

    ElementsCollection tasks = $$("#todo-list>li");
    SelenideElement clearCompleted = $("#clear-completed");

    public void openToDoMVC(){ open("http://todomvc.com/examples/troopjs_require/#"); }

    public void clearData() {
        executeJavaScript("localStorage.clear()");
        open("http://todomvc.com");
    }

    public void add(String... taskTexts) {
        for (String text:taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    public void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().find(".destroy").shouldBe(visible).click();
    }

    public void toggle(String taskText) {
        tasks.find(exactText(taskText)).find(".toggle").click();
    }

    public void clearCompleted() {
        clearCompleted.click();
        clearCompleted.shouldBe(hidden);
    }

    public void toggleAll() {
        $("#toggle-all").click();
    }

    public void filterAll() {
        $(By.linkText("All")).click();
    }

    public void filterActive() {
        $(By.linkText("Active")).click();
    }

    public void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

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
