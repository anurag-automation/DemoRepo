package com.edureka.keyworddriven.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.edureka.keyworddriven.base.Base;



public class KeyWordEngine {

	public WebDriver driver;

	public Properties prop;

	public static Workbook book;
	public static Sheet sheet;

	public Base base;
	public WebElement element;

	public final String SCENARIO_SHEET_PATH = "C:\\Users\\Anurag\\eclipse-workspace\\EdurekaAssignmentKeywordDriven\\src\\main\\java\\com\\edureka\\keyworddriven\\scenarios\\91mobiles_scenarios.xlsx";

	public void startExecution(String sheetName) {

		FileInputStream file = null;

		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			try {
				book = WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
		} catch (EncryptedDocumentException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		int k = 0;

		for (int i = 0; i <= sheet.getLastRowNum(); i++)
			try {
				{
					String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
					String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
					String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
					String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

					switch (action) {
					case "LAUNCH_BROWSER":

						base = new Base();
						prop = base.init_properties();

						if (value.isEmpty() || value.equals("NA")) {

							driver = base.init_driver(prop.getProperty("browser"));

						} else {

							driver = base.init_driver(value);
						}

						break;

					case "OPEN_URL":

						if (value.isEmpty() || value.equals("NA")) {

							driver.get(prop.getProperty("url"));

						} else {

							driver.get(value);
							Thread.sleep(6000);
						}
						break;
					case "CLOSE_BROWSER":

						Thread.sleep(6000);
						driver.quit();
						break;
					default:
						break;
					}

					switch (locatorType) {
					case "id":
						element = driver.findElement(By.id(locatorValue));

						if (action.equalsIgnoreCase("ENTER_TEXT")) {
							element.clear();
							element.sendKeys(value);
						} else if (action.equalsIgnoreCase("CLICK")) {

							element.click();
						}else if (action.equalsIgnoreCase("VERIFY_TEXT")) {

							String elementText = element.getText();
							System.out.println("Text from element " + elementText);
						}else if (action.equalsIgnoreCase("PRINT_TEXT")) {

							String elementText = element.getText();
							System.out.println("Text from element " + elementText);
						}
						locatorType = null;
						break;

				
					case "className":
						Thread.sleep(8000);
						element = driver.findElement(By.className(locatorValue));

						if (action.equalsIgnoreCase("ENTER_TEXT")) {
							element.clear();
							element.sendKeys(value);
						} else if (action.equalsIgnoreCase("CLICK")) {

							element.click();
						
						} else if (action.equalsIgnoreCase("VERIFY_TEXT")) {

							String elementText = element.getText();
							System.out.println("Text from element " + elementText);
						}else if (action.equalsIgnoreCase("PRINT_TEXT")) {

							String elementText = element.getText();
							System.out.println("Text from element " + elementText);
						}
						locatorType = null;
						break;
					case "xpath":
						Thread.sleep(8000);
						element = driver.findElement(By.xpath(locatorValue));

						if (action.equalsIgnoreCase("ENTER_TEXT")) {
							element.clear();
							element.sendKeys(value);
						} else if (action.equalsIgnoreCase("CLICK")) {

							element.click();
						}  else if (action.equalsIgnoreCase("VERIFY_TEXT")) {

							String elementText = element.getText();
							System.out.println("Text from element " + elementText);
						}else if (action.equalsIgnoreCase("PRINT_TEXT")) {

							String elementText = element.getText();
							System.out.println("Text from element " + elementText);
						}

						locatorType = null;
						break;

					default:
						break;
					}
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

	}

}
