package uk.co.dashery.productfeed;

import com.univocity.parsers.annotations.Parsed;

public class Product {
    @Parsed
    private String id;
    @Parsed
    private String merchant;
    @Parsed
    private String name;
    @Parsed
    private String description;
    @Parsed
    private int price;
    @Parsed
    private String link;
    @Parsed
    private String imageLink;

    public Product() {
    }

    public Product(String id, String merchant, String name, String description, int price, String
            link, String imageLink) {
        this.id = id;
        this.merchant = merchant;
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;
        this.imageLink = imageLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", merchant='" + merchant + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", link='" + link + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (price != product.price) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (merchant != null ? !merchant.equals(product.merchant) : product.merchant != null)
            return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description
                != null)
            return false;
        if (link != null ? !link.equals(product.link) : product.link != null) return false;
        return imageLink != null ? imageLink.equals(product.imageLink) : product.imageLink == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (merchant != null ? merchant.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (imageLink != null ? imageLink.hashCode() : 0);
        return result;
    }
}
