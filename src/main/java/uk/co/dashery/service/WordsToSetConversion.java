package uk.co.dashery.service;

import com.univocity.parsers.conversions.Conversion;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
        if (input == null) {
            return Collections.emptySet();
        }

        Set<String> out = new TreeSet<String>();
        for (String token : input.split(separator)) {
            out.add(token);
        }

        return out;
    }

    @Override
    public String revert(Set<String> o) {
        return o.stream().collect(Collectors.joining(separator));
    }
}