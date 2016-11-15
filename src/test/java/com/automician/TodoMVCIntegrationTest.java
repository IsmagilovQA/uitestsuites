package com.automician;

import com.automician.testconfigs.BaseTest;
import com.automician.categories.Smoke;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static pages.ToDoMVC.*;

public class TodoMVCIntegrationTest extends BaseTest {

    @Test
    public void testTasksCommonFlow() {
        add("a");

        //Complete
        toggle("a");
        assertTasks("a");

        switchToActive();
        assertNoVisibleTasks();

        switchToCompleted();
        assertVisibleTasks("a");

        //Reopen
        toggle("a");
        assertNoVisibleTasks();

        switchToAll();

        delete("a");
        assertNoTasks();
    }

}
