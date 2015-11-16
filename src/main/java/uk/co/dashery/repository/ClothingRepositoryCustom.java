package uk.co.dashery.repository;

import org.springframework.data.mongodb.core.query.Query;
import uk.co.dashery.data.Clothing;

import java.util.List;

public interface ClothingRepositoryCustom {
    List<Clothing> findByQuery(Query query);
}
