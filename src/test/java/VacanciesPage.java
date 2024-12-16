import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.LinkedList;
import java.util.List;

public class VacanciesPage {

    //Колекция карточек с названием вакансий
    @FindBy(how = How.XPATH,using = ".//span[@data-qa='serp-item__title-text']")
   private ElementsCollection vacanciesList;

    //Текст для проверки что вакансия не найдена
    @FindBy(how = How.XPATH,using = "//h1[@data-qa='title']")
    private SelenideElement vacancyFieldNotFound;

    //Текст для проверки найденых вакансий с помощью поиска и выпадающего списка
    @FindBy(how = How.XPATH,using = " //h1[@data-qa='vacancies-catalog-header']")
    private SelenideElement vacancyFieldFromDropdownList;

    //----------------------------------------------------------------------------------------

    @Step("Получение списка карточек с вакансиями на запрос 'QA engineer'")
    public List<String> get_List_Vacancies(){
        List<String> arr = new LinkedList<>();

        for (int i =0; i < 50; i++) {
        arr.add(vacanciesList.get(i).getText());
        }
        return arr;
    }

    @Step("Получение процента выдвчи актуальных вакансий")
    public int get_percentage_of_current_vacancies(){
        List<String> arr1 = get_List_Vacancies();

        int count = 0;
        for (int i = 0; i < arr1.size(); i++) {

            if (arr1.get(i).contains("Тестировщик") || arr1.get(i).contains("QA")
                    || arr1.get(i).contains("тестированию") || arr1.get(i).contains("тестировщик")
                    ) {
                count++;
            }
        }
        int actualPercenage = (count*100)/50;
        return actualPercenage;
    }

    @Step("Получение ошибки, вакансия не найдена")
    public String get_text_vacancy_field_not_found(){
    return vacancyFieldNotFound.getText();
    }

    @Step("Получение строки с результатом для успешного выполнения поиска")
    public String get_text_from_dropdown_list(){
        return vacancyFieldFromDropdownList.getText();
    }
}
