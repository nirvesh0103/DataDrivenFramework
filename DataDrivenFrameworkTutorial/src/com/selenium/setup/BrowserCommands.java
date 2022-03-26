package com.selenium.setup;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.ArrayList;
import java.util.List;

public class BrowserCommands {

    WebDriver driver;

    public BrowserCommands(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Function to open the web page in a browser
     * @param URL - Type {@link String} - Value - URL of a website.
     */
    public void openURL(String URL){
        driver.get(URL);
    }

    /**
     * Function to get the page title of currently opened webpage
     * @return Type - {@link String} - Page title of currently opened webpage.
     */
    public String getPageTitle(){
        return driver.getTitle();
    }

    /**
     * Function to get the url of the currently opened webpage.
     * @return Type - {@link String} - URL/link of the currently opened webpage.
     */
    public String getPageURL(){
        return driver.getCurrentUrl();
    }

    /**
     * Function to set the opened browser size.
     * @param width - Type {@link Integer} - value greater than 0 to set the browser width of given value
     * @param height - Type {@link Integer} - value any integer value greater than 0 to set the browser height of given value
     * @param maximize - Type {@link Boolean} - value true to maximize the browser
     */
    public void setBrowserSize(int width, int height, boolean maximize){

        if(maximize || width <=0 || height <=0) {
            driver.manage().window().maximize();
        } else if(width > 0 && height > 0){
            Dimension dm = new Dimension(width, height);
            driver.manage().window().setSize(dm);
        }
    }

    /**
     * Function to open a new Tab or window.
     * @param windowType - Type {@link String} - Values - Window, Tab
     * @return Type {@link String} - returns the window handle/name of newly opened tab/window.
     */
    public String openNewTabOrWindow(String windowType){
        if (windowType.equalsIgnoreCase("Window")){
            driver.switchTo().newWindow(WindowType.WINDOW);
        } else{
            driver.switchTo().newWindow(WindowType.TAB);
        }

        return driver.getWindowHandle();
    }

    /**
     * Function to get the name of a currently opened tab/window
     * @return Type - {@link String} - name of the currently opened tab
     */
    public String getWindowOrTabHandle(){
        return driver.getWindowHandle();
    }

    /**
     * Function to get the name of all the opened window tabs.
     * @return Type - {@link ArrayList} - Returns the name of the all the opened windows.
     */
    public List<String> getOpenedWindowNames(){

        return new ArrayList<>(driver.getWindowHandles());
    }

    /**
     * Function to switch to a specific tab/window.
     * @param windowHandle - Type {@link String} - Value: window handle/name of the tab
     */
    public void switchToTabOrWindow(String windowHandle){
        driver.switchTo().window(windowHandle);
    }

    /**
     * Function to close currently opened browser tab/window or close all the browser windows/tabs
     * @param allWindows - Type boolean - Value: true to close all the browser windows
     *                                          : False to close the current browser window
     */
    public void closeBrowserWindow(boolean allWindows){
        if(allWindows){
            driver.quit();
        }else{
            driver.close();
        }
    }
}
