package uk.co.dashery.rabbitmq;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import uk.co.dashery.clothing.Clothing;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClothingJsonMessageConverterTest {

    private ClothingJsonMessageConverter clothingJsonMessageConverter;

    @Before
    public void setUp() {
        clothingJsonMessageConverter = new ClothingJsonMessageConverter();
    }

    @Test
    public void testConvertsFromJsonMessage() throws Exception {
        Message message = givenAMessageWithTwoJsonProducts();

        List<Clothing> convertedClothing = clothingJsonMessageConverter.fromMessage(message);

        ArrayList<Clothing> expectedClothing = expectedClothing();
        assertThat(convertedClothing, is(expectedClothing));
    }

    private Message givenAMessageWithTwoJsonProducts() {
        String productsJson = "[" +
                "{\"id\":\"id123\"," +
                "\"merchant\":\"A Test Brand\"," +
                "\"name\":\"Test Item\"," +
                "\"description\":\"<p>Some description or other.</p>\"," +
                "\"price\":10000," +
                "\"link\":\"a_link.html\"," +
                "\"imageLink\":\"image.jpg\"}," +
                "{\"id\":\"id456\"," +
                "\"merchant\":\"Another Day\"," +
                "\"name\":\"Another Dollar\"," +
                "\"description\":\"&lt;p&gt;A different description.&lt;/p&gt;\"" +
                ",\"price\":200," +
                "\"link\":\"different_link\"," +
                "\"imageLink\":\"image2.jpg\"}" +
                "]";
        return new Message(productsJson.getBytes(), new MessageProperties());
    }

    private ArrayList<Clothing> expectedClothing() {
        Clothing firstClothing = new Clothing("id123", "A Test Brand", "Test Item", 10000,
                "a_link.html", "image.jpg", Sets.newHashSet("description", "some", "or", "other"));
        Clothing secondClothing = new Clothing("id456", "Another Day", "Another Dollar", 200,
                "different_link", "image2.jpg", Sets.newHashSet("a", "different", "description"));
        return Lists.newArrayList(firstClothing, secondClothing);
    }
}