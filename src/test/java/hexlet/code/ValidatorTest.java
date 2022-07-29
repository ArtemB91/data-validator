package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    void testMap1() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap())); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true
    }

    @Test
    void testMap2() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertFalse(schema.isValid(human2)); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4)); // false
    }
}
