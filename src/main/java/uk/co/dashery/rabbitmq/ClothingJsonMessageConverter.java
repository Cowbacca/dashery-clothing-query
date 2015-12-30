package uk.co.dashery.rabbitmq;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import uk.co.dashery.clothing.Clothing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ClothingJsonMessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws
            MessageConversionException {
        return null;
    }

    @Override
    public List<Clothing> fromMessage(Message message) throws MessageConversionException {
        Gson gson = new Gson();
        String json = new String(message.getBody());
        LinkedTreeMap[] jsonMaps = gson.fromJson(json, LinkedTreeMap[].class);
        return Arrays.stream(jsonMaps)
                .map(this::getClothing)
                .collect(Collectors.toList());
    }

    private Clothing getClothing(LinkedTreeMap jsonMap) {
        Clothing clothing = new Clothing();
        clothing.setId((String) jsonMap.get("id"));
        clothing.setBrand((String) jsonMap.get("merchant"));
        clothing.setName((String) jsonMap.get("name"));
        clothing.setPrice(((Double) jsonMap.get("price")).intValue());
        clothing.setLink((String) jsonMap.get("link"));
        clothing.setImageLink((String) jsonMap.get("imageLink"));
        clothing.setTags(getTags((String) jsonMap.get("description")));
        return clothing;
    }

    private HashSet<String> getTags(String description) {
        if (description != null) {
            String[] tags = description.split(" ");
            return Sets.newHashSet(tags);
        } else {
            return Sets.newHashSet();
        }
    }
}
