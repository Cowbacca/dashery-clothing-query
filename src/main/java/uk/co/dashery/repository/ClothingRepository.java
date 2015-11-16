package uk.co.dashery.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import uk.co.dashery.data.Clothing;

import java.util.List;

public interface ClothingRepository extends MongoRepository<Clothing, String>, ClothingRepositoryCustom {
    List<Clothing> findByType(@Param("type") String type);

}
