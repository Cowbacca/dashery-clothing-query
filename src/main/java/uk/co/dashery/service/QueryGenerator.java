package uk.co.dashery.service;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class QueryGenerator {

    public static final String IS = ":";

    public Query generate(String search) {
        String[] terms = search.split(IS);
        return new Query(Criteria.where(terms[0]).is(terms[1]));
    }
}
