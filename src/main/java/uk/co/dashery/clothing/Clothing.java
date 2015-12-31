package uk.co.dashery.clothing;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.CharMatcher;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clothing {

    public static final CharMatcher PUNCTUATION_MATCHER = CharMatcher.anyOf(",. \n\t\\\"'][#*:()");

    @Id
    @JsonIgnore
    private String id;
    private String brand;
    private String name;
    private int price;
    private String link;
    private String imageLink;
    private Set<String> tags;

    public Clothing(String id) {
        this(id, null, null, 0, null, null, null);
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

    @JsonProperty("description")
    public void setDescription(String description) {
        if (description != null) {
            String unescapedDescription = Parser.unescapeEntities(description, true);
            String parsedHtml = Jsoup.parse(unescapedDescription).text();
            String lowerCaseDescription = parsedHtml.toLowerCase();
            String[] tags = lowerCaseDescription.split(" ");
            this.tags =  Arrays.stream(tags)
                    .map(tag -> PUNCTUATION_MATCHER.trimFrom(tag)).collect
                            (Collectors.toSet());
        } else {
            this.tags = Sets.newHashSet();
        }
    }
}
