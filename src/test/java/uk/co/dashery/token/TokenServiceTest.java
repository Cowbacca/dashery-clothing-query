package uk.co.dashery.token;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import uk.co.dashery.TestDasheryClothingQueryApplication;
import uk.co.dashery.clothing.Clothing;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDasheryClothingQueryApplication.class)
@TestPropertySource("classpath:service-test.properties")
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestTemplate restTemplate;
    @Value("${dashery.autocomplete.url}")
    private String autocompleteURL;
    @Value("${dashery.autocomplete.creation.endpoint}")
    private String tokenCreationEndpoint;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testCreateFromProducts() throws Exception {
        List<Clothing> clothing = createClothing();

        tokenService.createFromClothing(clothing);

        verify(restTemplate).postForEntity(generateFullPostUrl(), clothing, Clothing.class);
    }

    private String generateFullPostUrl() {
        return autocompleteURL + tokenCreationEndpoint;
    }
}