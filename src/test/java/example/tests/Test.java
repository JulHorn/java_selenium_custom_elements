package example.tests;

import example.pages.BingPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import webelement.customElementsDecorator.CustomElementFieldDecorator;

import java.util.concurrent.TimeUnit;

/**
 * A simple example on how to use the custom elements in a test.
 **/
public class Test {

    /**
     * The driver which is used to interact with the browser.
     **/
    private WebDriver webDriver;

    /**
     * The set up method which creates the driver for each test and turns the implicit wait feature off.
     **/
    @Before
    public void setUp() {
        // Setup chrome driver via webdrivermanager for easy usage
        ChromeDriverManager.getInstance().setup();

        // Create it
        webDriver = new ChromeDriver();

        // Enable implicit wait
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * After a test is finished, ensure that the browser will be closed.
     **/
    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    /**
     * A simple test, which searches via bing and opens the first result.
     **/
    @org.junit.Test
    public void bingExampleTest() {
        // The Bing page object
        // To keep the example simple, it contains logic for the search and list
        BingPage bingPage = new BingPage();

        // Open the Bing page
        webDriver.get("https://www.bing.com/");

        // Uses the Selenium page factory in order to initialize the Bing page object
        // The CustomElementFieldDecorator statement is needed for the usage of the custom elements
        PageFactory.initElements(new CustomElementFieldDecorator(webDriver, webDriver), bingPage);
        // Do the search and open result stuff
        bingPage.search("Test");
        bingPage.openSearchResult(0);
    }
}
