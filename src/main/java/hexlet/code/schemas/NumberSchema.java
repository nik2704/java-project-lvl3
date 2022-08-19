package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    public NumberSchema positive() {
        addPredicate(v -> v != null ? ((Integer) v).intValue() > 0 : true);
        return this;
    }

    public NumberSchema range(int start, int end) {
        addPredicate(v -> (((Integer) v).intValue() >= start) && (((Integer) v).intValue() <= end));
        return this;
    }

    @Override
    public BaseSchema required() {
        super.required();
        addPredicate(v -> !Objects.isNull(v));
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value instanceof Integer) {
            return super.isValid(value);
        }

        if (isRequired()) {
            return false;
        }

        return true;
    }

}
