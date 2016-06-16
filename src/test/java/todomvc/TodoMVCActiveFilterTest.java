package todomvc;

import org.junit.Test;
import pages.TodoMVCPage;
import static core.GivenHelpers.TaskType.*;
import static core.GivenHelpers.*;

public class TodoMVCActiveFilterTest extends BaseTest {

    TodoMVCPage toDoMVC = new TodoMVCPage();

    @Test
    public void testAdd() {
        toDoMVC.givenAtActive(ACTIVE, "a");

        toDoMVC.add("b");
        toDoMVC.assertVisibleTasks("a", "b");
        toDoMVC.assertItemsLeft(2);
    }

    @Test
    public void testEdit() {
        toDoMVC.givenAtActive(ACTIVE, "a", "b");

        toDoMVC.edit("b", "b_edited");
        toDoMVC.assertTasks("a", "b_edited");
        toDoMVC.assertItemsLeft(2);
    }

    @Test
    public void testComplete() {
        toDoMVC.givenAtActive(ACTIVE, "a", "b");

        toDoMVC.toggle("a");
        toDoMVC.assertVisibleTasks("b");
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll() {
        toDoMVC.givenAtActive(ACTIVE, "a", "b");

        toDoMVC.toggleAll();
        toDoMVC.assertNoVisibleTasks();
        toDoMVC.assertItemsLeft(0);
    }

    @Test
    public void testCancelEdit() {
        toDoMVC.givenAtActive(ACTIVE, "a");

        toDoMVC.cancelEdit("a", "a edited");
        toDoMVC.assertTasks("a");
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testClearCompleted() {
        toDoMVC.givenAtActive(aTask(ACTIVE, "a"), aTask(COMPLETED, "b"));

        toDoMVC.clearCompleted();
        toDoMVC.assertTasks("a");
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testDelete() {
        toDoMVC.givenAtActive(ACTIVE, "a", "b");

        toDoMVC.delete("a");
        toDoMVC.assertTasks("b");
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditByPressTab() {
        toDoMVC.givenAtActive(ACTIVE, "a");

        toDoMVC.editByPressTab("a", "a_edited");
        toDoMVC.assertTasks("a_edited");
        toDoMVC.assertItemsLeft(1);
    }
}