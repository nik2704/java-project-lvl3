package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {

    public MapSchema shape(Map<String, BaseSchema> schemas) {

        this.sizeof(schemas.size());

        for (Map.Entry<String, BaseSchema> shape : schemas.entrySet()) {
            addPredicate(v -> ((Map<?, ?>) v).containsKey(shape.getKey()));
            addPredicate(v -> shape.getValue().isValid(((Map<?, ?>) v).get(shape.getKey())));
        }

        return this;
    }

    public MapSchema sizeof(int mapSize) {
        addPredicate(v -> ((Map) v).size() == mapSize);
        return this;
    }

    @Override
    public BaseSchema required() {
        super.required();
        addPredicate(v -> !Objects.isNull(v));
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value instanceof Map) {
            return super.isValid(value);
        }

        if (isRequired()) {
            return false;
        }

        return true;
    }
}
