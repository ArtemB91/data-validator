package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema implements Schema {

    private final List<Predicate<Object>> checks = new ArrayList<>();

    protected final void addCheck(Predicate<Object> predicate) {
        checks.add(predicate);
    }

    @Override
    public final boolean isValid(Object value) {
        for (Predicate<Object> check: checks) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }

}
