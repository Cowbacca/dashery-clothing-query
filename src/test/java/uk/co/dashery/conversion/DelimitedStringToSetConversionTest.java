package uk.co.dashery.conversion;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class DelimitedStringToSetConversionTest {

    public static final String A_TOKEN = "test";
    public static final String ANOTHER_TOKEN = "another";
    public static final String DELIMITER = ";";
    private DelimitedStringToSetConversion delimitedStringToSetConversion;

    @Before
    public void setUp() throws Exception {
        delimitedStringToSetConversion = new DelimitedStringToSetConversion(DELIMITER);
    }

    @Test
    public void testExecute() throws Exception {
        String delimitedString = generateDelimitedString(DELIMITER);
        assertThat(delimitedStringToSetConversion.execute(delimitedString), is(Sets.newHashSet
                (A_TOKEN, ANOTHER_TOKEN)));
    }

    private String generateDelimitedString(String delimiter) {
        return A_TOKEN + delimiter + ANOTHER_TOKEN;
    }

    @Test
    public void testRevert() throws Exception {
        String delimitedString = generateDelimitedString(DELIMITER);
        assertThat(delimitedStringToSetConversion.revert(Sets.newHashSet(A_TOKEN, ANOTHER_TOKEN))
                , is(delimitedString));
    }

    @Test
    public void testExecuteAndRevertAreOpposites() {
        String delimitedString = generateDelimitedString(DELIMITER);
        Set<String> stringSet = delimitedStringToSetConversion.execute(delimitedString);
        assertThat(delimitedStringToSetConversion.revert(stringSet), is(delimitedString));
    }
}