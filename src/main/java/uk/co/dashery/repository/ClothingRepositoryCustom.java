package uk.co.dashery.repository;

import uk.co.dashery.data.Clothing;

import java.util.List;

public interface ClothingRepositoryCustom {
    List<Clothing> findByAllTagsIn(String... tags);
}
