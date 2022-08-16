package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {
    private Map<String, Schema> schemasCtrl;
    private boolean shapeChecked = false;

    @Override
    public Schema shape(Map<String, Schema> schemas) {
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
            if (super.getState().isSizeChecked()) {
                if (!(((Map) value).size() == super.getState().getSize())) {
                    return false;
                }
            }
        }

        if (this.shapeChecked) {
            if (this.schemasCtrl.size() != ((Map) value).size()) {
                return false;
            }

            for (Map.Entry<String, Schema> shape : this.schemasCtrl.entrySet()) {
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
    public Schema sizeof(int size) {
        super.getState().setSize(size);
        super.getState().setSizeChecked(true);
        return this;
    }
}
