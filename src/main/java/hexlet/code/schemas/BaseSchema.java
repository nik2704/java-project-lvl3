package hexlet.code.schemas;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
    @Getter
    private boolean required = false;

    private Predicate<T> requiredCondition;

    private List<Predicate<T>> conditions = new ArrayList<>();

    public final void addPredicate(Predicate<T> p) {
        conditions.add(p);
    }

    public abstract boolean checkInstanceOfRequiredClass(Object value);

    public final boolean isValid(Object value) {
        if (isRequired()) {
            if (value == null) {
                return false;
            }

            if (!checkInstanceOfRequiredClass(value)) {
                return false;
            }

            if (!requiredCondition.test((T) value)) {
                return false;
            }
        }
        if (checkInstanceOfRequiredClass(value)) {
            return areConditionsMet(value);
        }

        return true;
    }

    private boolean areConditionsMet(Object value) {
        for (Predicate p : conditions) {
            if (!p.test(value)) {
                return false;
            }
        }
        return true;
    }

    public abstract BaseSchema required(); // {

    public final BaseSchema setRequiredCondition(Predicate<T> p) {
        this.required = true;
        requiredCondition = p;

        return this;
    }
}
