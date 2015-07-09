package work81;


import work81.categories.Smoke;
import core.TodosBaseTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;


//***
public class TodosE2ETest extends TodosBaseTest {
    @Category(Smoke.class)
    @Test
    public void testAtAllFilterOperations() {

        //add tasks
        page.add("t1", "t2", "t3", "t4");
        page.assertTasks("t1", "t2", "t3", "t4");
        page.assertItemsLeftCounter(4);

        //complete all
        page.toggleAll();
        page.assertTasks("t1", "t2", "t3", "t4");
        page.assertItemsLeftCounter(0);

        //reopen all
        page.toggleAll();
        page.assertTasks("t1", "t2", "t3", "t4");
        page.assertItemsLeftCounter(4);

        //edit
        page.edit("t2", "t2 edited");
        page.assertTasks("t1", "t2 edited", "t3", "t4");
        page.assertItemsLeftCounter(4);

        //complete
        page.toggle("t3");
        page.toggle("t4");
        page.assertTasks("t1", "t2 edited", "t3", "t4");
        page.assertItemsLeftCounter(2);

        //filter Active
        page.filterActive();
        page.assertVisibleTasks("t1", "t2 edited");
        page.assertItemsLeftCounter(2);

        //filter Completed
        page.filterCompleted();
        page.assertVisibleTasks("t3", "t4");
        page.assertItemsLeftCounter(2);

        //filter All
        page.filterAll();
        page.assertTasks("t1", "t2 edited", "t3", "t4");
        page.assertItemsLeftCounter(2);

        //reopen
        page.toggle("t4");
        page.assertTasks("t1", "t2 edited", "t3", "t4");
        page.assertItemsLeftCounter(3);

        //clear completed
        page.clearCompleted();
        page.assertTasks("t1", "t2 edited", "t4");
        page.assertItemsLeftCounter(3);

        //delete edited
        page.delete("t2 edited");
        page.assertTasks("t1", "t4");
        page.assertItemsLeftCounter(2);

        //delete by editing
        page.edit("t4", "");
        page.assertTasks("t1");
        page.assertItemsLeftCounter(1);

    }

}