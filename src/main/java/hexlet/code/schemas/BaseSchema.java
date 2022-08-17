package hexlet.code.schemas;


import java.util.ArrayList;
import java.util.function.Predicate;


public abstract class BaseSchema {
    private ArrayList<Predicate> predicates;

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

    public abstract BaseSchema required();
}
