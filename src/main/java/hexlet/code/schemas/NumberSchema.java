package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    public NumberSchema positive() {
        super.addPredicate(v -> v != null ? Integer.parseInt(Objects.toString(v)) > 0 : true);
        return this;
    }

    public NumberSchema range(int start, int end) {
        super.addPredicate(v -> v instanceof Integer);
        super.addPredicate(v -> (((Integer) v).intValue() >= start) && (((Integer) v).intValue() <= end));
        return this;
    }

    @Override
    public BaseSchema required() {
        super.required();
        super.addPredicate(v -> !Objects.isNull(v));
        super.addPredicate(v -> v instanceof Integer);
        return this;
    }

}
