import core.DriverInitializer;
import core.configuration.ConfigKey;
import core.configuration.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static core.Utils.stringContains;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StaticPageLoadTest {

    private WebDriver driver;

    @ParameterizedTest
    @CsvFileSource(files = "data.csv")
    public void testPageTitles(String url, String expectedTitle) throws MalformedURLException, URISyntaxException {
        driver = DriverInitializer.initialize(Configuration.getProperty(ConfigKey.BROWSER));
        driver.get(url);

        assertTrue(stringContains(driver.getTitle(), expectedTitle));
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
