package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapSchemaTest {
    private final int testSize = 2;

    @Test
    public void testValue() {

        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isEqualTo(true);
        schema.required();

        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(new HashMap())).isEqualTo(true);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isEqualTo(true);

        schema.sizeof(testSize);
        assertThat(schema.isValid(data)).isEqualTo(false);
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isEqualTo(true);

    }
}
