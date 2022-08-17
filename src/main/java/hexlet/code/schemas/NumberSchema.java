package hexlet.code.schemas;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {
    @Getter
    @Setter
    private boolean positiveChecked = false;

    @Getter
    @Setter
    private boolean rangeChecked = false;

    @Getter
    @Setter
    private int minValue = 0;

    @Getter
    @Setter
    private int maxValue = 0;

    @Override
    public boolean isValid(Object value) {
        boolean result = super.checkRequiredValidity(value, obj -> obj instanceof Integer);

        if (!Objects.isNull(value)) {
            int intValue = Integer.parseInt(Objects.toString(value));

            if (this.isPositiveChecked()) {
                if (intValue <= 0) {
                    return false;
                }
            }

            if (this.isRangeChecked()) {
                if (!((intValue >= this.getMinValue()) && (intValue <= this.getMaxValue()))) {
                    return false;
                }
            }
        }

        return result;
    }

    @Override
    public NumberSchema required() {
        super.setRequired(true);
        return this;
    }
    public NumberSchema positive() {
        this.setPositiveChecked(true);
        return this;
    }

    public NumberSchema range(int start, int end) {
        this.setMinValue(start);
        this.setMaxValue(end);
        this.setRangeChecked(true);
        return this;
    }

}
