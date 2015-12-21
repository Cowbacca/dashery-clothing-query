package uk.co.dashery.clothing;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.univocity.parsers.annotations.Convert;
import com.univocity.parsers.annotations.Parsed;
import org.springframework.data.annotation.Id;
import uk.co.dashery.conversion.DelimitedStringToSetConversion;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clothing {

    @Id
    @JsonIgnore
    private String id;
    @Parsed
    public String brand;
    @Parsed
    public String name;
    @Parsed
    public int price;
    @Parsed
    public String link;
    @Parsed
    public String imageLink;
    @Parsed
    @Convert(conversionClass = DelimitedStringToSetConversion.class, args = {";"})
    public Set<String> tags;

    public Clothing(String brand, String name, int price, String link, String imageLink, Set<String> tags) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.link = link;
        this.imageLink = imageLink;
        this.tags = tags;
    }

    public Clothing() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clothing clothing = (Clothing) o;

        if (price != clothing.price) return false;
        if (id != null ? !id.equals(clothing.id) : clothing.id != null) return false;
        if (brand != null ? !brand.equals(clothing.brand) : clothing.brand != null) return false;
        if (name != null ? !name.equals(clothing.name) : clothing.name != null) return false;
        if (link != null ? !link.equals(clothing.link) : clothing.link != null) return false;
        if (imageLink != null ? !imageLink.equals(clothing.imageLink) : clothing.imageLink != null) return false;
        return !(tags != null ? !tags.equals(clothing.tags) : clothing.tags != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (imageLink != null ? imageLink.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", link='" + link + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", tags=" + tags +
                '}';
    }
}
