package example.pages;

import example.exampleElements.BingResultListModule;
import example.exampleElements.BingSearchModule;
import org.openqa.selenium.support.FindBy;

/**
 * The Bing page object.
 * In order to keep this example simple, this page object contains both the search logic and the result list logic.
 **/
public class BingPage {

    /**
     * Includes the bing search module in this page.
     * If the custom webelement does not use the given locator, you can simply insert what you want.
     **/
    @FindBy(xpath = "NotNeeded")
    private BingSearchModule searchModule;

    /**
     * Includes the bing result list module in this page.
     * The given locator can be used in the custom webelement.
     **/
    @FindBy(id = "b_results")
    private BingResultListModule resultListModule;

    /**
     * Searches for a text.
     *
     * @param searchText The text for which will be searched.
     **/
    public void search(String searchText) {
        searchModule.search(searchText);
    }

    /**
     * Opens a search result by clicking on the result link.
     *
     * @param searchResultNumber The number of the search result, which should be opened.
     **/
    public void openSearchResult(int searchResultNumber) {
        resultListModule.openSearchResult(searchResultNumber);
    }
}