package uk.co.dashery.clothing;

import java.util.List;

public interface ClothingRepositoryCustom {
    List<Clothing> findByAllTagsIn(String... tags);
}
