package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {
    @Override
    public boolean isValid(Object value) {
        boolean result = super.checkRequiredValidity(value, null);

        if (!Objects.isNull(value)) {
            if (super.getState().isSizeChecked()) {
                result = ((Map) value).size() == super.getState().getSize();
            }
        }

        return result;
    }

    @Override
    public Schema sizeof(int size) {
        super.getState().setSize(size);
        super.getState().setSizeChecked(true);
        return this;
    }
}
