package uk.co.dashery.clothing;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.CharMatcher;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clothing {

    public static final CharMatcher PUNCTUATION_MATCHER = CharMatcher.anyOf(",. \n\t\\\"'][#*:()");

    @Id
    private String id;
    private String brand;
    private String name;
    private int price;
    private String link;
    private String imageLink;
    private Set<String> tags;

    public Clothing() {
        this(null, null, null, 0, null, null, null);
    }
    public Clothing(String id) {
        this(id, null, null, 0, null, null, null);
    }

    public Clothing(String id, String brand, String name, int price, String link, String imageLink,
                    String searchableText) {
        this.tags = new HashSet<>();

        setId(id);
        setBrand(brand);
        setName(name);
        setPrice(price);
        setLink(link);
        setImageLink(imageLink);
        addNewTags(searchableText);
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonProperty
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("merchant")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
        addNewTags(name);
    }

    @JsonIgnore
    public Set<String> getTags() {
        return tags;
    }

    @JsonProperty("description")
    public void setTags(String description) {
        addNewTags(description);
    }

    private void addNewTags(String text) {
        if (text != null) {
            String unescapedText = Parser.unescapeEntities(text, true);
            String parsedHtml = Jsoup.parse(unescapedText).text();
            String lowerCaseParsedHtml = parsedHtml.toLowerCase();
            String[] tags = lowerCaseParsedHtml.split(" ");
            Set<String> newTags = Arrays.stream(tags)
                    .map(tag -> PUNCTUATION_MATCHER.trimFrom(tag)).collect
                            (Collectors.toSet());
            this.tags.addAll(newTags);
        }
    }
}
