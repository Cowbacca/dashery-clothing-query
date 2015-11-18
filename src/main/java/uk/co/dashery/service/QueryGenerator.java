package uk.co.dashery.service;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class QueryGenerator {

    public static final String IS = ":";
    public static final String TOKEN_SPLITTER = ",";

    public Query generate(String search) {
        String[] tokens = search.split(TOKEN_SPLITTER);
        Criteria criteria = new Criteria();

        for (int i = 0; i < tokens.length; i++) {
            criteria = addTokenToCriteria(tokens[i], criteria, i);
        }

        return new Query(criteria);
    }

    private Criteria addTokenToCriteria(String token, Criteria criteria, int i) {
        String[] terms = parseToken(token);
        criteria = whereOrAnd(terms, i, criteria).is(terms[1]);
        return criteria;
    }

    private String[] parseToken(String token) {
        return token.split(IS);
    }

    private Criteria whereOrAnd(String[] terms, int i, Criteria criteria) {
        if (isFirstToken(i)) {
            criteria = criteria.where(terms[0]);
        } else {
            criteria = criteria.and(terms[0]);
        }
        return criteria;
    }

    private boolean isFirstToken(int i) {
        return i == 0;
    }
}
