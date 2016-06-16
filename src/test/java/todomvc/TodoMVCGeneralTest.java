package todomvc;
import org.junit.Test;
import pages.TodoMVCPage;
import static core.GivenHelpers.TaskType.*;
import static core.GivenHelpers.*;

public class TodoMVCGeneralTest extends BaseTest {

    TodoMVCPage toDoMVC = new TodoMVCPage();

    @Test
    public void testTaskLifeCycle() {
        toDoMVC.givenAtAll();
        toDoMVC.add("a");

        //Complete
        toDoMVC.toggle("a");
        toDoMVC.assertTasks("a");

        toDoMVC.filterActive();
        toDoMVC.assertNoVisibleTasks();

        toDoMVC.filterCompleted();
        toDoMVC.assertVisibleTasks("a");

        //Reopen
        toDoMVC.toggle("a");
        toDoMVC.assertNoVisibleTasks();

        toDoMVC.filterAll();
        toDoMVC.delete("a");
        toDoMVC.assertNoVisibleTasks();
    }

    @Test
    public void testSwitchFromAllToCompleted() {
        toDoMVC.givenAtAll(aTask(ACTIVE, "a"), aTask(COMPLETED, "b"));

        toDoMVC.filterCompleted();
        toDoMVC.assertVisibleTasks("b");
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testSwitchFromActiveToAll() {
        toDoMVC.givenAtActive(aTask(ACTIVE, "a"), aTask(COMPLETED, "b"), aTask(COMPLETED, "c"));

        toDoMVC.filterAll();
        toDoMVC.assertVisibleTasks("a", "b", "c");
        toDoMVC.assertItemsLeft(1);
    }

    @Test
    public void testSwitchFromCompletedToActive() {
        toDoMVC.givenAtCompleted(aTask(ACTIVE, "a"), aTask(COMPLETED, "b"), aTask(COMPLETED, "c"));

        toDoMVC.filterActive();
        toDoMVC.assertVisibleTasks("a");
        toDoMVC.assertItemsLeft(1);
    }
}


