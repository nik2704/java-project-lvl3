package hexlet.code.schemas;

import lombok.ToString;

import java.util.Objects;


@ToString
public final class StringSchema extends BaseSchema {

    @Override
    public boolean isValid(Object value) {
        boolean result = super.checkRequiredValidity(value, obj ->
                obj instanceof String && (!Objects.toString(obj).isEmpty()));

        String strValue = value != null ? Objects.toString(value) : "";

        if (super.getState().isMinLengthChecked()) {
            if (strValue.length() < super.getState().getMinLength()) {
                return false;
            }
        }

        if (super.getState().isStrContainsChecked()) {
            if (!strValue.contains(super.getState().getContainedStr())) {
                return false;
            }
        }

        return result;
    }

    @Override
    public Schema contains(String value) {
        super.getState().setContainedStr(value);
        super.getState().setStrContainsChecked(true);
        return this;
    }

    @Override
    public Schema minLength(int mLength) {
        super.getState().setMinLength(mLength);
        super.getState().setMinLengthChecked(true);
        return this;
    }

}
