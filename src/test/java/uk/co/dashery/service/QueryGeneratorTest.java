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
    public static final String ANOTHER_KEY = "anotherKey";
    public static final String ANOTHER_VALUE = "anotherValue";
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

        assertThat(criteriaValueFromQuery(query, TEST_KEY), is(TEST_VALUE));
    }

    @Test
    public void testGenerateMultipleTerms() {
        String searchString = createMultiTokenSearchString();

        Query query = queryGenerator.generate(searchString);

        assertThat(criteriaValueFromQuery(query, TEST_KEY), is(TEST_VALUE));
        assertThat(criteriaValueFromQuery(query, ANOTHER_KEY), is(ANOTHER_VALUE));
    }

    private String createSearchString(String key, String value) {
        return key + QueryGenerator.IS + value;
    }

    private String createMultiTokenSearchString() {
        return createSearchString(TEST_KEY, TEST_VALUE) + QueryGenerator.TOKEN_SPLITTER + createSearchString(ANOTHER_KEY, ANOTHER_VALUE);
    }

    private Object criteriaValueFromQuery(Query query, String key) {
        return query.getQueryObject().get(key);
    }
}