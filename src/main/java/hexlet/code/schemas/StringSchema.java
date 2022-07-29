package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema implements Schema<String> {

    private final List<Predicate<String>> checks = new ArrayList<>();

    public final StringSchema required() {
        checks.add((v) -> v != null && !v.isEmpty());
        return this;
    }

    public final StringSchema minLength(final int length) {
        checks.add((v) -> v.length() >= length);
        return this;
    }

    public final StringSchema contains(String content) {
        checks.add((v) -> v.contains(content));
        return this;
    }

    @Override
    public final boolean isValid(String value) {
        for (Predicate<String> check: checks) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
