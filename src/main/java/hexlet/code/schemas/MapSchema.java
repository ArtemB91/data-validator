package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema implements Schema {

    public final MapSchema required() {
        addCheck((v) -> v instanceof Map);
        return this;
    }

    public final MapSchema sizeof(final Integer size) {
        addCheck((v) -> ((Map) v).size() == size);
        return this;
    }

    public final MapSchema shape(final Map<String, BaseSchema> shape) {
        addCheck((v) -> mapCheck(v, shape));
        return this;
    }

    private boolean mapCheck(Object value, final Map<String, BaseSchema> shape) {
        Map<String, Object> valueMap = (Map<String, Object>) value;
        for (Map.Entry<String, Object> keyValue : valueMap.entrySet()) {
            String keyToCheck = keyValue.getKey();
            Object valueToCheck = keyValue.getValue();

            if (!shape.containsKey(keyToCheck)) {
                continue;
            }

            BaseSchema schema = shape.get(keyToCheck);
            if (!schema.isValid(valueToCheck)) {
                return false;
            }
        }
        return true;
    }


}
