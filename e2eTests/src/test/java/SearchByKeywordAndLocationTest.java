import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

//use case 3
public class SearchByKeywordAndLocationTest extends SearchBaseTest {
    @Test
    void testEnterKeywordSelectLocationClickSearchShouldReturnProductsAvailableInSelectedCity() {
        page.navigate(URL_TO_PAGE);

        page.fill("//*[@id=\"root\"]/div[2]/div[2]/form/input", "Kipsplaat Knauf");
        page.click("div.pl-2");
        page.waitForSelector("input[type='checkbox'][value='TARTU']");
        page.click("input[type='checkbox'][value='TARTU']");
        page.click("button[type='submit']");

        Locator loadingSpinIcon = page.locator("#root > div.w-full.h-max.flex.bg-gray-100 > div.w-full.md\\:w-3\\/4.h-max.md\\:p-10.p-4 > div.w-full.h-max > div > svg");
        assertThat(loadingSpinIcon).isVisible();

        page.waitForSelector("ul.flex.flex-col");
        Locator firstProduct = page.locator("#root > div.w-full.h-max.flex.bg-gray-100 > div.w-full.md\\:w-3\\/4.h-max.md\\:p-10.p-4 > div.w-full.h-max > ul > li:nth-child(1) > div");

        Locator firstProductImage = firstProduct.locator("div.flex.justify-start.items-center.h-full");
        assertThat(firstProductImage).isVisible();

        Locator firstProductPrice = firstProduct.locator("text=€");
        assertThat(firstProductPrice).isVisible();

        Locator firstProductQuantity = firstProduct.locator("text=TK");
        assertThat(firstProductQuantity).isVisible();

        Locator firstProductAvailabilityButton = firstProduct.locator("text=Saadavus");
        assertThat(firstProductAvailabilityButton).isVisible();

        Locator firstProductRedirectToShopButton = firstProduct.locator("text=E-poodi");
        assertThat(firstProductRedirectToShopButton).isVisible();
    }

    //Use case 3 alternative scenario
    @Test
    void testEnterKeywordThatDoesNotReturnAnyProductsShouldShowEmptyPage() {
        page.navigate(URL_TO_PAGE);

        page.fill("//*[@id=\"root\"]/div[2]/div[2]/form/input", "ThisStringDefinitelyDoesNotReturnAnyBuildingMaterials");
        page.click("div.pl-2");
        page.waitForSelector("input[type='checkbox'][value='TARTU']");
        page.click("input[type='checkbox'][value='TARTU']");
        page.click("button[type='submit']");

        Locator loadingSpinIcon = page.locator("#root > div.w-full.h-max.flex.bg-gray-100 > div.w-full.md\\:w-3\\/4.h-max.md\\:p-10.p-4 > div.w-full.h-max > div > svg");
        assertThat(loadingSpinIcon).isVisible();

        page.waitForSelector("div.text-gray-700");

        Locator productCount = page.locator("div.text-gray-700 > p");

        assertThat(productCount).containsText("Leitud 0 toodet");
    }
}
