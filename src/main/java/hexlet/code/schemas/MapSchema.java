package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema implements Schema {

    private final Map<String, Object> data = new HashMap<>();

    public final MapSchema required() {
        addCheck((v) -> v instanceof Map);
        return this;
    }

    public final MapSchema sizeof(final Integer size) {
        addCheck((v) -> ((Map) v).size() == size);
        return this;
    }


}
