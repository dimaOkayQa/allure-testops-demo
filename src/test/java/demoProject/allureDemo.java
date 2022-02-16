package demoProject;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.*;

public class allureDemo {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NUMBER = "#68";
    private static final String BASE_URL = "https://github.com/";

    //public BaseSteps steps = new BaseSteps();

    @Test
    @DisplayName("Поиск Issue по номеру в репозитории (чистый Selenide)")
    @Owner("Dmitry Galas")
    @Tags({@Tag("web"), @Tag("critical")})
    public void issueSearchingSelenide(){

        open(BASE_URL);
        $(byName("q")).setValue(REPOSITORY).pressEnter();
        $(By.linkText(REPOSITORY)).click();
        $(".UnderlineNav-body").$(byText("Issues")).click();
        $(withText(ISSUE_NUMBER)).shouldBe(exist);

    }

    @Test
    @DisplayName("Поиск Issue по номеру в репозитории (лямбда-степы)")
    @Owner("Dmitry Galas")
    @Link(name = "BASE_URL", value = BASE_URL)
    @Feature("Issues")
    @Story("Поиск Issue по номеру в репозитории")
    public void issueSearchingSteps(){
        parameter("Repository", REPOSITORY);
        parameter("Issue number", ISSUE_NUMBER);
        step("Открытие Github", () -> {
            open(BASE_URL);
        });

        step("Поиск и открытие репозитория" + REPOSITORY, () -> {
            $(byName("q")).setValue(REPOSITORY).pressEnter();
            $(By.linkText(REPOSITORY)).click();
        });

        step("Переход в раздел Issues", () ->{
            $(".UnderlineNav-body").$(byText("Issues")).click();
        });

        step("Поиск Issue с номером "+ ISSUE_NUMBER, () ->{
            $(withText(ISSUE_NUMBER)).shouldBe(exist);
        });
    }


    @Test
    @AllureId("1")
    @DisplayName("Demo repository is available on GitHub")
    @Tags({@Tag("git"), @Tag("repo")})
    @Owner("admin")
    public void newTestMethod() {
        step("Open https://github.com/");

        step("Input dimaOkayQa in a search field");
        step("Search by all gitHub");
        step("Click Users for displaying search by users");
        step("Open dimaOkayQa profile");
        step("Open Repositories tab");
        step("Click allure-testops-demo repository");
    }

    @Test
    @AllureId("2")
    @DisplayName(".gitignore is not empy")
    @Tags({@Tag("git"), @Tag("gitignore")})
    @Owner("admin")
    public void newTestMethod2() {
        step("Open Code tab");

        step("Open .gitignore from root directory");
    }

    @Test
    @AllureId("15")
    @DisplayName("Test case from IDEA #15")
    @Tags({@Tag("idea-15"), @Tag("idea-demo")})
    @Owner("admin")
    public void newTestMethod3() {
        step("Launch IDEA");

        step("Add the case");

        step("Click to add the case in testOps");
    }

    public void newcase(){}
}
