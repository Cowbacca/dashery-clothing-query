package uk.co.dashery.clothing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import uk.co.dashery.products.Products;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;
import static uk.co.dashery.ClothingTestUtils.generateCsvFile;

public class ClothingCsvParserTest {

    @Spy
    private DasheryClothingCsvParser dasheryClothingCsvParser = new DasheryClothingCsvParser();

    @InjectMocks
    private ClothingCsvParser clothingCsvParser;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testParse() throws Exception {
        List<Clothing> products = clothingCsvParser.parse(new Products(generateCsvFile("test.csv")));

        List<Clothing> expectedProducts = createClothing();
        assertThat(products, is(expectedProducts));
    }
}