package work_with_native_todoMVC;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;


public class TodosBaseTest {

    @BeforeClass
    public static void setBrowser() {
        Configuration.fastSetValue = true;
    }


}
