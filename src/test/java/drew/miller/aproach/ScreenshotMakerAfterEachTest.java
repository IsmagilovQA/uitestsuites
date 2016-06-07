package drew.miller.aproach;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.junit.After;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

public class ScreenshotMakerAfterEachTest {

    static {
        Configuration.pageLoadStrategy = "normal";
    }

    @Attachment(type = "image/png")
    public byte[] makeScreenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }

    @After
    public void screenshot() throws IOException {
        makeScreenshot();
    }
}
