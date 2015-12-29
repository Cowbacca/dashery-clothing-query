package uk.co.dashery.productfeed;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@EqualsAndHashCode
public class ProductsCreatedEvent {
    @Getter
    private final List<Product> products;
}
