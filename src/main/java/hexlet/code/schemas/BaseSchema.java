package hexlet.code.schemas;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.function.Predicate;


public abstract class BaseSchema {
    @Getter
    @Setter
    private boolean required = false;

    public abstract boolean isValid(Object value);

    public final boolean checkRequiredValidity(Object value, Predicate p) {
        if (this.required) {
            if (Objects.isNull(value)) {
                return false;
            }

            if (p != null) {
                return p.test(value);
            }
        }

        return true;
    }

    public abstract BaseSchema required();
}
