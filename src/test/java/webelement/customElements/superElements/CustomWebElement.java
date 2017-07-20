package webelement.customElements.superElements;

import org.openqa.selenium.support.PageFactory;
import webelement.customElementsDecorator.CustomElementFieldDecorator;
import webelement.modules.WebElementTransformer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Parent class for all complex web elements.
 **/
public abstract class CustomWebElement {

    /**
     * The webDriver which can be used in subclasses.
     **/
    private WebDriver webDriver;

    /**
     * The locator through which the element(s) used for an action will be identified.
     **/
    private By locator;

    /**
     * Used to access the locators of a webelement.
     * **/
    private WebElementTransformer transformer;

    /**
     * Constructor.
     *
     * @param webDriver The webDriver used to interact with the webbrowser.
     * @param by        The locator used to identify the element(s) on the website.
     **/
    public CustomWebElement(WebDriver webDriver, By by) {
        this.webDriver = webDriver;
        locator = by;
        transformer = new WebElementTransformer();

        // Call the page factory on this object to initialize custom webelements in custom webelements (aka nesting)
        PageFactory.initElements(new CustomElementFieldDecorator(webDriver, webDriver), this);
    }

    /**
     * Returns the locator to identify the element(s) on the website.
     *
     * @return Returns the locator to identify the element(s) on the website.
     **/
    public By getBy() {
        return locator;
    }

    /**
     * The element must be visible in order to retrieve its attribute.
     * Get the value of a the given attribute of the element. Will return the current value, even if
     * this has been modified after the page has been loaded. More exactly, this method will return
     * the value of the given attribute, unless that attribute is not present, in which case the value
     * of the property with the same name is returned (for example for the "value" property of a
     * textarea element). If neither value is set, null is returned. The "style" attribute is
     * converted as best can be to a text representation with a trailing semi-colon. The following are
     * deemed to be "boolean" attributes, and will return either "true" or null:
     * <p>
     * async, autofocus, autoplay, checked, compact, complete, controls, declare, defaultchecked,
     * defaultselected, defer, disabled, draggable, ended, formnovalidate, hidden, indeterminate,
     * iscontenteditable, ismap, itemscope, loop, multiple, muted, nohref, noresize, noshade,
     * novalidate, nowrap, open, paused, pubdate, readonly, required, reversed, scoped, seamless,
     * seeking, selected, spellcheck, truespeed, willvalidate
     * <p>
     * Finally, the following commonly mis-capitalized attribute/property names are evaluated as
     * expected:
     * <p>
     * <ul>
     * <li>"class"
     * <li>"readonly"
     * </ul>
     *
     * @param attributeName The name of the attribute.
     * @return The attribute/property's current value or null if the value is not set.
     */
    public String getAttribute(String attributeName) {
        return getWebDriver().findElement(getBy()).getAttribute(attributeName);
    }

    /**
     * Returns the module to transform stuff.
     *
     * @return Returns the module to transform stuff.
     **/
    protected WebElementTransformer transformer() {
        return transformer;
    }

    /**
     * Returns the webDriver.
     *
     * @return Returns the webDriver.
     **/
    protected WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * Returns the used type of a given by locator.
     *
     * @return Returns the used type of a given by locator.
     **/
    protected WebElementTransformer.LocatorType getLocatorType() {
        return transformer.getLocatorType(getBy());
    }

    /**
     * Returns the locator value of a locator.
     *
     * @param type The type of the locator.
     * @return The value of the locator.
     **/
    protected String getLocatorValue(WebElementTransformer.LocatorType type) {
        return transformer.getLocatorValue(getBy(), type);
    }
}