package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class StringSchemaTest {

    @ParameterizedTest
    @CsvSource(value = {
            "stroka, default, 0, true",
            "BLANC, default, 0, true",
            "NULL, default, 0, true",
            "stroka, REQUIRED, 0, true",
            "BLANC, REQUIRED, 0, false",
            "NULL, REQUIRED, 0, false",
            "stroka, MIN, 6, true",
            "stroka, MIN, 5, true",
            "stroka, MIN, 7, false",
            "WHITESPACE, REQUIRED, 0, true",
            "WHITESPACE, MIN, 1, true",
            "WHITESPACE, MIN, 0, true",
        }, ignoreLeadingAndTrailingWhitespace = true)
    public void testValue(String value, String mode, int minLength, boolean expected) {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean controlData = false;
        String testValue = value;

        switch (value) {
            case "BLANC":
                testValue = "";
                break;
            case "NULL":
                testValue = null;
                break;
            case "WHITESPACE":
                testValue = " ";
                break;
            default:
        }

        switch (mode) {
            case "default":
                controlData = schema.isValid(testValue);
                break;
            case "REQUIRED":
                controlData = schema.required().isValid(testValue);
                break;
            case "MIN":
                controlData = schema.minLength(minLength).isValid(testValue);
                break;
            default:
        }

        assertThat(controlData).isEqualTo(expected);
    }
}