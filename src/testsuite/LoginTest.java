package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Write down the following test into ‘LoginTest’ class
 * 1. userShouldNavigateToLoginPageSuccessfully
 *      click on the ‘Sign In’ link
 *      Verify the text ‘Welcome Back!’
 * 2. verifyTheErrorMessage
 *      click on the ‘Sign In’ link
 *      Enter invalid username
 *      Enter invalid password
 *      Click on Login button
 *      Verify the error message ‘Invalid email  or password.’
 */
public class LoginTest extends BaseTest {

    static String baseUrl = "https://courses.ultimateqa.com/"; // base URL

    @Before
    public void setUp () {
        openBrowser(baseUrl); // calling method from BaseTest class to open and set-up the browser
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        driver.findElement(By.partialLinkText("Sign In")).click(); // find the element and click to sign-in
        // find the message in the redirected page to verify the successful redirection
        String actualText = driver.findElement(By.xpath("//h2[@class = 'page__heading']")).getText();
        String expectedText = "Welcome Back!"; // expected message in teh redirected page
        Assert.assertEquals(expectedText,actualText); // verify expected and actual text
    }

    @Test
    public void verifyTheErrorMessage() {
        userShouldNavigateToLoginPageSuccessfully(); // redirect to the sign-in page
        driver.findElement(By.name("user[email]")).sendKeys("ABCD"); // find the element for email field and enter in-valid email
        driver.findElement(By.name("user[password]")).sendKeys("1234"); // find the element for password field and enter in-valid password
        driver.findElement(By.xpath("//button[@type = 'submit']")).click(); // find the element to submit and click
        // find the element for the error message and store the error message
        String actualError = driver.findElement(By.xpath("//li[@class = 'form-error__list-item']")).getText();
        String expectedError = "Invalid email or password."; // expected error message
        Assert.assertEquals(expectedError, actualError); // verify expected and actual text
    }

    @After
    public void tearTown () {
        closeBrowser(); // calling method from BaseTest class to close the browser
    }

}
