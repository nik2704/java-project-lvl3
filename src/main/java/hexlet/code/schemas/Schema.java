package hexlet.code.schemas;

import hexlet.code.schemas.states.State;


public interface Schema {
    boolean isValid(Object value);
    default Schema contains(String value) {
        return this;
    }
    Schema required();
    Schema notRequired();
    default Schema minLength(int mLength) {
        return this;
    }
    default Schema positive() {
        return this;
    }
    default Schema range(int start, int end) {
        return this;
    }

    void setState(State state);
}
