package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addPredicate(v -> v.intValue() > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        addPredicate(v -> (v.intValue() >= start) && (v.intValue() <= end));
        return this;
    }

    @Override
    public BaseSchema required() {
        super.required();
        addPredicate(v -> !Objects.isNull(v));
        return this;
    }

    @Override
    public boolean checkInstanceOfRequiredClass(Object value) {
        return value instanceof Integer;
    }

}
