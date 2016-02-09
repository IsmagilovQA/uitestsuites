package core;




import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.impl.ElementFinder;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class AdditionalSelenideAPI {

    public static void setUp() {
        Commands.collection.add("setValueWithoutChangeEvent", new SetValueWithoutChangeEvent());
        Commands.collection.add("pressEnterWithChangeEvent", new PressEnterWithChangeEvent());
    }

    /**
            * Replacement for standard Selenide `$` method.
    * @param selector CSS selector
    * @return MySelenideElement - an "advanced" version of `SelenideElement` with more custom methods
    */
    public static SelenideElementWithAdditionalLogic $_(String selector) {
        return ElementFinder.wrap(SelenideElementWithAdditionalLogic.class, null, By.cssSelector(selector), 0);
    }

    public static SelenideElementWithAdditionalLogic $_(By by) {
        return ElementFinder.wrap(SelenideElementWithAdditionalLogic.class, null, by, 0);
    }
}
