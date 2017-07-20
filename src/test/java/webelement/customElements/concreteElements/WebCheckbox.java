package webelement.customElements.concreteElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webelement.customElements.superElements.CustomWebElement;

/**
 * A checkbox.
 **/
public class WebCheckbox extends CustomWebElement {

    /**
     * Constructor.
     *
     * @param webDriver The webdriver usd to interact with the webbrowser.
     * @param by        The locator used to identify the element(s) on the website.
     **/
    public WebCheckbox(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    /**
     * Returns true if the checkbox is checked, else false.
     *
     * @return Returns true if the checkbox is checked, else false.
     **/
    public boolean isChecked() {
        return getWebDriver().findElement(getBy()).isSelected();
    }

    /**
     * Checks the checkbox.
     **/
    public void check() {
        if (!isChecked()) {
            getWebDriver().findElement(getBy()).click();
        }
    }

    /**
     * Unchecks a checkbox.
     **/
    public void uncheck() {

        if (isChecked()) {
            getWebDriver().findElement(getBy()).click();
        }
    }
}
