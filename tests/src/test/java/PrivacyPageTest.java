import core.SeleniumTest;
import core.configuration.ConfigKey;
import core.configuration.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.PrivacyPage;

import static core.Utils.stringContains;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>Test cases for the Privacy Page</p>
 * <p>The cases verify the static overview and send submit form with modified settings</p>
 */
public class PrivacyPageTest extends SeleniumTest {

    private PrivacyPage privacyPage;

    @Override
    protected void testCaseInitializer(WebDriver driver) {
        final var homePage = new HomePage(driver);
        final var forumPage = homePage.clickOnForumNavigationItem();
        forumPage.doLogin(Configuration.getProperty(ConfigKey.USERNAME), Configuration.getProperty(ConfigKey.PASSWORD));
        final var accountDetailsPage = forumPage.openAccountDetailsPage();
        privacyPage = accountDetailsPage.openPrivacyPage();
    }

    @Test
    public void statusSettingShouldChangeAfterSubmit() {
        final var old = privacyPage.isStatusCheckboxSelected();
        privacyPage.selectStatusCheckbox();
        privacyPage.clickOnSubmitButton();
        privacyPage.getStatusCheckbox();
        privacyPage.waitWithTimeout(2);

        assertNotEquals(old, privacyPage.isStatusCheckboxSelected());
    }

    @Test
    public void selectedNavigationItemShouldBePrivacy() {
        assertTrue(stringContains(privacyPage.getSelectedNavigationItemText(), "Privacy"));
    }

    @Test
    public void pageTitleShouldContainsPrivacyWord() {
        assertTrue(stringContains(privacyPage.getTitle(), "Privacy")); // Static test for current page title
    }
}
