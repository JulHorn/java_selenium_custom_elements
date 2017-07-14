# Java Selenium Custom WebElements

Offers an additional abstraction layer around the Selenium WebElement class when using the page factory by creating custom Webelements.
These custom WebElements can be anything from a simple button to something as complex as a table and simply be used via the @FindBy annotation just like the default Selenium WebElements.

The project consists of the actual source code which enables the usage of custom WebElements, some simple custom WebElements, an example on how to use it and a gradle file, which can be used to
import the code as an project into any gradle supporting IDE (IntelliJ, Eclipse, ...).

Benefits:
1.

ToDo: Make an understandable example

## Usage

The custom WebElement code is located in the "src/test/java/webelement" folder. Simply copy the content to your own project and use it however you like.
The following steps are necessary to use the custom WebElements:

1. (Optional) Create a custom WebElement by subclassing the "CustomWebElement" class.
2: Use the PageFactory on a class which contains @FindBy annotated custom WebElements like this: "PageFactory.initElements(new CustomElementFieldDecorator(WebDriverObject, WebDriverObject), PageObject);".

ToDo: Explain super class
ToDo: Mention links
ToDo: Modify gitignore files

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

The example demonstrates the usage custom WebElements searching via Bing and opening the first search result.
The project can be found in the "test" source folder in the "example" package. It consists of the following packages:

1. "exampleElements": Two example custom WebElements representing the Bing search form and the Bing result list. They are subclasses from the "CustomWebElement" class.
2. "pages": Contains a a page object representing the Bing page which uses the custom WebElements from 1.
3. "tests": Contains the test class. Execute the example via this class. The PageFactory is used in this class.