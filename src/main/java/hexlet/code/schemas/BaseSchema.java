package hexlet.code.schemas;


import lombok.Getter;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
    private static String captionRequired = "required";
    private static String captionCondition = "condition";

    @Getter
    private boolean required = false;

    private Map<String, List<Predicate<T>>> conditions;

    public BaseSchema() {
        conditions = new HashMap<>();
    }

    private void putCondition(Predicate<T> p, String capture) {
        if (!conditions.containsKey(capture)) {
            conditions.put(capture, new ArrayList<Predicate<T>>());
        }
        conditions.get(capture).add(p);
    }
    public final void addPredicate(Predicate<T> p, String capture) {
        putCondition(p, capture);
    }
    public final void addPredicate(Predicate<T> p) {
        putCondition(p, captionCondition);
    }

    public abstract boolean checkInstanceOfRequiredClass(Object value);

    public final boolean isValid(Object value) {
        if (isRequired()) {
            if (!checkInstanceOfRequiredClass(value)) {
                return false;
            }
            if (!areConditionsMet(value, captionRequired)) {
                return false;
            }
        }
        if (checkInstanceOfRequiredClass(value)) {
            return areConditionsMet(value, captionCondition);
        }

        return true;
    }

    private boolean areConditionsMet(Object value, String caption) {
        if (conditions.containsKey(caption)) {
            for (Predicate p : conditions.get(caption)) {
                if (!p.test(value)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * The base method for adding predicates for future checking of required conditions.
     * @return      the object itself
     */
    public BaseSchema required() {
        this.required = true;
        addPredicate(v -> v != null, captionRequired);
        return this;
    }
}
