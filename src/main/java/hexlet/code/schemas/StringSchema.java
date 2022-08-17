package hexlet.code.schemas;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;


@ToString
public final class StringSchema extends BaseSchema {
    @Getter
    @Setter
    private int minLength = 0;

    @Getter
    @Setter
    private String containedStr;

    @Getter
    @Setter
    private boolean minLengthChecked = false;

    @Getter
    @Setter
    private boolean strContainsChecked = false;

    @Override
    public boolean isValid(Object value) {
        boolean result = super.checkRequiredValidity(value, obj ->
                obj instanceof String && (!Objects.toString(obj).isEmpty()));

        String strValue = value != null ? Objects.toString(value) : "";

        if (this.isMinLengthChecked()) {
            if (strValue.length() < this.getMinLength()) {
                return false;
            }
        }

        if (this.isStrContainsChecked()) {
            if (!strValue.contains(this.getContainedStr())) {
                return false;
            }
        }

        return result;
    }

    @Override
    public StringSchema required() {
        super.setRequired(true);
        return this;
    }

    public StringSchema contains(String value) {
        this.setContainedStr(value);
        this.setStrContainsChecked(true);
        return this;
    }

    public StringSchema minLength(int mLength) {
        this.setMinLength(mLength);
        this.setMinLengthChecked(true);
        return this;
    }

}
