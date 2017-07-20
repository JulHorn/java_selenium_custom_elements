package webelement.customElements.concreteElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webelement.customElements.superElements.CustomWebElement;

import java.util.List;

/**
 * Represents a group of radioboxes.
 **/
public class WebRadioboxGroup extends CustomWebElement {

    /**
     * Constructor. Initializes the webelement.exampleElements.
     *
     * @param webdDiver The webdDiver usd to interact with the webbrowser.
     * @param by        The locator used to identify the element(s) on the website.
     **/
    public WebRadioboxGroup(WebDriver webdDiver, By by) {
        super(webdDiver, by);
    }

    /**
     * Returns the index of the selected radiobox group.
     *
     * @return The index of the selected radiobox group option.
     **/
    public int getSelectedRadioBoxOptionIndex() {
        List<WebElement> options = getWebDriver().findElements(getBy());

        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).isSelected()) {
                return i;
            }
        }

        throw new IllegalArgumentException("There is no selected option for the radiobox " + getBy());
    }

    /**
     * Clicks the radiobox option and selects it.
     *
     * @param index The index of the option which should be clicked.
     **/
    public void clickRadioboxOption(int index) {
        getWebDriver().findElements(getBy()).get(index).click();
    }
}
