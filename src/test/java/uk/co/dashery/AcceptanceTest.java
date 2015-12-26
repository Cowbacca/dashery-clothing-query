package uk.co.dashery;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.dashery.clothing.ClothingController;
import uk.co.dashery.productfeed.ProductFeedController;

import javax.inject.Inject;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@DasheryClothingQueryIntegrationTest
public class AcceptanceTest {

    @Inject
    private MongoTemplate mongoTemplate;

    @Inject
    private ProductFeedController productFeedController;
    @Inject
    private ClothingController clothingController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productFeedController, clothingController)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.getDb().dropDatabase();
    }

    @Test
    public void testGivesRelevantAttributesInClothingJson() throws Exception {
        givenAUrlToACsvFileInAffiliateWindowFormatHasBeenProvided();

        JsonObject firstClothing = whenASearchIsPerformedUsingATokenFromThisCsv();

        assertThatAttributesHaveExpectedValues(firstClothing);

    }

    private void givenAUrlToACsvFileInAffiliateWindowFormatHasBeenProvided() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String urlString = classLoader.getResource("affiliatewindow.csv").toString();
        mockMvc.perform(post("/productFeed")
                .param("url", urlString)
                .param("usingUrl", "true")
                .param("affiliateWindowFormat", "true"))
                .andReturn();
    }

    private JsonObject whenASearchIsPerformedUsingATokenFromThisCsv() throws Exception {
        String clothingJson = mockMvc.perform(get("/clothing").param("search", "Another"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(clothingJson);
        return jsonElement.getAsJsonArray().get(0).getAsJsonObject();
    }

    private void assertThatAttributesHaveExpectedValues(JsonObject firstClothing) {
        HashMap<String, String> expectedAttributeValues = getExpectedAttributeValues();

        expectedAttributeValues
                .forEach((attribute, expectedValue) ->
                        assertThat(firstClothing.get(attribute).getAsString(), is(expectedValue)));
    }

    private HashMap<String, String> getExpectedAttributeValues() {
        HashMap<String, String> expectedFieldValues = new HashMap<>();
        expectedFieldValues.put("brand", "A Test Brand");
        expectedFieldValues.put("name", "Test Item");
        expectedFieldValues.put("link", "a_link.html");
        expectedFieldValues.put("imageLink", "image.jpg");
        expectedFieldValues.put("price", "10000");
        return expectedFieldValues;
    }
}
