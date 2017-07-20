package example.exampleElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webelement.customElements.superElements.CustomWebElement;

import java.util.List;

/**
 * A module, which represents the bing search result list and offers functionality to interact with it.
 **/
public class BingResultListModule extends CustomWebElement {

    /**
     * Constructor.
     *
     * @param webDriver The webDriver to interact with the browser.
     * @param by        The locator used to identify the element(s) on the website.
     **/
    public BingResultListModule(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    /**
     * Opens a search result by clicking on the result link.
     *
     * @param searchResultNumber The number of the search result, which should be opened. 0 is the first search result.
     **/
    public void openSearchResult(int searchResultNumber) {
        WebElement listContainer = getWebDriver().findElement(getBy());
        List<WebElement> resultLinks = listContainer.findElements(By.xpath(".//li//h2//a"));

        resultLinks.get(searchResultNumber).click();
    }
}
