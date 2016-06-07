package core;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.impl.Events.events;
import static com.codeborne.selenide.Configuration.fastSetValue;

import java.io.IOException;

public class PressEnterWithChangeEvent implements Command<SelenideElementWithAdditionalLogic> {
    public SelenideElementWithAdditionalLogic execute(SelenideElement proxy, WebElementSource locator, Object[] args) {

        if (fastSetValue)
            events.fireEvent(locator.findAndAssertElementIsVisible(), "focus", "keydown", "keypress", "input", "keyup", "change");
        else
            events.fireChangeEvent(locator.findAndAssertElementIsVisible());

        locator.findAndAssertElementIsVisible().sendKeys(new CharSequence[]{Keys.ENTER});
        return (SelenideElementWithAdditionalLogic) proxy;
    }
}
