package hexlet.code.schemas.states;

import hexlet.code.schemas.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class State {
    private final Schema schema;
// String Methods
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
//*****
//*** Integer methods
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
//*****
//*** Map
    @Getter
    @Setter
    private int size = 0;

    @Getter
    @Setter
    private boolean sizeChecked = false;
//*****
    @Getter
    @Setter
    private boolean required = false;
}
