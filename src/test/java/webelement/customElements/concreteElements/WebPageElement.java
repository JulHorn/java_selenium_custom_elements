package webelement.customElements.concreteElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webelement.customElements.superElements.CustomWebElement;

import java.util.List;

public class WebPageElement extends CustomWebElement {

    /**
     * Constructor.
     *
     * @param webDriver The webDriver usd to interact with the webbrowser.
     * @param by        The locator used to identify the element(s) on the website.
     **/
    public WebPageElement(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    /**
     * Clicks on the button.
     **/
    public void click() {
        getWebDriver().findElement(getBy()).click();
    }

    /**
     * Sets the text of the element.
     **/
    public void setText(String text) {
        getWebDriver().findElement(getBy()).clear();
        getWebDriver().findElement(getBy()).sendKeys(text);
    }

    /**
     * Finds an element which uses the locator of this element as base.
     *
     * @return The found sub web element of this complex web element.
     **/
    public WebElement findElement(By locator) {
        return getWebDriver().findElement(getBy()).findElement(locator);
    }

    /**
     * Finds elements which uses the locator of this element as base.
     *
     * @return The found sub web elements of this complex web element.
     **/
    public List<WebElement> findElements(By locator) {
        return getWebDriver().findElement(getBy()).findElements(locator);
    }

    /**
     * Returns the node text of the element.
     *
     * @return Returns the node text of the element.
     **/
    public String getText() {
        return getWebDriver().findElement(getBy()).getText();
    }
}
