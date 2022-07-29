package hexlet.code.schemas;

public class StringSchema extends BaseSchema implements Schema {

    public final StringSchema required() {
        addCheck((v) -> v instanceof String && !((String) v).isEmpty());
        return this;
    }

    public final StringSchema minLength(final int length) {
        addCheck((v) -> v instanceof String && ((String) v).length() >= length);
        return this;
    }

    public final StringSchema contains(String content) {
        addCheck((v) -> v instanceof String && ((String) v).contains(content));
        return this;
    }

}
