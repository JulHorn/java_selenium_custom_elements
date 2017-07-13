package webelement.customElements.concreteElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webelement.customElements.superElements.CustomWebElement;

/**
 * A label.
 **/
public class WebLabel extends CustomWebElement {

    /**
     * Constructor.
     *
     * @param webDriver The webDriver to interact with the webbrowser.
     * @param by        The locator used to identify the element(s) on the webpage.
     **/
    public WebLabel(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

	/* ----- Methods ----- */

    /**
     * Returns the text of the label.
     **/
    public String getText() {
        return getWebDriver().findElement(getBy()).getText();
    }
}
