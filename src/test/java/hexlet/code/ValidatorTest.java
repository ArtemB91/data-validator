package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    // to fix checkstyle "magic number" check:
    private static final int STRING_MIN_LENGTH_TEST = 5;

    @Test
    void stringValid() {
        Validator v = new Validator();
        StringSchema schema = v.string().required();

        assertTrue(schema.isValid("word"));
        assertTrue(schema.isValid(" "));

        schema.minLength(STRING_MIN_LENGTH_TEST);
        assertTrue(schema.isValid("12345"));
        assertTrue(schema.isValid("anotherWord"));

        schema.contains("Word");
        assertTrue(schema.isValid("firstWord"));
        assertTrue(schema.isValid("secondWord"));

    }

    @Test
    void stringNotValid() {
        Validator v = new Validator();
        StringSchema schema = v.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));

        schema.minLength(STRING_MIN_LENGTH_TEST); // according to checkstyle "magic number" check :(
        assertFalse(schema.isValid("1234"));
        assertFalse(schema.isValid("hi"));

        schema.contains("word");
        assertFalse(schema.isValid("firstWord"));
        assertFalse(schema.isValid("secondWord"));
    }
}
