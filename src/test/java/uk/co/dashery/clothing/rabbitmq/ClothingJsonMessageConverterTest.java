package uk.co.dashery.clothing.rabbitmq;

import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import uk.co.dashery.clothing.Clothing;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClothingJsonMessageConverterTest {

    @Test
    public void testFromMessage() throws Exception {
        ClothingJsonMessageConverter clothingJsonMessageConverter = new
                ClothingJsonMessageConverter();

        String productsJson = "[{\"id\":\"id123\",\"merchant\":\"A Test Brand\",\"name\":\"Test " +
                "Item\",\"description\":\"Some description or other.\",\"price\":10000," +
                "\"link\":\"a_link.html\",\"imageLink\":\"image.jpg\"}]";
        Message message = new Message(productsJson.getBytes(), new MessageProperties());

        Clothing convertedClothing = clothingJsonMessageConverter.fromMessage(message);

        assertThat(convertedClothing, is(new Clothing("id123")));
    }
}