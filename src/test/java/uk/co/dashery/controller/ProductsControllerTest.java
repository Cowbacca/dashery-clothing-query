package uk.co.dashery.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import uk.co.dashery.data.Clothing;
import uk.co.dashery.data.Products;
import uk.co.dashery.service.ClothingService;
import uk.co.dashery.service.ProductCsvParser;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;

public class ProductsControllerTest {

    public static final String URL = "test";
    @InjectMocks
    private ProductsController productsController;

    @Mock
    private ProductCsvParser productCsvParser;
    @Mock
    private ClothingService clothingService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testIngestProductsFromURL() throws Exception {
        List<Clothing> clothing = createClothing();
        when(productCsvParser.parseFromUrl(URL)).thenReturn(clothing);

        productsController.ingestProductsFromUrl(new Products(URL));

        verify(clothingService).create(clothing);
    }
}