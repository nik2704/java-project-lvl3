package hexlet.code.schemas;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {

    @Getter
    @Setter
    private int size = 0;

    @Getter
    @Setter
    private boolean sizeChecked = false;
    private Map<String, BaseSchema> schemasCtrl;
    private boolean shapeChecked = false;

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.schemasCtrl = schemas;
        this.shapeChecked = true;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (!super.checkRequiredValidity(value, null)) {
            return false;
        }

        if (!Objects.isNull(value)) {
            if (this.isSizeChecked()) {
                if (!(((Map) value).size() == this.getSize())) {
                    return false;
                }
            }
        }

        if (this.shapeChecked) {
            if (this.schemasCtrl.size() != ((Map) value).size()) {
                return false;
            }

            for (Map.Entry<String, BaseSchema> shape : this.schemasCtrl.entrySet()) {
                Object valueForChecking = ((Map) value).get(shape.getKey());

                if (!((Map) value).containsKey(shape.getKey())) {
                    return false;
                }

                if (!shape.getValue().isValid(valueForChecking)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public MapSchema required() {
        super.setRequired(true);
        return this;
    }
    public MapSchema sizeof(int mapSize) {
        this.setSize(mapSize);
        this.setSizeChecked(true);
        return this;
    }
}
