package gerardosuarez.codetestgerardosuarez.utils;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class StringUtilsTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void changeNullByEmptyString() throws Exception {
        String test1 = StringUtils.changeNullByEmptyString(null);
        assertEquals(test1, StringUtils.EMPTY_STRING);
        String test2 = StringUtils.changeNullByEmptyString("test");
        assertEquals(test2, "test");
    }

    @Test
    public void isEmpty() throws Exception {
        String test1 = null;
        boolean isEmpty1 = StringUtils.isEmpty(test1);
        assertTrue(isEmpty1);
        String test2 = StringUtils.changeNullByEmptyString("test");
        boolean isEmpty2 = StringUtils.isEmpty(test2);
        assertFalse(isEmpty2);
    }
}