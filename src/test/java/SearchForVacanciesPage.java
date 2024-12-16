import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;

public class SearchForVacanciesPage {

    //Локатор текста "Поиск вакансий"
    @FindBy(how = How.XPATH,xpath = ".//h1[text()='Поиск вакансий']")
    private SelenideElement searchForVacancies;

    @Step("Получение текста для проверки 'Поиск вакансий'")
    public String getTextSearchForVacancies(){
    return searchForVacancies.shouldBe(visible, Duration.ofSeconds(20)).getText();
    }

}
