package hexlet.code.schemas;

import hexlet.code.schemas.states.State;
import lombok.ToString;

import java.util.Objects;

@ToString
public final class StringSchema implements Schema {

    private State state;

    public StringSchema() {
        this.state = new State(this);
    }

    @Override
    public boolean isValid(Object value) {
        boolean result = true;

        if (this.state.isRequired() && Objects.isNull(value)) {
            return false;
        }

        String strValue = value != null ? Objects.toString(value) : "";
        if (this.state.isRequired() && strValue.isEmpty()) {
            return false;
        }

        if (this.state.isMinLengthChecked()) {
            if (strValue.length() < this.state.getMinLength()) {
                return false;
            }
        }

        if (this.state.isStrContainsChecked()) {
            if (!strValue.contains(this.state.getContainedStr())) {
                return false;
            }
        }

        return result;
    }

    @Override
    public Schema contains(String value) {
        this.state.setContainedStr(value);
        this.state.setStrContainsChecked(true);
        return this;
    }

    @Override
    public Schema required() {
        this.state.setRequired(true);
        return this;
    }

    @Override
    public Schema notRequired() {
        this.state.setRequired(false);
        return this;
    }

    @Override
    public Schema minLength(int mLength) {
        this.state.setMinLength(mLength);
        this.state.setMinLengthChecked(true);
        return this;
    }

    @Override
    public void setState(State schemaState) {
        this.state = schemaState;
    }
}
