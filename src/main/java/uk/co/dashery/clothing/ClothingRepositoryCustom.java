package uk.co.dashery.clothing;

import uk.co.dashery.clothing.Clothing;

import java.util.List;

public interface ClothingRepositoryCustom {
    List<Clothing> findByAllTagsIn(String... tags);
}
