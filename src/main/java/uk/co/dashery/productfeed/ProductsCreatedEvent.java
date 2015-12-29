package uk.co.dashery.productfeed;

import java.util.List;


public class ProductsCreatedEvent {
    private final List<Product> products;

    public ProductsCreatedEvent(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsCreatedEvent that = (ProductsCreatedEvent) o;

        return products != null ? products.equals(that.products) : that.products == null;

    }

    @Override
    public int hashCode() {
        return products != null ? products.hashCode() : 0;
    }
}
