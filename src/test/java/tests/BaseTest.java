package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.LoginConfig;
import config.WebDriverProvider;
import helpers.Attachments;
import org.aeonbits.owner.ConfigFactory;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    public static LoginConfig loginConfig = ConfigFactory.create(LoginConfig.class);

    @BeforeAll
    static void setUp() {
        WebDriverProvider.configure();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
    }

}
