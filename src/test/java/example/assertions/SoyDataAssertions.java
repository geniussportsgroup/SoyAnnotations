package example.assertions;

import com.geniussports.soy.model.LongData;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.restricted.BooleanData;
import com.google.template.soy.data.restricted.NullData;
import com.google.template.soy.data.restricted.NumberData;
import com.google.template.soy.data.restricted.StringData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SoyDataAssertions {

    public static void assertNullData(SoyData soyData) {
        assertEquals(NullData.INSTANCE, soyData);
    }

    public static void assertString(String value, SoyData object) {
        if (value != null) {
            assertTrue(object instanceof StringData);
            assertEquals(value, object.toString());
        } else {
            assertNullData(object);
        }
    }


    public static void assertInteger(Integer value, SoyData object) {
        if (value != null) {
            assertTrue(object instanceof NumberData);
            assertEquals(value.intValue(), object.integerValue());
        } else {
            assertNullData(object);
        }
    }

    public static void assertLong(Long value, SoyData object) {
        if (value != null) {
            assertTrue(object instanceof LongData);
            assertEquals(value, Long.valueOf(Double.valueOf(object.numberValue()).longValue()));
        } else {
            assertNullData(object);
        }

    }

    public static void assertBoolean(Boolean value, SoyData object) {
        if (value != null) {
            assertTrue(object instanceof BooleanData);
            assertEquals(value, Boolean.valueOf(((BooleanData) object).getValue()));
        } else {
            assertNullData(object);
        }
    }
}
