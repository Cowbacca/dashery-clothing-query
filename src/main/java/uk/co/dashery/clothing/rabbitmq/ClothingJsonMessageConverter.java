package uk.co.dashery.clothing.rabbitmq;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import uk.co.dashery.clothing.Clothing;

public class ClothingJsonMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws
            MessageConversionException {
        return null;
    }

    @Override
    public Clothing fromMessage(Message message) throws MessageConversionException {
        Gson gson = new Gson();
        String json = new String(message.getBody());
        LinkedTreeMap[] jsonMaps = gson.fromJson(json, LinkedTreeMap[].class);
        return new Clothing((String) jsonMaps[0].get("id"));
    }
}
