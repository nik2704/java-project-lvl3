package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {

    public MapSchema shape(Map<String, BaseSchema> schemas) {

        this.sizeof(schemas.size());

        for (Map.Entry<String, BaseSchema> shape : schemas.entrySet()) {
            super.addPredicate(v -> v instanceof Map ? ((Map<?, ?>) v).containsKey(shape.getKey()) : false);
            super.addPredicate(v -> shape.getValue().isValid(((Map<?, ?>) v).get(shape.getKey())));
        }

        return this;
    }

    public MapSchema sizeof(int mapSize) {
        super.addPredicate(v -> v instanceof Map ? ((Map) v).size() == mapSize : false);
        return this;
    }

    @Override
    public BaseSchema required() {
        super.required();
        super.addPredicate(v -> !Objects.isNull(v));
        super.addPredicate(v -> v instanceof Map);
        return this;
    }
}
