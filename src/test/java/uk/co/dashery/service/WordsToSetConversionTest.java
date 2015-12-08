package uk.co.dashery.service;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class WordsToSetConversionTest {

    private WordsToSetConversion wordsToSetConversion;

    @Before
    public void setUp() throws Exception {
        wordsToSetConversion = new WordsToSetConversion(";");
    }

    @Test
    public void testExecute() throws Exception {
        assertThat(wordsToSetConversion.execute("test;another"), is(Sets.newHashSet("test", "another")));
    }

    @Test
    public void testRevert() throws Exception {
        assertThat(wordsToSetConversion.revert(Sets.newHashSet("test", "another")), is("test;another"));
    }
}