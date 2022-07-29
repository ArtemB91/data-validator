package hexlet.code;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
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

    @SuppressWarnings("checkstyle:magicNumber")
    @Test
    void numberAll() {
        Validator v = new Validator();

        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(10)); // true
        schema.isValid("5"); // false

        assertTrue(schema.positive().isValid(10)); // true
        schema.isValid(-10); // false

        schema.range(5, 10);

        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false
    }
}
