package hexlet.code.schemas.states;

import hexlet.code.schemas.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class State {
    private final Schema schema;

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

    @Getter
    @Setter
    private boolean required = false;
}
