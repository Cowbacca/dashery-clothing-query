package uk.co.dashery.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.query.Query;
import uk.co.dashery.data.Clothing;
import uk.co.dashery.repository.ClothingRepository;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;

public class ClothingServiceTest {

    public static final String SEARCH_STRING = "test:test";
    @InjectMocks
    private ClothingService clothingService;

    @Mock
    private ClothingRepository mockClothingRepository;
    @Mock
    private Query mockQuery;
    @Mock
    private QueryGenerator mockQueryGenerator;
    @Mock
    private TokenService tokenService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testSearch() throws Exception {
        List<Clothing> clothing = createClothing();
        when(mockQueryGenerator.generate(SEARCH_STRING)).thenReturn(mockQuery);
        when(mockClothingRepository.findByQuery(mockQuery)).thenReturn(clothing);

        assertThat(clothingService.search(SEARCH_STRING), is(clothing));

        verify(tokenService).create(clothing);
    }
}