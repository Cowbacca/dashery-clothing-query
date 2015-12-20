package uk.co.dashery.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import uk.co.dashery.clothing.ClothingController;
import uk.co.dashery.clothing.Clothing;
import uk.co.dashery.clothing.ClothingService;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;

public class ClothingControllerTest {

    public static final String SEARCH_STRING = "test:test";
    @InjectMocks
    private ClothingController clothingController;

    @Mock
    private ClothingService mockClothingService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testClothing() throws Exception {
        List<Clothing> clothing = createClothing();
        when(mockClothingService.search(SEARCH_STRING)).thenReturn(clothing);

        assertThat(clothingController.clothing(SEARCH_STRING), is(clothing));
    }
}