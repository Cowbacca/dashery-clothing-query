package uk.co.dashery.clothing;

import com.google.common.collect.Lists;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.dashery.DasheryClothingQueryIntegrationTest;
import uk.co.dashery.productfeed.Product;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.dashery.ClothingTestUtils.generateClothing;

@RunWith(SpringJUnit4ClassRunner.class)
@DasheryClothingQueryIntegrationTest
public class ClothingControllerIT {

    @Inject
    private MongoTemplate mongoTemplate;
    @Inject
    private ClothingRepository clothingRepository;
    @Inject
    private ClothingController clothingController;
    private Clothing bananaAppleClothing;
    private Clothing justBananaClothing;

    @Before
    public void setUp() throws Exception {
        bananaAppleClothing = generateClothing("id123", "Test Brand", "Test Name", 100, "Banana",
                "Apple");
        justBananaClothing = generateClothing("id456", "Test Brand", "Test Name", 100, "Banana");
        clothingRepository.insert(Lists.newArrayList(bananaAppleClothing, justBananaClothing));
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.getDb().dropDatabase();
    }

    @Test
    public void testSearchForClothing() throws Exception {
        List<Clothing> clothingWithBananaTag = Lists.newArrayList(bananaAppleClothing,
                justBananaClothing);

        assertThat(clothingController.clothing("Banana"), is(clothingWithBananaTag));
    }

    @Test
    public void testSearchForClothingWithTwoTags() {
        assertThat(clothingController.clothing("Banana,Apple"), is(Lists.newArrayList
                (bananaAppleClothing)));
    }

    @Test
    public void testUpdatesClothingRatherThanDuplicates() throws IOException {
        Product product = givenAProduct();
        product.setPrice(1);

        clothingController.createFrom(Lists.newArrayList(product));

        MatcherAssert.assertThat(firstClothingWithTagNamedSome().getPrice(), CoreMatchers.is(1));

        product.setPrice(2);

        clothingController.createFrom(Lists.newArrayList(product));

        MatcherAssert.assertThat(firstClothingWithTagNamedSome().getPrice(), CoreMatchers.is(2));
    }

    private Product givenAProduct() {
        Product product = new Product();
        product.setId("id");
        product.setDescription("Some description.");
        return product;
    }

    private Clothing firstClothingWithTagNamedSome() {
        return clothingController.clothing("Some").get(0);
    }
}
