package core;

import com.codeborne.selenide.SelenideElement;

public interface SelenideElementWithAdditionalLogic extends SelenideElement {
    SelenideElementWithAdditionalLogic setValueWithoutChangeEvent(String value);
    SelenideElementWithAdditionalLogic pressEnterWithChangeEvent();
}
