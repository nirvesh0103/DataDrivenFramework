package com.modules;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InputForms {

	WebDriver driver;
	WebDriverWait wait;

	public InputForms(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void navigateToInputForms() {

		By inputFormsLoc = By.xpath("//ul[@id='treemenu']//a[text()='Input Forms']");
		WebElement inputformlink = driver.findElement(inputFormsLoc);
		inputformlink.click();

		By ajaxFormLoc = By.xpath("//ul[@id='treemenu']//a[text()='Ajax Form Submit']");
		WebElement ajaxFormLink = driver.findElement(ajaxFormLoc);
		ajaxFormLink.click();

	}

	void simpleCheckBoxDemo() {

		By checkBoxLinkLoc = By.xpath("//ul[@id='treemenu']//a[text()='Checkbox Demo']");
		WebElement checkboxDemolink = driver.findElement(checkBoxLinkLoc);
		checkboxDemolink.click();

		By ageCheckBoxLoc = By.id("isAgeSelected");
		WebElement agecheckbox = driver.findElement(ageCheckBoxLoc);
		System.out.println("check box is selected");
		agecheckbox.click();

		By checkAllboxloc = By.xpath("//input[@id='check1']");
		WebElement checkallbox = driver.findElement(checkAllboxloc);
		System.out.println("check all box");
		checkallbox.click();
		checkallbox.click();
		System.out.println("uncheck all box");
	}

	void datePicker() {

		By datePickerBoxLinkLoc = By.xpath("//ul[@id=\"treemenu\"]//a[text()='Date pickers']");
		WebElement datePickerBoxlink = driver.findElement(datePickerBoxLinkLoc);
		datePickerBoxlink.click();
		System.out.println("datepicker element is selected form");
		// ul[@id="treemenu"]//a[text()='Bootstrap Date Picker']
		By bootstripLoc = By.xpath("//ul[@id=\"treemenu\"]//a[text()='Bootstrap Date Picker']");
		WebElement bootstriplink = driver.findElement(bootstripLoc);
		bootstriplink.click();
		// td[contains(@class, 'day')and text()='26']
	}

	void formsDemo() throws InterruptedException {
		navigateToInputForms();

		By simpleFormDemoLoc = By.linkText("Simple Form Demo");
		WebElement simpleFormDemolink = driver.findElement(simpleFormDemoLoc);
		simpleFormDemolink.click();

		By enterMsgTextLoc = By.cssSelector("input#user-message");
		WebElement entermsgTextbox = driver.findElement(enterMsgTextLoc);
		entermsgTextbox.click();
		entermsgTextbox.clear();
		entermsgTextbox.sendKeys("This is the message for input box");
		Thread.sleep(2000);

		By showMsgBtnLoc = By.cssSelector("#get-input>button");
		Thread.sleep(2000);
		WebElement showMsgbutton = driver.findElement(showMsgBtnLoc);
		System.out.println("pass");
		showMsgbutton.click();
	}

	void tableDemo() {
		By tdLoc = By.xpath("//table[@id='task-table']//tr/td[3]");
		List<WebElement> assigneeCells = driver.findElements(tdLoc);

		for (WebElement assigneeCell : assigneeCells) {

			System.out.println("Assing is: " + assigneeCell.getText());
		}
	}

	public String ajaxFormDemo(String name, String comment) {

		By nameLoc = By.id("title");
		WebElement nameTextField = driver.findElement(nameLoc);
		nameTextField.sendKeys(name);

		By commentLoc = By.id("description");
		WebElement commentTextFiled = driver.findElement(commentLoc);
		commentTextFiled.sendKeys(comment);

		By sumbitBtnLoc = By.id("btn-submit");
		WebElement submitBtn = driver.findElement(sumbitBtnLoc);
		submitBtn.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit-control")));

		By submitMsgLoc = By.id("submit-control");
		WebElement submitMsg = driver.findElement(submitMsgLoc);

		String submitMsgText = submitMsg.getText();
		System.out.println("submit message text: " + submitMsgText);
		while (submitMsgText.equals("Ajax Request is Processing!")) {
			submitMsg = driver.findElement(submitMsgLoc);
			submitMsgText = submitMsg.getText();
		}

		return submitMsgText;
	}
}
