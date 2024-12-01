import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Use case 2
public class SearchByCategoryAndKeywordTest extends SearchBaseTest {

    @ParameterizedTest
    @CsvSource({
            "Ehitusplaadid, Kipsplaat, Kipsplaat Knauf",
            "Plokid ja kivid, Fibo, Kergplokk Fibo",
            "Soojustus, Kivivill, kivivill windrock",
            "Puit, Liimpuit, liimpuitplaat kuusk",
            "Segud valmis/kuiv, Krohv, krohvisegu uninaks",
            "Fassaad, Puitvoodrilauad, voodrilaud väljas",
            "Kattematerjalid, Geotekstiil, geotekstiil",
            "Ehitusmetall, Armatuur, armatuurvarras",})
    void testSelectCategoryEnterKeywordClickSearchShouldReturnProducts(String category, String subCategory, String keyword) {
        Response response = page.navigate(URL_TO_PAGE);
        assertEquals(response.status(), 200);

        Locator categoryElement = page.locator(String.format("text=%s", category));
        assertThat(categoryElement).isVisible();
        categoryElement.click();

        Locator subCategoryElement = page.locator(String.format("text=%s", subCategory));
        assertThat(subCategoryElement).isVisible();
        subCategoryElement.click();

        Locator searchInput = page.locator(String.format("input[placeholder='Otsi toodet kategooriast %s']", subCategory));
        searchInput.fill(keyword);

        Locator searchButton = page.locator("button[type='submit']");
        assertThat(searchButton).isVisible();
        searchButton.click();

        Locator loadingSpinIcon = page.locator("svg.animate-spin");
        assertThat(loadingSpinIcon).isVisible();

        page.waitForSelector("ul.flex.flex-col");

        Locator firstProduct = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[3]/ul/li[1]/div");
        assertThat(firstProduct).isVisible();

        Locator firstProductImage = firstProduct.locator("div.flex.justify-start.items-center.h-full");
        assertThat(firstProductImage).isVisible();

        Locator firstProductPrice = firstProduct.locator("text=€");
        assertThat(firstProductPrice).isVisible();

        Locator firstProductAvailabilityButton = firstProduct.locator("text=Saadavus");
        assertThat(firstProductAvailabilityButton).isVisible();

        Locator firstProductRedirectToShopButton = firstProduct.locator("text=E-poodi");
        assertThat(firstProductRedirectToShopButton).isVisible();
    }

    @Test
    void testSelectCategoryEnterKeywordThatDoesNotReturnAnyProductsShouldShowEmptyPage() {
        Response response = page.navigate(URL_TO_PAGE);
        assertEquals(response.status(), 200);

        Locator categoryElement = page.locator("text=Ehitusplaadid");
        assertThat(categoryElement).isVisible();
        categoryElement.click();

        Locator subCategoryElement = page.locator("text=Kipsplaat");
        assertThat(subCategoryElement).isVisible();
        subCategoryElement.click();

        Locator searchInput = page.locator("input[placeholder='Otsi toodet kategooriast Kipsplaat']");
        assertThat(searchInput).isVisible();
        searchInput.fill("ThisStringDefinitelyDoesNotReturnAnyBuildingMaterials");

        Locator searchButton = page.locator("button[type='submit']");
        assertThat(searchButton).isVisible();
        searchButton.click();

        Locator loadingSpinIcon = page.locator("svg.animate-spin");
        assertThat(loadingSpinIcon).isVisible();

        page.waitForSelector("div.text-gray-700");

        Locator productCount = page.locator("div.text-gray-700 > p");
        assertThat(productCount).containsText("Leitud 0 toodet");
    }
}
