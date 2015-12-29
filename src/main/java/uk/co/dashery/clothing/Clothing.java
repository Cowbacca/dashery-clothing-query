package uk.co.dashery.clothing;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clothing {

    @Id
    @JsonIgnore
    private String id;
    private String brand;
    private String name;
    private int price;
    private String link;
    private String imageLink;
    private Set<String> tags;

    public Clothing() {
        this(null);
    }

    public Clothing(String id) {
        this(id, null, null, 0, null, null, null);
    }

    public Clothing(String id, String brand, String name, int price, String link, String
            imageLink, Set<String> tags) {
        this.id = id;
        this.setBrand(brand);
        this.setName(name);
        this.setPrice(price);
        this.setLink(link);
        this.setImageLink(imageLink);
        this.setTags(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clothing clothing = (Clothing) o;

        if (getPrice() != clothing.getPrice()) return false;
        if (id != null ? !id.equals(clothing.id) : clothing.id != null) return false;
        if (getBrand() != null ? !getBrand().equals(clothing.getBrand()) : clothing.getBrand() !=
                null)
            return false;
        if (getName() != null ? !getName().equals(clothing.getName()) : clothing.getName() != null)
            return false;
        if (getLink() != null ? !getLink().equals(clothing.getLink()) : clothing.getLink() != null)
            return false;
        if (getImageLink() != null ? !getImageLink().equals(clothing.getImageLink()) : clothing
                .getImageLink() != null)
            return false;
        return !(getTags() != null ? !getTags().equals(clothing.getTags()) : clothing.getTags()
                != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getPrice();
        result = 31 * result + (getLink() != null ? getLink().hashCode() : 0);
        result = 31 * result + (getImageLink() != null ? getImageLink().hashCode() : 0);
        result = 31 * result + (getTags() != null ? getTags().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "id='" + id + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", link='" + getLink() + '\'' +
                ", imageLink='" + getImageLink() + '\'' +
                ", tags=" + getTags() +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void setId(String id) {
        this.id = id;
    }
}
