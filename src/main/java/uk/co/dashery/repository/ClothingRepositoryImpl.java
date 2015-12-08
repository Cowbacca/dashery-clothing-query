package uk.co.dashery.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import uk.co.dashery.data.Clothing;

import java.util.List;

public class ClothingRepositoryImpl implements ClothingRepositoryCustom {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<Clothing> findByAllTagsIn(String... tags) {
        Criteria whereAllOfTheGivenTags = new Criteria("tags").all(tags);
        Query query = new Query(whereAllOfTheGivenTags);
        return mongoOperations.find(query, Clothing.class);
    }
}
