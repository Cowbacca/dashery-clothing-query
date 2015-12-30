package uk.co.dashery.rabbitmq;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import uk.co.dashery.clothing.Clothing;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClothingJsonMessageConverter implements MessageConverter {

    public static final CharMatcher PUNCTUATION_MATCHER = CharMatcher.anyOf(",. \n\t\\\"'][#*:()");

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

    private Set<String> getTags(String description) {
        if (description != null) {
            String unescapedDescription = Parser.unescapeEntities(description, true);
            String parsedHtml = Jsoup.parse(unescapedDescription).text();
            String lowerCaseDescription = parsedHtml.toLowerCase();
            String[] tags = lowerCaseDescription.split(" ");
            return Arrays.stream(tags)
                    .map(tag -> PUNCTUATION_MATCHER.trimFrom(tag)).collect
                            (Collectors.toSet());
        } else {
            return Sets.newHashSet();
        }
    }
}
