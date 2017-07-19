# Java Selenium Custom WebElements

The Selenium custom WebElements offer an additional abstraction layer around the Selenium WebElement class when using the page factory. 
It is possible to create simple custom elements from dropdown menus up to complex modules like radio button groups or tables. Each custom element can contain other custom WebElements or Selenium WebElements.
This allows you to encapsulate functionality on how to interact with elements on a website in on place and still have an easy way to use the WebElements.

The project consists of the actual source code, which enables the usage of custom WebElements, some simple custom WebElements, an example on how to use it and a gradle file, which can be used to
import the code as an project into any gradle supporting IDE (IntelliJ, Eclipse, ...).

## Usage

The custom WebElement code is located in the "src/test/java/webelement" folder. Simply copy the content to your own project and use it however you like.
This chapter will cover the basic usage/extension of the custom WebElements. For this, a shortened version of the example code (see chapter "Example") is being used.

### 1: (Optional) Create a custom WebElement
Create a custom WebElement by subclassing the "CustomWebElement" class. All subclasses of "CustomWebElement" can be used with the PageFactory. 

```java
/* Subclass from CustomWebElement to create your own custom WebElement*/

public class BingSearchModule extends CustomWebElement {

    /* Use default Selenium WebElements or other custom WebElements */
    
    @FindBy(id = "sb_form_q")
    private WebTextField searchField;
    
    @FindBy(id = "sb_form_go")
    private WebButton searchButton;

    /*  The constructor. */
    
    public BingSearchModule(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    /* Operations on the page */
    
    public void search(String searchText) {
        searchField.setText(searchText);
        searchButton.click();
    }
}

```
### 2: Use a custom WebElement

The custom WebElements can be used the same as the default Selenium WebElements by using the "@FindBy" annotation.

```java
public class BingPage {

    @FindBy(id = "b_results")
    private BingResultListModule resultListModule;

    public void openSearchResult(int searchResultNumber) {
        resultListModule.openSearchResult(searchResultNumber);
    }
}

```

### 3: Use the PageFactory

Use the PageFactory on a class which contains @FindBy annotated custom WebElements like this: "PageFactory.initElements(new CustomElementFieldDecorator(WebDriverObject, WebDriverObject), PageObject);".


```java
public class Test{
    public void bingExampleTest() {
        BingPage bingPage = new BingPage();
        WebDriver webDriver = new ChromeDriver();
        
        webDriver.get("https://www.bing.com/");
    
        // Uses the Selenium page factory in order to initialize the Bing page object
        // The CustomElementFieldDecorator statement is needed for the usage of the custom elements
        PageFactory.initElements(new CustomElementFieldDecorator(webDriver, webDriver), bingPage);
        // Do the search and open result stuff
        bingPage.search("Test");
        bingPage.openSearchResult(0);
    }
}
```

## Structure of the custom WebElements

1. The "customElementsDecorator" package: This is where the magic happens. The code is quite complex but hopefully good enough commented to understand what happens there. There are usually no code changes necessary in this package.
2. The "modules" package: Contains one helper class, e.g. for getting the used locator from an already existing WebElement.
3. The "customElements" package. It consists of
    1. the "superElements" package: This package contains the super class from which all other custom WebElements are subclassed.
    2. the "concreteElements" package: Contains some basic custom WebElements like
        1. WebButton: A simple button.
        2. WebLink: A simple link.
        3. WebCheckbox: A simple checkbox.
        4. WebLabel: A simple label.
        5. WebPageElement: Equivalent to the default Selenium WebElement. Use this if other elements do not match.

### Dependencies

In order to use this feature you have to add the following dependencies to your project:

1. [Selenium server](http://docs.seleniumhq.org/download/) (tested with version 3.3.1)

The version of the dependencies should not influence this projects behaviour.
Look up the gradle file to see the gradle dependencies declarations.

## Example

The example demonstrates the usage of custom WebElements by doing a search via Bing and opening the first search result.
The project can be found in the "test" source folder in the "example" package. It consists of the following packages:

1. "exampleElements": Two example custom WebElements representing the Bing search form and the Bing result list. They are subclasses from the "CustomWebElement" class.
    1. "BingSearchModule" uses some default custom WebElements in order to form a more complex custom WebElement.
    2. "BingResultListModule" uses the "getBy()" method in order to access the element locator, which is passed from the "BingPage" webpage object, and uses it to interact with the website.
2. "pages": Contains a a page object representing the Bing page, which uses the custom WebElements from 1.
3. "tests": Contains the test class. Execute the example via this class. The PageFactory is used in this class.

## Sources

The following sources were used as basis to create this project:

* http://www.alechenninger.com/2014/07/a-case-study-of-javas-dynamic-proxies_14.html
* http://www.mograblog.com/2013/08/extending-selenium-in-java.html