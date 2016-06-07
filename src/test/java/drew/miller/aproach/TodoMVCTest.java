package drew.miller.aproach;

import com.nitorcreations.junit.runners.NestedRunner;
import drew.miller.aproach.categories.Buggy;
import drew.miller.aproach.categories.Smoke;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static pages.ToDoMVC.*;
import static pages.Givens.*;
import static pages.Givens.TaskType.*;

@RunWith(NestedRunner.class)
public class TodoMVCTest extends ScreenshotMakerAfterEachTest {

    public class AtActiveFilter {
        @Test
        public void testAdd() {
            givenAtActive(COMPLETED, "a");

            add("b");
            assertVisibleTasks("b");
            assertItemsLeft(1);
        }

        @Test
        public void testEdit() {
            givenAtActive(ACTIVE, "a", "b");

            edit("a", "a edited");
            assertTasks("a edited", "b");
            assertItemsLeft(2);
        }

        @Test
        public void testCancelEdit() {
            given(ACTIVE, "a");

            cancelEdit("a", "a edit cancelled");
            assertTasks("a");
            assertItemsLeft(1);
        }

        @Test
        public void testComplete() {
            givenAtActive(ACTIVE, "a", "b");

            toggle("a");
            assertVisibleTasks("b");
            assertItemsLeft(1);
        }

        @Test
        public void testDelete() {
            givenAtActive(ACTIVE, "a", "b");

            delete("b");
            assertTasks("a");
            assertItemsLeft(1);
        }

        @Test
        public void testConfirmByTab() {
            givenAtActive(ACTIVE, "a", "b");

            startEdit("a", "a edited").pressTab();
            assertTasks("a edited", "b");
            assertItemsLeft(2);
        }

        @Test
        public void testSwitchToAll() {
            givenAtActive(
                    aTask(ACTIVE, "a"),
                    aTask(COMPLETED, "b")
            );

            switchToAll();
            assertTasks("a", "b");
            assertItemsLeft(1);
        }
    }

    public class AtAllFilter {
        @Test
        public void testEdit() {
            given(ACTIVE, "a");

            edit("a", "a edited");
            assertTasks("a edited");
            assertItemsLeft(1);
        }

        @Test
        public void testReopen() {
            given(COMPLETED, "a", "b");

            toggle("b");
            assertTasks("a", "b");
            assertItemsLeft(1);
        }

        @Category(Buggy.class)
        @Test
        public void testReopen1() {
            given(COMPLETED, "a", "b");

            toggle("b");
            assertTasks("a", "b");
            assertItemsLeft(1);
        }

        @Test
        public void testCompleteAll() {
            given(
                    aTask(ACTIVE, "a"),
                    aTask(ACTIVE, "b"),
                    aTask(COMPLETED, "c")
            );

            toggleAll();
            assertTasks("a", "b", "c");
            assertItemsLeft(0);
        }

        @Test
        public void testReopenAll() {
            given(COMPLETED, "a", "b");

            toggleAll();
            assertTasks("a", "b");
            assertItemsLeft(2);
        }

        @Test
        public void testClearCompleted() {
            given(
                    aTask(ACTIVE, "a"),
                    aTask(COMPLETED, "b")
            );

            clearCompleted();
            assertTasks("a");
            assertItemsLeft(1);
        }

        @Category(Smoke.class)
        @Test
        public void testCancelEdit() {
            given(ACTIVE, "a", "b");

            cancelEdit("a", "a edit cancelled");
            assertTasks("a", "b");
            assertItemsLeft(2);
        }

        @Test
        public void testDeleteByRemovingText() {
            given(ACTIVE, "a", "b");

            edit("b", "");
            assertTasks("a");
            assertItemsLeft(1);
        }

        @Test
        public void testSwitchToCompleted() {
            given(
                    aTask(ACTIVE, "a"),
                    aTask(COMPLETED, "b")
            );

            switchToCompleted();
            assertVisibleTasks("b");
            assertItemsLeft(1);
        }
    }

    public class Integration {
        @Category(Smoke.class)
        @Test
        public void testTasksCommonFlow() {
            given();

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

    public class AtCompletedFilter {
        @Test
        public void testAdd() {
            givenAtCompleted(COMPLETED, "a");

            add("b");
            assertVisibleTasks("a");
            assertItemsLeft(1);
        }

        @Test
        public void testEdit() {
            givenAtCompleted(COMPLETED, "a", "b");

            edit("b", "b edited");
            assertTasks("a", "b edited");
            assertItemsLeft(0);
        }

        @Test
        public void testDelete() {
            givenAtCompleted(
                    aTask(COMPLETED, "a"),
                    aTask(ACTIVE, "b")
            );

            delete("a");
            assertNoVisibleTasks();
            assertItemsLeft(1);
        }

        @Test
        public void testCompleteAll() {
            givenAtCompleted(ACTIVE, "a", "b");

            toggleAll();
            assertTasks("a", "b");
            assertItemsLeft(0);
        }

        @Test
        public void testCancelEdit() {
            givenAtCompleted(COMPLETED, "a");

            cancelEdit("a", "a edit cancelled");
            assertTasks("a");
            assertItemsLeft(0);
        }

        @Test
        public void testReopenAll() {
            givenAtCompleted(COMPLETED, "a", "b");

            toggleAll();
            assertNoVisibleTasks();
            assertItemsLeft(2);
        }

        @Test
        public void testClearCompleted() {
            givenAtCompleted(
                    aTask(ACTIVE, "b"),
                    aTask(COMPLETED, "a")
            );

            clearCompleted();
            assertNoVisibleTasks();
            assertItemsLeft(1);
        }


        @Test
        public void testConfirmByClickOutside() {
            givenAtCompleted(
                    aTask(COMPLETED, "a"),
                    aTask(ACTIVE, "b")
            );

            confirmEditByClickOutside("a", "a edited");
            assertVisibleTasks("a edited");
            assertItemsLeft(1);
        }

        @Test
        public void testSwitchToActive() {
            givenAtCompleted(
                    aTask(COMPLETED, "a"),
                    aTask(ACTIVE, "b")
            );

            switchToActive();
            assertVisibleTasks("b");
            assertItemsLeft(1);
        }
    }
}
