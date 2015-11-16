package uk.co.dashery.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import uk.co.dashery.data.Clothing;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;


public class ClothingRepositoryCustomTest {

    @InjectMocks
    private ClothingRepositoryCustom clothingRepositoryCustom = new ClothingRepositoryImpl();

    @Mock
    private Query mockQuery;
    @Mock
    private MongoOperations mockMongoOperations;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testFindByQuery() throws Exception {
        List<Clothing> clothing = createClothing();
        when(mockMongoOperations.find(mockQuery, Clothing.class)).thenReturn(clothing);

        assertThat(clothingRepositoryCustom.findByQuery(mockQuery), is(clothing));
    }
}