package testCases;

import java.io.IOException;
import java.time.Duration;

import utilities.Constants;
import utilities.ExcelUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.modules.InputForms;
import com.selenium.setup.BrowserCommands;
import com.selenium.setup.SetupSeleniumWebDriver;

public class Test_Ajax_Form_demo {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = null;
		try {
			SetupSeleniumWebDriver.setBrowserDriver(Constants.BROWSER_NAME);
			driver = SetupSeleniumWebDriver.launchBrowserUsingWDM(Constants.BROWSER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.get(Constants.URL);

		Test_Ajax_Form_demo webEleExample = new Test_Ajax_Form_demo();
		webEleExample.waitAndClosePopup(driver);

		InputForms inputForms = new InputForms(driver);

		ExcelUtils excelUtils = new ExcelUtils();
		String filePath = Constants.DIR_TEST_DATA + Constants.AJAX_FORM_FILE_NAME;

		excelUtils.setupExcelFile(filePath, "Sheet1");

		int noOfRows = excelUtils.getRowCountInExcelWorkSheet();

		System.out.println("Total number of rows: " + noOfRows);
		String name = "";
		String comment = "";

		for (int currentRow = 1; currentRow <= noOfRows; currentRow++) {
			inputForms.navigateToInputForms();

			name = excelUtils.getCellValue(currentRow, 0);
			comment = excelUtils.getCellValue(currentRow, 1);

			String successMsg = inputForms.ajaxFormDemo(name, comment);
			if (successMsg.equals("Form submited Successfully!")) {
				excelUtils.setCellValue(currentRow, 2, "PASS", filePath);
			} else {
				excelUtils.setCellValue(currentRow, 2, "FAIL", filePath);
			}

		}

		BrowserCommands browserCommands = new BrowserCommands(driver);
		browserCommands.closeBrowserWindow(true);

	}

	void waitAndClosePopup(WebDriver driver) throws InterruptedException {
		By closePopUpLoc = By.id("at-cv-lightbox-close");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(closePopUpLoc));

		WebElement closePopupLoc = driver.findElement(closePopUpLoc);
		closePopupLoc.click();
	}
}