package com.geniussports.soy.converters.soydata;

import com.geniussports.soy.model.SoyMapDataWithToStringProvider;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;
import java.util.function.Function;

public class ClassToSoyMapDataConverter implements Function<Object, SoyMapData> {

    /**
     * Important to keep this SoyData for the time being.
     */
    public final Map<String, Function<Object, ? extends SoyData>> membersConverters;

    private final Boolean useOriginalToString;

    public ClassToSoyMapDataConverter(Map<String, Function<Object, ? extends SoyData>> membersConverters,
                                      Boolean useOriginalToString) {
        this.membersConverters = membersConverters;
        this.useOriginalToString = useOriginalToString;
    }

    @Override
    public SoyMapData apply(Object from) {
        if (from != null) {
            SoyMapData soyMapData = getSoyMapData(from);
            for (Map.Entry<String, Function<Object, ? extends SoyData>> entry : membersConverters.entrySet()) {
                soyMapData.put(entry.getKey(), entry.getValue().apply(from));
            }
            return soyMapData;
        } else {
            return null;
        }
    }

    private SoyMapData getSoyMapData(Object from) {
        if (useOriginalToString) {
            return new SoyMapDataWithToStringProvider(from);
        } else {
            return new SoyMapData();
        }
    }
}
