package work_with_native_todoMVC;


import org.junit.Test;
import org.junit.experimental.categories.Category;
import work_with_native_todoMVC.categories.Smoke;


import static pages.ToDoMVCPage.*;

@Category(Smoke.class)
public class TodosE2ETest extends TodosBaseTest {

    @Test
    public void testTasksLifeCycleAndFiltering() {
        givenAtAll();

        add("t1");
        edit("t1", "t1 edited");
        assertTasks("t1 edited");

        filterActive();

        //complete
        toggle("t1 edited");
        assertVisibleTasksAreEmpty();

        add("t2");
        assertVisibleTasks("t2");

        filterCompleted();

        //reopen
        toggle("t1 edited");
        assertVisibleTasksAreEmpty();

        filterAll();
        assertTasks("t1 edited", "t2");
        assertItemsLeft(2);

        delete("t1 edited");
        assertTasks("t2");

        //complete all
        toggleAll();
        assertTasks("t2");

        clearCompleted();
        assertTasksAreEmpty();
    }

}