package hexlet.code.schemas;


import lombok.ToString;



@ToString
public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        return (StringSchema) setRequiredCondition(v -> !v.isEmpty());
    }

    public StringSchema contains(String value) {
        addPredicate(v -> v.contains(value));
        return this;
    }

    public StringSchema minLength(int mLength) {
        addPredicate(v -> v.length() >= mLength);
        return this;
    }

    @Override
    public boolean checkInstanceOfRequiredClass(Object value) {
        return value instanceof String;
    }

}
