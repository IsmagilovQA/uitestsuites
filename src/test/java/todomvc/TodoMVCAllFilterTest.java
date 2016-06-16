package todomvc;

import org.junit.Test;
import pages.TodoMVCPage;
import static core.GivenHelpers.TaskType.*;
import static core.GivenHelpers.*;

public class TodoMVCAllFilterTest extends BaseTest {

    TodoMVCPage toDoMVC = new TodoMVCPage();

    @Test
    public void testEdit() {
        toDoMVC.givenAtAll(ACTIVE, "a");

        toDoMVC.edit("a", "a_edited");
        toDoMVC.assertTasks("a_edited");
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll() {
        toDoMVC.givenAtAll(aTask(COMPLETED, "a"), aTask(ACTIVE, "b"), aTask(ACTIVE, "c"));

        toDoMVC.toggleAll();
        toDoMVC.assertVisibleTasks("a", "b", "c");
        toDoMVC.assertItemsLeft(0);
    }

    @Test
    public void testReopen() {
        toDoMVC.givenAtAll(COMPLETED, "a", "b");

        toDoMVC.toggle("a");
        toDoMVC.assertVisibleTasks("a", "b");
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testCancelEdit() {
        toDoMVC.givenAtAll(ACTIVE, "a", "b");

        toDoMVC.cancelEdit("b", "b edited");
        toDoMVC.assertTasks("a", "b");
        toDoMVC.assertItemsLeft(2);
    }

    @Test
    public void testClearCompleted() {
        toDoMVC.givenAtAll(aTask(COMPLETED, "a"), aTask(COMPLETED, "b"), aTask(ACTIVE, "c"));

        toDoMVC.clearCompleted();
        toDoMVC.assertTasks("c");
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testDeleteByRemovingText() {
        toDoMVC.givenAtAll(ACTIVE, "a", "b");

        toDoMVC.edit("b", "");
        toDoMVC.assertTasks("a");
        toDoMVC.assertItemsLeft(1);
    }
}
