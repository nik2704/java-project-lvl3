package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map> {

    public MapSchema shape(Map<String, BaseSchema> schemas) {

        this.sizeof(schemas.size());

        for (Map.Entry<String, BaseSchema> shape : schemas.entrySet()) {
            addPredicate(v -> v.containsKey(shape.getKey()));
            addPredicate(v -> shape.getValue().isValid(v.get(shape.getKey())));
        }

        return this;
    }

    public MapSchema sizeof(int mapSize) {
        addPredicate(v -> v.size() == mapSize);
        return this;
    }

    @Override
    public MapSchema required() {
        return (MapSchema) setRequiredCondition(v -> !Objects.isNull(v));
    }

    @Override
    public boolean checkInstanceOfRequiredClass(Object value) {
        return value instanceof Map;
    }

}
