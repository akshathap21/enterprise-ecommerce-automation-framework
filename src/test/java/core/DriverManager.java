package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    // ThreadLocal allocates independent, isolated browser threads inside the JVM
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            ChromeOptions options = new ChromeOptions();
            //Creates an object used to configure and customize the behavior, arguments, and preferences
            //of the Chrome browser before it opens.
            
            options.addArguments("--remote-allow-origins=*");
           /*  A critical security flag that bypasses CORS (Cross-Origin Resource Sharing) and 
            * WebSocket connection security policies. 
            * It prevents Chrome from blocking connections between the Selenium driver 
            * and the browser itself (a common issue in modern Chrome updates).
            */
            
            
            options.addArguments("--start-maximized");
            /*Instructs Chrome to open fully maximized immediately on startup, replacing the older, 
            slower driver.manage().window().maximize() command.
            */
            
            // Selenium 4 automatically handles driver binary downloads natively
            driverThreadLocal.set(new ChromeDriver(options));
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove(); // Clears memory allocations to avoid leaks
        }
    }
}

/*Core Java Concepts Behind This
 * ThreadLocal Variable Architecture
 * ThreadLocal acts as a specialized data wrapper. It provides thread-isolation, meaning if 
 * Thread-A and Thread-B call this method simultaneously, they each get a dedicated, completely isolated WebDriver instance. 
 * No synchronized blocks or manual thread locks are required.
 * 
 * Encapsulation & The Factory Pattern
 * This code encapsulates the creation logic of the driver. 
 * By hiding how the driver is instantiated inside DriverManager, you implement a variation of the 
 * Factory Design Pattern to generate test workers safely.
 * 
 * Lazy Initialization
 * The enclosing if (driverThreadLocal.get() == null) check ensures the browser is only created 
 * at the exact moment a test requests it, conserving memory and system resources.
 * 
 */


/*
Interview Questions to Expect

1. "Why use ThreadLocal instead of a standard static WebDriver driver;?
"Expected Answer: A plain static driver is shared globally across the JVM. 
If you run tests in parallel, multiple threads will attempt to control the exact same browser window, 
causing immediate collision, random crashes, and test failures. 
ThreadLocal guarantees each test thread gets its own, completely isolated browser sandbox.

2. "Why use options.addArguments("--start-maximized") instead of driver.manage().window().maximize()?"
Expected Answer: The ChromeOptions argument maximizes the browser at the native OS level during launch. 
The standard driver.manage() method opens the browser in a default small window first,
 and then sends a secondary command to maximize it. 
The options approach is faster and avoids visual rendering issues on slow testing grids.

3. "What happens if you forget to call driverThreadLocal.remove() in your quitDriver method?"
Expected Answer: It creates a severe Memory Leak. When running tests in a CI/CD pipeline using a 
thread pool (like TestNG provider threads), the threads do not die immediately; they get reused. 
If you don't call .remove(), the references to old, dead WebDriver objects remain trapped in memory,
 eventually causing an OutOfMemoryError.
*/