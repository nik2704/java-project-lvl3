package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class StringSchemaTest {
    private final int v0 = 0;
    private final int v5 = 5;
    private final int v6 = 6;
    private final int v7 = 7;

    @Test
    public void testValue() {
        Validator v = new Validator();

        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);

        assertThat(schema.required());

        assertThat(schema.isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.isValid("hexlet")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid("")).isEqualTo(false);

        assertThat(schema.contains("wh").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);

        assertThat(schema.isValid("what does the fox say")).isEqualTo(false);
    }

    @Test
    public void testLengthValue() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.minLength(v6).isValid("stroka")).isEqualTo(true);
        assertThat(schema.minLength(v5).isValid("stroka")).isEqualTo(true);
        assertThat(schema.minLength(v7).isValid("stroka")).isEqualTo(false);
        assertThat(schema.minLength(v7).isValid(" ")).isEqualTo(false);
        assertThat(schema.minLength(v0).isValid(null)).isEqualTo(true);
    }

}
