package uk.co.dashery.clothing;

import com.google.common.collect.Sets;
import org.junit.Test;
import uk.co.dashery.clothing.tag.Tag;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClothingTest {

    @Test
    public void testAddsNameToTags() {
        Clothing clothing = new Clothing();
        clothing.setName("a test name");

        assertThat(clothing.getTags(), is(Sets.newHashSet(new Tag("a"), new Tag("test"), new Tag
                ("name"))));
    }
}
