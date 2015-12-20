package uk.co.dashery.products;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import uk.co.dashery.clothing.Clothing;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.*;

public class ProductsServiceTest {

    @Spy
    private DasheryClothingCsvParser dasheryClothingCsvParser = new DasheryClothingCsvParser();
    @Spy
    private AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser = new AffiliateWindowClothingCsvParser();

    @InjectMocks
    private ProductsService productsService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testParsesCsvInDasheryFormat() throws Exception {
        List<Clothing> products = productsService.getClothingFrom(new Products(generateCsvFile("test.csv"), false));

        List<Clothing> expectedProducts = createClothing();
        assertThat(products, is(expectedProducts));
    }

    @Test
    public void testParsesCsvInAffiliateWindowFormat() throws IOException {
        List<Clothing> products = productsService.getClothingFrom(new Products(generateCsvFile("affiliatewindow.csv"), true));

        List<Clothing> expectedProducts = Lists.newArrayList(generateClothing("A Test Brand", null, 100),
                generateClothing("Another Day", null, 200));
        assertThat(products, is(expectedProducts));
    }
}