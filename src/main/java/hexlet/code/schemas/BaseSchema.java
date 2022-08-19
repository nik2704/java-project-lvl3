package hexlet.code.schemas;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema {

    @Getter
    private boolean required = false;

    private List<Predicate> predicates;

    public BaseSchema() {
        predicates = new ArrayList<>();
    }

    public final void addPredicate(Predicate p) {
        predicates.add(p);
    }

    /**
     * The base method of checking of required conditions.
     * @param       value is being tested
     * @return      true or false
     */
    public boolean isValid(Object value) {
        for (Predicate p : predicates) {
            if (!p.test(value)) {
                return false;
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
        addPredicate(v -> v != null);
        return this;
    }
}
