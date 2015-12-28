package uk.co.dashery.productfeed;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.dashery.DasheryClothingQueryIntegrationTest;
import uk.co.dashery.clothing.Clothing;
import uk.co.dashery.clothing.ClothingController;

import javax.inject.Inject;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.dashery.ClothingTestUtils.generateCsvFile;

@RunWith(SpringJUnit4ClassRunner.class)
@DasheryClothingQueryIntegrationTest
public class ProductFeedControllerIT {

    @Inject
    private MongoTemplate mongoTemplate;

    @Inject
    private ProductFeedController productFeedController;
    @Inject
    private ClothingController clothingController;

    @After
    public void tearDown() throws Exception {
        mongoTemplate.getDb().dropDatabase();
    }

    @Test
    public void testUpdatesClothingRatherThanDuplicates() throws IOException {
        productFeedController.ingestProducts(new ProductFeedForm(generateCsvFile("test.csv")));
        assertThat(firstClothingWithTagNamedSome().getPrice(), is(10000));

        productFeedController.ingestProducts(new ProductFeedForm(generateCsvFile("test-updated" +
                ".csv")));
        assertThat(firstClothingWithTagNamedSome().getPrice(), is(150));
    }

    private Clothing firstClothingWithTagNamedSome() {
        return clothingController.clothing("Some").get(0);
    }
}
