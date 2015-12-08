package uk.co.dashery.service;

import com.google.common.collect.Sets;
import com.univocity.parsers.conversions.Conversion;

import java.util.Set;

/**
 * Modified from https://github.com/uniVocity/univocity-parsers
 */
public class WordsToSetConversion implements Conversion<String, Set<String>> {

    public static final String DEFAULT_SEPARATOR = ",";

    private final String separator;

    public WordsToSetConversion(String... args) {
        String separator = DEFAULT_SEPARATOR;

        if (args.length >= 1) {
            separator = args[0];
        }

        this.separator = separator;
    }

    @Override
    public Set<String> execute(String input) {
        return Sets.newHashSet(input.split(separator));
    }

    @Override
    public String revert(Set<String> set) {
        return String.join(separator, set);
    }
}
