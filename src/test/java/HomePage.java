import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    //Поле ввода вакансий для поиска
    @FindBy(how = How.XPATH,using = ".//input[@id='a11y-search-input']")
    private SelenideElement fieldInput;

    //Кнопка закрытия формы регистрации
    @FindBy(how = How.XPATH,using = ".//div[@class='bloko-modal-close-button']")
    private SelenideElement closingTheRegistrationForm ;

    //Кнопка расширенного поиска
    @FindBy(how = How.XPATH,using = ".//a[@data-qa='advanced-search']")
    private SelenideElement advancedSearch ;

    //Вакансия из выпадающего списка
    @FindBy(how = How.XPATH,using = "//div[text()='Qa engineer']")
    private SelenideElement vacancyFromTheDropDownList;

//---------------------------------------------------------------------------

    @Step("Закрывает всплываеющее окно регистрации если оно есть")
    public void closeSpam(){
        if (closingTheRegistrationForm.isDisplayed()){
            closingTheRegistrationForm.click();
        }
    }

    @Step("Вставляет данные в поле поиска и возвращает страницу с вакансиями")
    public VacanciesPage clikField(String text){
        fieldInput.click();
        fieldInput.setValue(text).pressEnter();
        closeSpam();

        return page(VacanciesPage.class);
    }

    @Step("Вставляет данные в поле поиска и нажимает из выпавшего списка на 1 вакансию")
    public VacanciesPage clikFieldAndSelectFromTheDropdownList(String text){
        fieldInput.click();
        fieldInput.setValue(text);

        vacancyFromTheDropDownList.click();

        closeSpam();
        return page(VacanciesPage.class);
    }

    @Step("Нажатие на кнопку расширенных настроек")
    public SearchForVacanciesPage clickAdvancedSearch(){
        advancedSearch.click();
        return page(SearchForVacanciesPage.class);
    }
}
