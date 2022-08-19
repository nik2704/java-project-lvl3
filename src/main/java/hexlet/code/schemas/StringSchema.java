package hexlet.code.schemas;


import lombok.ToString;
import java.util.Objects;


@ToString
public final class StringSchema extends BaseSchema {
    @Override
    public StringSchema required() {
        super.required();
        addPredicate(v -> !Objects.toString(v).isEmpty());
        return this;
    }

    public StringSchema contains(String value) {
        addPredicate(v -> Objects.toString(v).contains(value));
        return this;
    }

    public StringSchema minLength(int mLength) {
        addPredicate(v -> v != null ? Objects.toString(v).length() >= mLength : mLength == 0);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value instanceof String) {
            return super.isValid(value);
        }

        if (isRequired()) {
            return false;
        }

        return true;
    }

}
