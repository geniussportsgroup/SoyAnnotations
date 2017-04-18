package com.geniussports.soy.factories.jsondata;

import com.geniussports.soy.context.JsonDataFactoryContext;
import com.geniussports.soy.converters.json.ClassToMapConverter;
import com.geniussports.soy.converters.json.DynamicConverter;
import com.geniussports.soy.converters.json.JsonMapConverter;
import com.geniussports.soy.meta.MetaClassInformation;
import com.geniussports.soy.meta.MetaMemberInformation;
import com.geniussports.soy.meta.MetaValueConvertableInformation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MetaInformationToJsonDataConverterConverter implements Function<MetaClassInformation, Function<Object, ?>> {

    private final JsonDataFactoryContext context;

    private final DynamicConverter dynamicConverter;

    public MetaInformationToJsonDataConverterConverter(JsonDataFactoryContext context) {
        this.context = context;
        this.dynamicConverter = new DynamicConverter(context);
    }

    @SuppressWarnings("unchecked")
    public Function<Object, ?> getValueConverter(MetaValueConvertableInformation valueConvertableInformation) {
        Class<?> memberType = valueConvertableInformation.getType();
        Function<Object, ?> valueConverter = valueConvertableInformation.getValueConverter();
        Function<Object, ?> soyConverter = context.create(memberType);
        return valueConverter.andThen(soyConverter);
    }

    @Override
    public Function<Object, ?> apply(MetaClassInformation from) {
        return getClassConverter(from);
    }

    private Map<String, Function<Object, ?>> getConverterMap(MetaClassInformation metaClassInformation) {
        Map<String, Function<Object, ?>> map = new HashMap<>();
        for (Map.Entry<String, MetaMemberInformation> entry : metaClassInformation.getMembersInformation().entrySet()) {
            map.put(entry.getKey(), getValueConverter(entry.getValue()));
        }
        return map;
    }

    private Function<Object, ?> getClassConverter(MetaClassInformation classInformation) {
        Function<Object, ?> valueConverter = classInformation.getValueConverter();
        if (valueConverter == null) {
            return new ClassToMapConverter(getConverterMap(classInformation));
        } else {
            return valueConverter.andThen(new JsonMapConverter(dynamicConverter));
        }
    }
}
