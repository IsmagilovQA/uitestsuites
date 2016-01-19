package core;

import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;
import pages.ToDoMVCPage;

import static com.codeborne.selenide.Selenide.open;


public class TodosBaseTest {
    public ToDoMVCPage page = new ToDoMVCPage();

//   {
//       Configuration.browser = System.getProperty("driver.browser");
//   }

    @Before
    public void openToDoMVCBeforeTest() { page.openToDoMVC(); }

    @After
    public void ClearDataAfterTest() { page.clearData(); }

}
