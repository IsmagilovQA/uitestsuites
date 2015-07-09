package work81;

import work81.categories.All;
import work81.categories.Buggy;
import core.TodosBaseTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(All.class)
public class TodosOperationsAtAllFilterTest extends TodosBaseTest {
    @Test
    public void testAdd(){
        page.add("t1", "t2");
        page.assertTasks("t1", "t2");
        page.assertItemsLeftCounter(2);
    }

    @Test
    public void testCompleteAllReopenAll(){
        //GIVEN
        page.add("t1", "t2");

        //complete all
        page.toggleAll();
        page.assertTasks("t1", "t2");
        page.assertItemsLeftCounter(0);

        //reopen all
        page.toggleAll();
        page.assertTasks("t1", "t2");
        page.assertItemsLeftCounter(2);
    }


    @Test
    public void testCompleteReopenClearCompleted(){
        //GIVEN
        page.add("t1", "t2", "t3");

        //complete
        page.toggle("t2");
        page.toggle("t3");
        page.assertTasks("t1", "t2", "t3");
        page.assertItemsLeftCounter(1);

        //reopen
        page.toggle("t2");
        page.assertTasks("t1", "t2", "t3");
        page.assertItemsLeftCounter(2);

        //clear completed
        page.clearCompleted();
        page.assertTasks("t1", "t2");
        page.assertItemsLeftCounter(2);

    }

    @Test
    public void testDeleteByEditing(){
        //GIVEN
        page.add("t1", "t2");

        //delete by editing
        page.edit("t2", "");
        page.assertTasks("t1");
        page.assertItemsLeftCounter(1);
    }

    @Test
     public void testSwitchWithActiveFilter(){
        //GIVEN
        page.add("t1", "t2");
        page.toggle("t2");

        //Switch At Active Filter
        page.filterActive();
        page.assertVisibleTasks("t1");
        page.assertItemsLeftCounter(1);

        //Switch At All Filter
        page.filterAll();
        page.assertTasks("t1", "t2");
        page.assertItemsLeftCounter(1);
    }

    @Test
    public void testSwitchWithCompletedFilter(){
        //GIVEN
        page.add("t1", "t2");
        page.toggle("t2");

        //Switch At Commleted Filter
        page.filterCompleted();
        page.assertVisibleTasks("t2");
        page.assertItemsLeftCounter(1);

        //Switch At All Filter
        page.filterAll();
        page.assertTasks("t1", "t2");
        page.assertItemsLeftCounter(1);
    }

    @Category(Buggy.class)
    @Test
    public void testWithBug(){
        page.add("t1", "t2");
        page.assertTasks("t1");
    }
}
