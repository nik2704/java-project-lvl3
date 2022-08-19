package hexlet.code.schemas;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema {
    private List<Predicate> predicates;

    public BaseSchema() {
        predicates = new ArrayList<>();
    }

    public final void addPredicate(Predicate p) {
        predicates.add(p);
    }

    public final boolean isValid(Object value) {
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
        addPredicate(v -> v != null);
        return this;
    }
}
