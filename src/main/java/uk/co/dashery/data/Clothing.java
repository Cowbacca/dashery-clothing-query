package uk.co.dashery.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.univocity.parsers.annotations.Parsed;
import org.springframework.data.annotation.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clothing {

    @Id
    @JsonIgnore
    private String id;
    @Parsed
    @JsonIgnore
    public String brand;
    @Parsed
    @JsonIgnore
    public String name;
    @JsonIgnore
    @Parsed
    public int price;
    @Parsed
    @JsonIgnore
    public String link;
    @Parsed
    @JsonIgnore
    public String imageLink;
    @Parsed
    public String type;
    @Parsed
    public String colour;
    @Parsed
    public String material;
    @Parsed
    public String origin;


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
        if (type != null ? !type.equals(clothing.type) : clothing.type != null) return false;
        if (colour != null ? !colour.equals(clothing.colour) : clothing.colour != null) return false;
        if (material != null ? !material.equals(clothing.material) : clothing.material != null) return false;
        return !(origin != null ? !origin.equals(clothing.origin) : clothing.origin != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (imageLink != null ? imageLink.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        return result;
    }
}
