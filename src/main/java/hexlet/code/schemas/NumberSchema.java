package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema {
    @Override
    public boolean isValid(Object value) {
        boolean result = super.checkRequiredValidity(value, obj -> obj instanceof Integer);

        if (!Objects.isNull(value)) {
            int intValue = Integer.parseInt(Objects.toString(value));

            if (super.getState().isPositiveChecked()) {
                if (intValue < 0) {
                    return false;
                }
            }

            if (super.getState().isRangeChecked()) {
                if (!((intValue >= super.getState().getMinValue()) && (intValue <= super.getState().getMaxValue()))) {
                    return false;
                }
            }
        }

        return result;
    }

    @Override
    public Schema positive() {
        super.getState().setPositiveChecked(true);
        return this;
    }

    @Override
    public Schema range(int start, int end) {
        super.getState().setMinValue(start);
        super.getState().setMaxValue(end);
        super.getState().setRangeChecked(true);
        return this;
    }

}
