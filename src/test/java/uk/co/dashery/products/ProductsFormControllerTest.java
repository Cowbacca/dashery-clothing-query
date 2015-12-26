package uk.co.dashery.products;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.ui.ExtendedModelMap;
import uk.co.dashery.clothing.Clothing;
import uk.co.dashery.clothing.ClothingService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;
import static uk.co.dashery.ClothingTestUtils.generateCsvFile;

public class ProductsFormControllerTest {

    @InjectMocks
    private ProductsController productsController;

    @Spy
    @InjectMocks
    private ProductsService productsService = new ProductsService();

    @Spy
    private DasheryClothingCsvParser dasheryClothingCsvParser = new DasheryClothingCsvParser();
    @Spy
    private AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser = new AffiliateWindowClothingCsvParser();


    @Mock
    private ClothingService clothingService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testIngestsProducts() throws Exception {
        List<Clothing> clothing = createClothing();

        productsController.ingestProducts(new ProductsForm(generateCsvFile("test.csv")));

        verify(clothingService).create(clothing);
    }

    @Test
    public void testProductsForm() {
        ExtendedModelMap model = new ExtendedModelMap();
        productsController.productsForm(model);

        assertThat(model.containsValue(new ProductsForm()), is(true));
    }
}