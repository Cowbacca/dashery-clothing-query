package uk.co.dashery.service;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class DelimitedStringToSetConversionTest {

    private DelimitedStringToSetConversion delimitedStringToSetConversion;

    @Before
    public void setUp() throws Exception {
        delimitedStringToSetConversion = new DelimitedStringToSetConversion(";");
    }

    @Test
    public void testExecute() throws Exception {
        assertThat(delimitedStringToSetConversion.execute("test;another"), is(Sets.newHashSet("test", "another")));
    }

    @Test
    public void testRevert() throws Exception {
        assertThat(delimitedStringToSetConversion.revert(Sets.newHashSet("test", "another")), is("test;another"));
    }
}