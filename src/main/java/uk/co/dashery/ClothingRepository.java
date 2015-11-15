package uk.co.dashery;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface ClothingRepository extends MongoRepository<Clothing, String> {
    List<Clothing> findByType(@Param("type") String type);
}
