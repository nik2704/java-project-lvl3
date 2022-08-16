package hexlet.code.schemas;

import hexlet.code.schemas.states.State;
import lombok.Getter;

import java.util.Objects;
import java.util.function.Predicate;


public abstract class BaseSchema implements Schema {

    public BaseSchema() {
        this.state = new State(this);
    }
    @Getter
    private State state;
    @Override
    public abstract boolean isValid(Object value);

    public final boolean checkRequiredValidity(Object value, Predicate p) {
        if (this.state.isRequired()) {
            if (Objects.isNull(value)) {
                return false;
            }

            if (p != null) {
                return p.test(value);
            }
        }

        return true;
    }

    @Override
    public final BaseSchema required() {
        this.state.setRequired(true);
        return this;
    }

    @Override
    public final BaseSchema notRequired() {
        this.state.setRequired(false);
        return this;
    }

    @Override
    public final void setState(State schemaState) {
        this.state = schemaState;
    }
}
