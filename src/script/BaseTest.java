package script;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import com.thoughtworks.selenium.*;

//import org.junit.AfterClass;

import java.util.*;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.ITestContext;
import org.testng.annotations.*;
//import org.testng.annotations.*;
import java.io.File;

public class BaseTest extends SeleneseTestCase {
	SeleniumServer server;
	HttpCommandProcessor proc;

	@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
	  String seleniumHost = context.getCurrentXmlTest().getParameter("selenium.host");
	  String seleniumPort = context.getCurrentXmlTest().getParameter("selenium.port");
	  String seleniumBrowser = context.getCurrentXmlTest().getParameter("selenium.browser");
	  String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");

	  RemoteControlConfiguration rcc = new RemoteControlConfiguration();
	  rcc.setSingleWindow(true);
	  rcc.setPort(Integer.parseInt(seleniumPort));

	  try {
	    server = new SeleniumServer(false, rcc);
	    server.boot();
	    server.start();
	  } catch (Exception e) {
	    throw new IllegalStateException("Can't start selenium server", e);
	  }

	  proc = new HttpCommandProcessor(seleniumHost, Integer.parseInt(seleniumPort),
	      seleniumBrowser, seleniumUrl);
	  selenium = new DefaultSelenium(proc);
	  selenium.start();
	}

	@AfterSuite(alwaysRun = true)
	public void setupAfterSuite() {
	  selenium.stop();
	  server.stop();
	}

	@Test(description="Launches the WordPress site")

	public void launchSite() throws InterruptedException{
	  selenium.open("");
	  selenium.waitForPageToLoad("10000");
	  selenium.windowMaximize();
      selenium.windowFocus();
      //selenium.getEval("this.page().findElement('id=flashClient');");
      String[] windowNames = selenium.getAllWindowNames();
      for (String t: windowNames){
    	  System.out.println(t);
      }
      Thread.sleep(1500);
	  assertEquals(selenium.getTitle(), "Wordpress Demo | Just another WordPress site");
	}

	@Test(description="Navigates to the admin page", dependsOnMethods = { "launchSite"})
	  public void openAdminPage() {
	  selenium.open("wp-admin");
	  selenium.waitForPageToLoad("30000");
	  assertEquals(selenium.getTitle(), "2070 Glenfield Squadron › Log In");
	}

	@Test(description="Enters valid login data", dependsOnMethods = { "openAdminPage"})
	  public void loginAsAdmin() {
	  selenium.type("user_login", "admin");
	  selenium.type("user_pass", "demo123");
	  selenium.click("wp-submit");
	  selenium.waitForPageToLoad("30000");
	  assertTrue(selenium.isTextPresent("Howdy, admin"));
	}

	@Test(description="Navigates to the New Post screen", dependsOnMethods = { "loginAsAdmin"})
	public void navigateNewPost() {
	  selenium.click("//a[contains(text(),'Posts')]/following::a[contains(text(),'Add New')][1]");
	  selenium.waitForPageToLoad("30000");
	  assertTrue(selenium.isTextPresent("Add New Post"));
	}

	@Test(description="Writes the new post", dependsOnMethods = { "navigateNewPost"})
	public void writeBlogPost() {
	  selenium.type("title", "New Blog Post");
	  selenium.click("edButtonHTML");
	  selenium.type("content", "This is a new post");
	  //TODO:Assert
	}

	@Test(description="Publishes the post", dependsOnMethods = { "writeBlogPost"})
	public void publishBlogPost() {
	  selenium.click("submitdiv");
	  selenium.click("publish");
	  selenium.waitForPageToLoad("30000");
	  assertTrue(selenium.isTextPresent("Post published."));
	}

	@Test(description="Verifies the post", dependsOnMethods = { "publishBlogPost"})
	public void verifyBlogPost() {
	  selenium.click("//a[contains(text(),'Posts') and contains(@class,'wp-first-item')]");
	  selenium.waitForPageToLoad("30000");
	  assertTrue(selenium.isElementPresent("//a[text()='New Blog Post']"));
	}

	@Test(description="Logs out", dependsOnMethods = { "verifyBlogPost"})
	public void logout() {
	  selenium.click("//a[text()='Log Out']");
	  //TODO:Assert
	}
}
