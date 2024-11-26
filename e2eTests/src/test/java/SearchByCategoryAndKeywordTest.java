import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

//use case 2
public class SearchByCategoryAndKeywordTest extends SearchBaseTest{

    @ParameterizedTest
    @CsvSource({
            "Ehitusplaadid, Kipsplaat, Kipsplaat Knauf",
            "Plokid ja kivid, Fibo, Kergplokk Fibo",
            "Soojustus, Kivivill, kivivill windrock",
            "Puit, Liimpuit, liimpuitplaat kuusk",
            "Segud valmis/kuiv, Krohv, krohvisegu uninaks",
            "Fassaad, Puitvoodrilauad, voodrilaud väljas",
            "Katused, Kivi, katusekivi",
            "Kattematerjalid, Geotekstiil, geotekstiil",
            "Ehitusmetall, Armatuur, armatuurvarras",})
    void testSelectCategoryEnterKeywordClickSearchShouldReturnProducts(String category, String subCategory, String keyword) {
        page.navigate(URL_TO_PAGE);

        page.click(String.format("text=%s",category));
        page.click(String.format("text=%s",subCategory));
        page.fill("//*[@id=\"root\"]/div[2]/div[2]/form/input", keyword);
        page.click("button[type='submit']");

        Locator loadingSpinIcon = page.locator("#root > div.w-full.h-max.flex.bg-gray-100 > div.w-full.md\\:w-3\\/4.h-max.md\\:p-10.p-4 > div.w-full.h-max > div > svg");
        assertThat(loadingSpinIcon).isVisible();

        page.waitForSelector("ul.flex.flex-col");
        Locator firstProduct = page.locator("#root > div.w-full.h-max.flex.bg-gray-100 > div.w-full.md\\:w-3\\/4.h-max.md\\:p-10.p-4 > div.w-full.h-max > ul > li:nth-child(1) > div");

        Locator firstProductImage = firstProduct.locator("div.flex.justify-start.items-center.h-full");
        assertThat(firstProductImage).isVisible();

        Locator firstProductPrice = firstProduct.locator("text=€");
        assertThat(firstProductPrice).isVisible();

        Locator firstProductAvailabilityButton = firstProduct.locator("text=Saadavus");
        assertThat(firstProductAvailabilityButton).isVisible();

        Locator firstProductRedirectToShopButton = firstProduct.locator("text=E-poodi");
        assertThat(firstProductRedirectToShopButton).isVisible();
    }

    //Use case 2 alternative Scenario
    @Test
    void testSelectCategoryEnterKeywordThatDoesNotReturnAnyProductsShouldShowEmptyPage() {
        page.navigate(URL_TO_PAGE);

        page.click("text=Ehitusplaadid");
        page.click("text=Kipsplaat");
        page.fill("//*[@id=\"root\"]/div[2]/div[2]/form/input", "ThisStringDefinitelyDoesNotReturnAnyBuildingMaterials");
        page.click("button[type='submit']");

        Locator loadingSpinIcon = page.locator("#root > div.w-full.h-max.flex.bg-gray-100 > div.w-full.md\\:w-3\\/4.h-max.md\\:p-10.p-4 > div.w-full.h-max > div > svg");
        assertThat(loadingSpinIcon).isVisible();

        page.waitForSelector("div.text-gray-700");

        Locator productCount = page.locator("div.text-gray-700 > p");

        assertThat(productCount).containsText("Leitud 0 toodet");
    }
}
