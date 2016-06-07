package work_with_native_todoMVC;

import org.junit.Test;

import static pages.ToDoMVC.*;
import static pages.Givens.*;
import static pages.Givens.TaskType.*;

public class TodoMVCTest extends ScreenshotMakerAfterEachTest {

    public class AtActiveFilterTest {
        @Test
        public void testAddAtActive() {
            givenAtActive(COMPLETED, "a");

            add("b");
            assertVisibleTasks("b");
            assertItemsLeft(1);
        }

        @Test
        public void testEditAtActive() {
            givenAtActive(ACTIVE, "a", "b");

            edit("a", "a edited");
            assertTasks("a edited", "b");
            assertItemsLeft(2);
        }

        @Test
        public void testCancelEditAtActive() {
            given(ACTIVE, "a");

            cancelEdit("a", "a edit cancelled");
            assertTasks("a");
            assertItemsLeft(1);
        }

        @Test
        public void testCompleteAtActive() {
            givenAtActive(ACTIVE, "a", "b");

            toggle("a");
            assertVisibleTasks("b");
            assertItemsLeft(1);
        }

        @Test
        public void testDeleteAtActive() {
            givenAtActive(ACTIVE, "a", "b");

            delete("b");
            assertTasks("a");
            assertItemsLeft(1);
        }

        @Test
        public void testConfirmByTabAtActive() {
            givenAtActive(ACTIVE, "a", "b");

            startEdit("a", "a edited").pressTab();
            assertTasks("a edited", "b");
            assertItemsLeft(2);
        }

        @Test
        public void testSwitchFromActiveToAll() {
            givenAtActive(
                    aTask(ACTIVE, "a"),
                    aTask(COMPLETED, "b")
            );

            switchToAll();
            assertTasks("a", "b");
            assertItemsLeft(1);
        }
    }

    public class AtAllFilterTest {
        @Test
        public void testEditAtAll() {
            given(ACTIVE, "a");

            edit("a", "a edited");
            assertTasks("a edited");
            assertItemsLeft(1);
        }

        @Test
        public void testReopenAtAll() {
            given(COMPLETED, "a", "b");

            toggle("b");
            assertTasks("a", "b");
            assertItemsLeft(1);
        }

        @Test
        public void testCompleteAllAtAll() {
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
        public void testReopenAllAtAll() {
            given(COMPLETED, "a", "b");

            toggleAll();
            assertTasks("a", "b");
            assertItemsLeft(2);
        }

        @Test
        public void testClearCompletedAtAll() {
            given(
                    aTask(ACTIVE, "a"),
                    aTask(COMPLETED, "b")
            );

            clearCompleted();
            assertTasks("a");
            assertItemsLeft(1);
        }

        @Test
        public void testCancelEditAtAll() {
            given(ACTIVE, "a", "b");

            cancelEdit("a", "a edit cancelled");
            assertTasks("a", "b");
            assertItemsLeft(2);
        }

        @Test
        public void testDeleteByRemovingTextAtAll() {
            given(ACTIVE, "a", "b");

            edit("b", "");
            assertTasks("a");
            assertItemsLeft(1);
        }

        @Test
        public void testSwitchFromAllToCompleted() {
            given(
                    aTask(ACTIVE, "a"),
                    aTask(COMPLETED, "b")
            );

            switchToCompleted();
            assertVisibleTasks("b");
            assertItemsLeft(1);
        }
    }

    public class IntegrationTest {
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

    public class AtCompletedFilterTest {
        @Test
        public void testAddAtCompleted() {
            givenAtCompleted(COMPLETED, "a");

            add("b");
            assertVisibleTasks("a");
            assertItemsLeft(1);
        }

        @Test
        public void testEditAtCompleted() {
            givenAtCompleted(COMPLETED, "a", "b");

            edit("b", "b edited");
            assertTasks("a", "b edited");
            assertItemsLeft(0);
        }

        @Test
        public void testDeleteAtCompleted() {
            givenAtCompleted(
                    aTask(COMPLETED, "a"),
                    aTask(ACTIVE, "b")
            );

            delete("a");
            assertNoVisibleTasks();
            assertItemsLeft(1);
        }

        @Test
        public void testCompleteAllAtCompleted() {
            givenAtCompleted(ACTIVE, "a", "b");

            toggleAll();
            assertTasks("a", "b");
            assertItemsLeft(0);
        }

        @Test
        public void testCancelEditAtCompleted() {
            givenAtCompleted(COMPLETED, "a");

            cancelEdit("a", "a edit cancelled");
            assertTasks("a");
            assertItemsLeft(0);
        }

        @Test
        public void testReopenAllAtCompleted() {
            givenAtCompleted(COMPLETED, "a", "b");

            toggleAll();
            assertNoVisibleTasks();
            assertItemsLeft(2);
        }

        @Test
        public void testClearCompletedAtCompleted() {
            givenAtCompleted(
                    aTask(ACTIVE, "b"),
                    aTask(COMPLETED, "a")
            );

            clearCompleted();
            assertNoVisibleTasks();
            assertItemsLeft(1);
        }

        @Test
        public void testFromAllToCompleted() {
            given(
                    aTask(ACTIVE, "a"),
                    aTask(COMPLETED, "b")
            );

            switchToCompleted();
            assertVisibleTasks("b");
        }

        @Test
        public void testConfirmByClickOutsideAtCompleted() {
            givenAtCompleted(
                    aTask(COMPLETED, "a"),
                    aTask(ACTIVE, "b")
            );

            confirmEditByClickOutside("a", "a edited");
            assertVisibleTasks("a edited");
            assertItemsLeft(1);
        }

        @Test
        public void testSwitchFromCompletedToActive() {
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
