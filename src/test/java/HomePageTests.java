import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePageTests {

    @Test
    @DisplayName("Позитивный тест поисковой строки вакансий")
    public void positiveTestOfTheJobSearchBar() {
        HomePage homePage = open("https://nn.hh.ru/",
                HomePage.class);

       int actual = homePage.
               clikField(Const.TEXT_POSITIV_1).
               get_percentage_of_current_vacancies();

        assertTrue("Слишком маленький процент актуальных карточек в выдаче - "
                        + actual + " %." + " Должен быть не меньше 75 %",
                actual >= 50);
        }

    @Test
    @DisplayName("Негативный тест поисковой строки вакансий")
    public void negativTestOfTheJobSearchBar(){
    HomePage homePage = open("https://nn.hh.ru/",
            HomePage.class);

    String expected = Const.VACANCY_SEARCH_ERROR_TEXT;

    String actual = homePage.
            clikField(Const.TEXT_NEGATIV_1).
            get_text_vacancy_field_not_found();

    assertEquals(expected, actual);

}

    @Test
    @DisplayName("Тест отображения страницы расширенного поиска")
    public void advancedSearchClickTest(){

    HomePage homePage = open("https://nn.hh.ru/",
            HomePage.class);

    String actual = homePage.clickAdvancedSearch()
            .getTextSearchForVacancies();

    String expected = "Поиск вакансий";

    assertEquals(expected, actual);
}

    @Test
    @DisplayName("Поиск по неполному запросу")
    @Description("Ввод данных в строку поиска и выбор из выпадающего списка нужного результата")
    public void testSearchByPartialQuery(){
        HomePage homePage = open("https://nn.hh.ru/",
                HomePage.class);

        String expected = Const.VACANCY_SEARCH_SUCCESSFUL_RESULT;

        String actual = homePage.
                clikFieldAndSelectFromTheDropdownList(Const.TEXT_2).
                get_text_from_dropdown_list();
        
        assertEquals(expected, actual);

    }

    }
