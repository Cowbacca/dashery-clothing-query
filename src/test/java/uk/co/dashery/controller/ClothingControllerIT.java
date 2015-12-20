package uk.co.dashery.controller;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.dashery.TestDasheryClothingQueryApplication;
import uk.co.dashery.clothing.ClothingController;
import uk.co.dashery.clothing.Clothing;
import uk.co.dashery.clothing.ClothingRepository;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.dashery.ClothingTestUtils.generateClothing;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDasheryClothingQueryApplication.class)
@TestPropertySource("classpath:service-test.properties")
public class ClothingControllerIT {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ClothingRepository clothingRepository;
    @Autowired
    private ClothingController clothingController;
    private Clothing bananaAppleClothing;
    private Clothing justBananaClothing;

    @Before
    public void setUp() throws Exception {
        bananaAppleClothing = generateClothing("Test Brand", "Test Name", 100, "Banana", "Apple");
        justBananaClothing = generateClothing("Test Brand", "Test Name", 100, "Banana");
        clothingRepository.insert(Lists.newArrayList(bananaAppleClothing, justBananaClothing));
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.getDb().dropDatabase();
    }

    @Test
    public void testSearchForClothing() throws Exception {
        List<Clothing> clothingWithBananaTag = Lists.newArrayList(bananaAppleClothing, justBananaClothing);

        assertThat(clothingController.clothing("Banana"), is(clothingWithBananaTag));
    }

    @Test
    public void testSearchForClothingWithTwoTags() {
        assertThat(clothingController.clothing("Banana,Apple"), is(Lists.newArrayList(bananaAppleClothing)));
    }
}
