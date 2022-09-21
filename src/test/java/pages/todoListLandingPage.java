package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class todoListLandingPage {

	//Setting Project Path for code to be portable and usable on any machine
	String projectPath = System.getProperty("user.dir");
	protected WebDriver driver;
	public todoListLandingPage(WebDriver driver){
		this.driver = driver;
	}

	//List of Objects to be used on the landing page
	private By todosText = By.xpath("//h1[contains(text(),'todos')]");
	private By todosInput = By.xpath("//h1[contains(text(),'todos')]/../input");
	private String enteredTextPartialPath = "//h1[contains(text(),'todos')]/../../section/ul/li[";
	private String tempPartialXpath = "//*[contains(text(),'";
	private By markAllAsCompletedButton = By.xpath("//label[@for='toggle-all']");
	private By clearCompletedButton = By.xpath("//button[contains(text(),'Clear completed')]");
	private By ActiveFilterButton = By.xpath("//a[contains(text(),'Active')]");
	private By CompletedFilterButton = By.xpath("//a[contains(text(),'Completed')]");


	//Function to open the desired web browser - Chrome or Edge
	public WebDriver openBrowser(String browser){
		switch(browser){  
		case "chrome": 	
			System.out.println("=========================================Test started in CHROME Browser=========================================");
			System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().window().maximize();  
			break; 
		case "edge": 	
			System.out.println("=========================================Test started in EDGE Browser=========================================");
			System.setProperty("webdriver.edge.driver", projectPath + "/src/test/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().window().maximize();  
			break;
		default:throw new InvalidArgumentException("Invalid Browser Value Entered. Please Check. Accepted values are - chrome, edge");  
		}  
		return driver;
	}

	//Function to navigate to To-Do List Page URL 
	public void navigateToUrl(String url){
		driver.navigate().to(url);
	}

	//Function to verify the successful loading of the todos page
	public void verifyLandingPage(){
		if (!driver.findElement(todosText).isDisplayed()){
			throw new NoSuchElementException("Landing page did not load successfully");
		}
	}

	//Function to enter the values in the to do list text-box
	public void enterTextToDoList(String text){
		String[] inputValues = text.split(",");
		for (int i = 0; i < inputValues.length; i ++){
			driver.findElement(todosInput).click();
			driver.findElement(todosInput).sendKeys(inputValues[i]);
			driver.findElement(todosInput).sendKeys(Keys.ENTER);
		}
	}


	//Function to click on various buttons
	public void buttonClick(String filterType) throws InterruptedException{
		switch(filterType){  
		case "Active": 	
			driver.findElement(ActiveFilterButton).click();
			break; 
		case "Completed": 	
			driver.findElement(CompletedFilterButton).click();
			break;
		case "Clear completed": 	
			driver.findElement(clearCompletedButton).click();
			Thread.sleep(5000);
			break;
		default:throw new InvalidArgumentException("Invalid Button-Name Value Entered. Please Check. Accepted values are - Active, Completed, Clear completed");  
		}  
	}

	//Function to clear and edit existing values in the to do list
	public void editTextToDoList(String text){
		String[] inputValues = text.split(",");
		Actions act = new Actions(driver);
		for (int i = 0; i < inputValues.length; i ++){
			String temp = enteredTextPartialPath + (i+1) + "]";
			By completeTextPath = By.xpath(temp);
			WebElement ele = driver.findElement(completeTextPath);
			act.doubleClick(ele).perform();
			for (int j = 0; j < inputValues[i].length(); j ++){
				act.sendKeys(ele,Keys.BACK_SPACE).perform();
			}
			act.sendKeys(ele,inputValues[i]).perform();
			act.sendKeys(ele,Keys.ENTER).perform();
		}
	}

	//Function to mark completed values in the to do list
	public void checkboxTickToDoList(String completedValues,String allValues){
		String[] completedValuesArr = completedValues.split(",");
		String[] allValuesArr = allValues.split(",");
		int toVerify = allValuesArr.length - completedValuesArr.length;
		if (toVerify == 0){
			driver.findElement(markAllAsCompletedButton).click(); //if the total number of COMPLETED items sand the total number 
		}														  //of items is same, then Select all checkbox is clicked
		else{
			for (int i = 0; i < completedValuesArr.length; i ++){
				for (int j = 0; j < allValuesArr.length; j ++){
					if (completedValuesArr[i].equals(allValuesArr[j])){
						String temp = tempPartialXpath + completedValuesArr[i] + "')]/../input";
						By completeTextPath = By.xpath(temp);
						WebElement ele = driver.findElement(completeTextPath);
						ele.click();
					}
				}
			}
		}
	}

	//Function to clear existing values in the to do list
	public void clearTextToDoList(String text){
		String[] inputValues = text.split(",");
		Actions act = new Actions(driver);
		for (int i = 0; i < inputValues.length; i ++){
			String temp = enteredTextPartialPath + (i+1) + "]";
			By completeTextPath = By.xpath(temp);
			WebElement ele = driver.findElement(completeTextPath);
			act.doubleClick(ele).perform();
			for (int j = 0; j < inputValues[i].length(); j ++){
				act.sendKeys(ele,Keys.BACK_SPACE).perform();
			}
			act.sendKeys(ele,Keys.ENTER).perform();
		}
	}

	//Function to verify the successful filter values of items in the to do list
	public void verifyFilterValuesToDoList(String filterType,String allValues,String completedValues) throws Exception{
		String[] allValuesArr = allValues.split(",");
		String[] completedValuesArr = completedValues.split(",");

		switch(filterType){
		case "Completed": 	
			for (int i = 0; i < completedValuesArr.length; i ++){
				String temp = tempPartialXpath + completedValuesArr[i] + "')]/../..";
				By completeTextPath = By.xpath(temp);
				WebElement ele = driver.findElement(completeTextPath);
				if((ele.getAttribute("class")).equals("todo completed")){
					System.out.println(completedValuesArr[i]+" marked as completed successfully");
				}
				else{
					throw new Exception("The completed Task "+ completedValuesArr[i] +" is still not checked on the To Do List");
				}
			}
			break;
		case "Active": 	
			for (int i = 0; i < allValuesArr.length; i ++){
				for (int j = 0; j < completedValuesArr.length; j ++){
					if (!(allValuesArr[i].equals(completedValuesArr[j]))){
						String temp = tempPartialXpath + allValuesArr[i] + "')]/../..";
						By completeTextPath = By.xpath(temp);
						WebElement ele = driver.findElement(completeTextPath);
						if((ele.getAttribute("class")).equals("todo")){
							System.out.println(allValuesArr[i]+" is visible in Active tasks as expected");
						}
						else{
							throw new Exception("The active Task "+ allValuesArr[i] +" is not present in the active tasks on the To Do List");
						}
					}
				}
			}
			break;
		default:throw new InvalidArgumentException("Invalid value type provided. Accepted types are - Completed, Active");
		}
	}

	//Function to verify the items left counter of the to do list
	public void verifyItemsLeftToDoList(String completedValues,String allValues) throws Exception{
		String[] completedValuesArr = completedValues.split(",");
		String[] allValuesArr = allValues.split(",");
		int toVerify = allValuesArr.length - completedValuesArr.length;
		String temp = tempPartialXpath + toVerify + "')]";
		By completeTextPath = By.xpath(temp);
		if (driver.findElement(completeTextPath).isDisplayed()){
			System.out.println("Item counter is displaying the right count - " + toVerify);
		}
		else{
			throw new Exception("The item counter is not displaying the right count - " + toVerify);
		}
	}

	//Function to verify the successful deletion of items into the to do list
	public void verifyDeleteTextToDoList(String text) throws Exception{
		String[] inputValues = text.split(",");
		for (int i = 0; i < inputValues.length; i ++){
			if(driver.getPageSource().contains(inputValues[i])){
				throw new Exception("The deleted Task "+ inputValues[i] +" is still visible on the To Do List");
			}
			else{
				System.out.println(inputValues[i]+" deleted successfully from the To-Do List");
			}
		}
	}

	//Function to verify the successful addition/editing of items into the to do list
	public void verifyTextToDoList(String text){
		String[] inputValues = text.split(",");
		for (int i = 0; i < inputValues.length; i ++){

			String temp = enteredTextPartialPath.toString() + (i+1) + "]/div/label";
			By completeTextPath = By.xpath(temp);
			if(driver.findElement(completeTextPath).getText().equals(inputValues[i])){
				System.out.println(inputValues[i]+" added successfully to the To-Do List");
			}
			else{
				throw new NoSuchElementException("The Task "+ inputValues[i] +" is not visible on the To Do List");
			}
		}
	}

	//Function to close and quit the web browser
	public void closeBrowser(){
		driver.close();
		driver.quit();
	}


}
