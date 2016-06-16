package todomvc;

import org.junit.Test;
import pages.TodoMVCPage;
import static core.GivenHelpers.TaskType.*;
import static core.GivenHelpers.*;

public class TodoMVCCompletedFilterTest extends BaseTest {

    TodoMVCPage toDoMVC = new TodoMVCPage();

    @Test
    public void testReopenAll() {
        toDoMVC.givenAtCompleted(COMPLETED, "a", "b", "c");

        toDoMVC.toggleAll();
        toDoMVC.assertNoVisibleTasks();
        toDoMVC.assertItemsLeft(3);
    }

    @Test
    public void testCancelEdit() {
        toDoMVC.givenAtCompleted(COMPLETED, "a", "b", "c");

        toDoMVC.cancelEdit("a", "a edited");
        toDoMVC.assertTasks("a", "b", "c");
        toDoMVC.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleted() {
        toDoMVC.givenAtCompleted(aTask(ACTIVE, "a"), aTask(COMPLETED, "b"));

        toDoMVC.clearCompleted();
        toDoMVC.assertNoVisibleTasks();
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditByClickOutOfTask() {
        toDoMVC.givenAtCompleted(COMPLETED, "a");

        toDoMVC.editByClickOutOfTask("a", "a_edited");
        toDoMVC.assertTasks("a_edited");
        toDoMVC.assertItemsLeft(0);
    }
}
