package uk.co.dashery.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import uk.co.dashery.data.Clothing;
import uk.co.dashery.data.Products;
import uk.co.dashery.service.ClothingCsvParser;
import uk.co.dashery.service.ClothingService;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;

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

        productsController.ingestProductsFromUrl(new Products(testCsvUrl()));

        verify(clothingService).create(clothing);
    }

    private String testCsvUrl() {
        return getClass().getClassLoader().getResource("test.csv").toString();
    }
}