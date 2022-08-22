package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberSchemaTest {
    private final int v10 = 10;
    private final int v4 = 4;
    private final int v5 = 5;
    private final int v11 = 11;
    private final int vM10 = -10;

    @Test
    public void testValue() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.isValid("10")).isEqualTo(true);
        assertThat(schema.isValid("ABC")).isEqualTo(true);
        assertThat(schema.isValid(new NumberSchema())).isEqualTo(true);

        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(v10)).isEqualTo(true);
        assertThat(schema.isValid("5")).isEqualTo(false);

        assertThat(schema.positive().isValid(v10)).isEqualTo(true);
        assertThat(schema.isValid(vM10)).isEqualTo(false);

        schema.range(v5, v10);
        assertThat(schema.isValid(v5)).isEqualTo(true);
        assertThat(schema.isValid(v10)).isEqualTo(true);
        assertThat(schema.isValid(v4)).isEqualTo(false);
        assertThat(schema.isValid(v11)).isEqualTo(false);
    }
}
