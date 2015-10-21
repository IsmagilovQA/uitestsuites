package work82;

import core.TodosBaseTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import work81.categories.All;
import work81.categories.Buggy;

@Category(All.class)
public class TodosOperationsAtAllFilterTest extends TodosBaseTest {
    @Test
    public void testAdd(){
        page.add("t1", "t2");
        page.assertTasks("t1", "t2");
        page.assertItemsLeftCounter(2);
    }

    @Test
    public void testEdit(){
        page.add("t1", "t2", "t3");
        page.edit("t2","t2 edited");
        page.assertTasks("t1", "t2 edited", "t3");
        page.assertItemsLeftCounter(3);
    }

    @Test
    public void testDeleteByEditing(){
        page.add("t1", "t2");
        page.edit("t2", "");
        page.assertTasks("t1");
        page.assertItemsLeftCounter(1);
    }

    @Test
    public void testDetete(){
        page.add("t1", "t2", "t3");
        page.delete("t2");
        page.assertTasks("t1", "t3");
        page.assertItemsLeftCounter(2);
    }

    @Test
    public void testCompleteAllReopenAll(){
        page.add("t1", "t2");
        page.toggleAll();
        page.assertItemsLeftCounter(0);

        page.toggleAll();
        page.assertTasks("t1", "t2");
        page.assertItemsLeftCounter(2);
    }


    @Test
    public void testCompleteReopenClear(){
        page.add("t1", "t2", "t3");
        page.toggle("t2");
        page.toggle("t3");
        page.assertItemsLeftCounter(1);

        page.toggle("t2");
        page.assertItemsLeftCounter(2);

        page.clearCompleted();
        page.assertTasks("t1", "t2");
        page.assertItemsLeftCounter(2);

    }



    @Category(Buggy.class)
    @Test
    public void testWithBug(){
        page.add("t1", "t2");
        page.assertTasks("t1");
    }
}
