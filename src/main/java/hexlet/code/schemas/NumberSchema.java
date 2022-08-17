package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    public NumberSchema positive() {
        super.addPredicate(v -> v != null ? Integer.parseInt(Objects.toString(v)) > 0 : true);
        return this;
    }

    public NumberSchema range(int start, int end) {
        super.addPredicate(v -> {
            return v != null ? (Integer.parseInt(Objects.toString(v)) >= start)
                    && (Integer.parseInt(Objects.toString(v)) <= end) : true;
        });
        return this;
    }

    @Override
    public BaseSchema required() {
        super.addPredicate(v -> !Objects.isNull(v));
        super.addPredicate(v -> v instanceof Integer);
        return this;
    }

}
