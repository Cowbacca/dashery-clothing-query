package uk.co.dashery.products;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.dashery.DasheryClothingQueryIntegrationTest;
import uk.co.dashery.clothing.ClothingRepository;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.dashery.ClothingTestUtils.generateCsvFile;

@RunWith(SpringJUnit4ClassRunner.class)
@DasheryClothingQueryIntegrationTest
public class ProductsControllerIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProductsController productsController;
    @Autowired
    private ClothingRepository clothingRepository;


    @After
    public void tearDown() throws Exception {
        mongoTemplate.getDb().dropDatabase();
    }

    @Test
    public void testUpdatesClothingRatherThanDuplicates() throws IOException {
        productsController.ingestProducts(new Products(generateCsvFile("test.csv")));
        productsController.ingestProducts(new Products(generateCsvFile("test.csv")));

        assertThat(clothingRepository.findByAllTagsIn("A Tag").size(), is(1));
    }
}
