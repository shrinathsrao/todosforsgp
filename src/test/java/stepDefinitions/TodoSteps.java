package stepDefinitions;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.todoListLandingPage;

public class TodoSteps {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	todoListLandingPage landingPageFunctions = new todoListLandingPage(driver);

	@Given("^Web (.*) is Open$")
	public void browser_open(String browser) {
		landingPageFunctions = new todoListLandingPage(driver);
		driver = landingPageFunctions.openBrowser(browser);
	} 

	@And("User is on To-do list page")
	public void navigate_to_landingPage() {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.navigateToUrl("http://todomvc.com/examples/vue/");
		landingPageFunctions.verifyLandingPage();
	}

	@When("^User enters (.*) in To-do list text box$")
	public void add_value(String values) {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.enterTextToDoList(values);
	}

	@When("^User marks the existing (.*) as completed from list of (.*)$")
	public void checkboxTick_value(String completedValues, String allValues) {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.checkboxTickToDoList(completedValues,allValues);
	}

	@When("^User clicks on (.*) button$")
	public void click_filterButton(String buttonType) throws InterruptedException {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.buttonClick(buttonType);
	}

	@When("^User edits the existing (.*) with (.*)$")
	public void edit_value(String oldValues,String values) {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.editTextToDoList(values);
	}

	@When("^User deletes an entry from existing (.*)$")
	public void delete_value(String values) {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.clearTextToDoList(values);
	}

	@Then("User clicks on Clear Completed button")
	public void clear_completed_click() throws Exception {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.buttonClick("Clear completed");
	}

	@Then("^User is able to see the entered (.*) in the To-Do list$")
	public void added_value_verify(String values) {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.verifyTextToDoList(values);
		landingPageFunctions.closeBrowser();
	}

	@Then("^User is not able to see the deleted entry (.*) in the To-Do list$")
	public void deleted_value_not_visible(String values) throws Exception {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.verifyDeleteTextToDoList(values);
		landingPageFunctions.closeBrowser();
	}

	@Then("^(.*) value out of all the (.*) is shown as completed$")
	public void checked_value_verify(String completedValues,String allValues) throws Exception {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.verifyFilterValuesToDoList("Completed",allValues,completedValues);
	}

	@Then("^User only sees the (.*) set of (.*) and (.*) in the to-do list$")
	public void filtered_list_verify(String filterType,String allValues,String completedValues) throws Exception {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.verifyFilterValuesToDoList(filterType,allValues,completedValues);
		landingPageFunctions.closeBrowser();
	}

	@And("^items left counter reflects the changes caused by (.*) items out of all (.*)$")
	public void items_left_verify(String completedValues, String allValues) throws Exception {
		landingPageFunctions = new todoListLandingPage(driver);
		landingPageFunctions.verifyItemsLeftToDoList(completedValues,allValues);
		landingPageFunctions.closeBrowser();
	}
}
