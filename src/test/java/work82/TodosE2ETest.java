package work82;


import core.TodosBaseTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import work81.categories.Smoke;


@Category(Smoke.class)
public class TodosE2ETest extends TodosBaseTest {

    @Test
    public void testTasksLifeCycleAndFiltering() {

        page.add("t1", "t2", "t3", "t4");
        page.edit("t2", "t2 edited");
        page.toggle("t2 edited");
        page.toggle("t3");
        page.delete("t1");
        page.clearCompleted();
        page.assertTasks("t4");

        page.toggleAll();
        page.add("t5");

        page.filterActive();
        page.assertVisibleTasks("t5");

        page.filterCompleted();
        page.assertVisibleTasks("t4");

        page.filterAll();
        page.toggle("t4");
        page.toggleAll();
        page.clearCompleted();
        page.assertTasksAreEmpty();

    }

}