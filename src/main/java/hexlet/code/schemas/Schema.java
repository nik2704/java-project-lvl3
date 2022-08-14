package hexlet.code.schemas;

import hexlet.code.schemas.states.State;

public interface Schema {
    boolean isValid(Object value);
    Schema contains(String value);
    Schema required();
    Schema notRequired();
    Schema minLength(int mLength);

    void setState(State state);
}
