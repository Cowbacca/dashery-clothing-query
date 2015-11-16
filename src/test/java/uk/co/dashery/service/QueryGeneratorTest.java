package uk.co.dashery.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.data.mongodb.core.query.Query;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class QueryGeneratorTest {

    public static final String TEST_KEY = "testKey";
    public static final String TEST_VALUE = "testValue";
    @InjectMocks
    private QueryGenerator queryGenerator;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testGenerate() throws Exception {
        String searchString = createSearchString(TEST_KEY, TEST_VALUE);

        Query query = queryGenerator.generate(searchString);

        assertThat(query.getQueryObject().get(TEST_KEY), is(TEST_VALUE));
    }

    private String createSearchString(String key, String value) {
        return key + QueryGenerator.IS + value;
    }
}