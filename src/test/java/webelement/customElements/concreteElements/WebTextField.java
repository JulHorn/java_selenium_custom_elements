package webelement.customElements.concreteElements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import webelement.customElements.superElements.CustomWebElement;

/**
 * A text field.
 **/
public class WebTextField extends CustomWebElement {

    /**
     * Constructor.
     *
     * @param webDriver The webDriver usd to interact with the webbrowser.
     * @param by        The locator used to identify the element(s) on the website.
     **/
    public WebTextField(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    /**
     * Returns the text of this textfield.
     *
     * @return Returns the text of this textfield.
     **/
    public String getText() {
        return getAttribute("value");
    }

    /**
     * Sets the text of this textfield.
     *
     * @param text The text which should be inserted in this text field.
     **/
    public void setText(String text) {
        getWebDriver().findElement(getBy()).clear();
        getWebDriver().findElement(getBy()).sendKeys(text);
    }

    /**
     * Tries to submit by pressing enter.
     */
    public void submit() {
        getWebDriver().findElement(getBy()).sendKeys(Keys.ENTER);
    }
}
