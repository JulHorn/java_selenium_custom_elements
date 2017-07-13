package webelement.customElementsDecorator;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import webelement.customElements.superElements.CustomWebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * This class creates handles the calls of methods of custom webelements.
 **/
public class CustomElementLocator implements MethodInterceptor {

    /**
     * The locator to get the webelement from the webpage.
     **/
    private final ElementLocator locator;

    /**
     * The constructor.
     *
     * @param locator The locator to get the webelement from the webpage.
     **/
    public CustomElementLocator(ElementLocator locator) {
        this.locator = locator;
    }

    /**
     * Handles the method calls to a custom webelement.
     *
     * @param o           The object from which the method was called.
     * @param method      The called method.
     * @param objects     The parameter object of the value.
     * @param methodProxy Used to call the method of the superclass.
     **/
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // Configure a custom webelement (WebButton etc.)
        if (o instanceof CustomWebElement) {
            // Invokes the method of the original object
            try {
                return methodProxy.invokeSuper(o, objects);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
        // Configure a normal webelement
        // Should never be called in the current usecase because it gets handled in the CustomElementFieldDecorator class
        else if (o instanceof WebElement) {
            // Only handle first displayed
            // Get the first default webelement which matches the locator
            WebElement displayedElement = locateElement();

            if (displayedElement != null) {
                return method.invoke(displayedElement, objects);
            }
            else {
                return methodProxy.invokeSuper(o, objects);
            }
        }

        return null;
    }

    /**
     * Get an instance of the webelement.
     *
     * @return Returns a proxy element which implements the webelement.
     * This is needed to call the isVisible and other methods on itself (the custom web element)
     * without getting a nasty exception.
     **/
    private WebElement locateElement() {
        return proxyForLocator(ElementLocator.class.getClassLoader(), locator);
    }

    /**
     * Creates a dynamic proxy element for a webelement.
     * Stolen form the DefaultFieldDecorator.class class in the Selenium lib.
     * Further information on Proxies: https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Proxy.html
     *
     * @param loader  The class loader used to create the proxy.
     * @param locator The element locator used to locate the webelement.
     * @return The proxy webelement.
     **/
    private WebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new LocatingElementHandler(locator);
        WebElement proxy;

        proxy = (WebElement) Proxy.newProxyInstance(
                loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);

        return proxy;
    }
}
