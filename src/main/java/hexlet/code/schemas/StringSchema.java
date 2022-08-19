package hexlet.code.schemas;


import lombok.ToString;
import java.util.Objects;


@ToString
public final class StringSchema extends BaseSchema {
    @Override
    public StringSchema required() {
        super.required();
        super.addPredicate(v -> !Objects.isNull(v));
        super.addPredicate(v -> v instanceof String);
        super.addPredicate(v -> !Objects.toString(v).isEmpty());
        return this;
    }

    public StringSchema contains(String value) {
        super.addPredicate(v -> Objects.toString(v).contains(value));
        return this;
    }

    public StringSchema minLength(int mLength) {
        super.addPredicate(v -> v != null ? Objects.toString(v).length() >= mLength : mLength == 0);
        return this;
    }
}
