package com.automician;

import com.automician.categories.Buggy;
import com.automician.categories.Smoke;
import com.automician.testconfigs.BaseTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static pages.ToDoMVC.*;
import static pages.ToDoMVC.assertItemsLeft;

public class TodoMVCAtAllFilterTest extends BaseTest {

    @Test
    public void testEdit() {
        add("a");

        edit("a", "a edited");
        assertTasks("a edited");
        assertItemsLeft(1);
    }

    @Test
    public void testReopen() {
        add("a", "b");
        toggleAll();

        toggle("b");
        assertTasks("a", "b");
        assertItemsLeft(1);
    }

    @Test
    public void testReopenWithBug() {
        add("a", "b");
        toggleAll();

        toggle("b");
        assertTasks("a", "b");
        assertItemsLeft(1);
    }

}
