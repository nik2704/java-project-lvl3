package hexlet.code.schemas;

import hexlet.code.schemas.states.State;

import java.util.Map;


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

    default Schema shape(Map<String, Schema> schemas) {
        return this;
    }
    default Schema sizeof(int size) {
        return  this;
    }

    void setState(State state);
}
