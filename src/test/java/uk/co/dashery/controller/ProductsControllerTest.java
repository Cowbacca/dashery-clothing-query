package uk.co.dashery.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ExtendedModelMap;
import uk.co.dashery.clothing.Clothing;
import uk.co.dashery.products.Products;
import uk.co.dashery.clothing.ClothingCsvParser;
import uk.co.dashery.clothing.ClothingService;
import uk.co.dashery.products.ProductsController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;
import static uk.co.dashery.ClothingTestUtils.getTestCsvAsStream;

public class ProductsControllerTest {

    public static final String URL = "test";
    @InjectMocks
    private ProductsController productsController;

    @Spy
    private ClothingCsvParser clothingCsvParser = new ClothingCsvParser();
    @Mock
    private ClothingService clothingService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testIngestProductsFromURL() throws Exception {
        List<Clothing> clothing = createClothing();

        productsController.ingestProducts(new Products(testCsvUrl()));

        verify(clothingService).create(clothing);
    }

    private String testCsvUrl() {
        return getClass().getClassLoader().getResource("test.csv").toString();
    }

    @Test
    public void testIngestProductsFromFile() throws IOException {
        List<Clothing> clothing = createClothing();

        productsController.ingestProducts(new Products(generateCsvFile()));

        verify(clothingService).create(clothing);
    }

    private MockMultipartFile generateCsvFile() throws IOException {
        InputStream inputFile = getTestCsvAsStream();
        return new MockMultipartFile("csvFile", "test.csv", "multipart/form-data", inputFile);
    }

    @Test
    public void testProductsForm() {
        ExtendedModelMap model = new ExtendedModelMap();
        productsController.productsForm(model);

        assertThat(model.containsValue(new Products()), is(true));
    }
}