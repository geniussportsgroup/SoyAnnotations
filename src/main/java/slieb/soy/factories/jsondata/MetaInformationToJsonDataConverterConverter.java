package slieb.soy.factories.jsondata;

import com.google.template.soy.data.SoyValue;
import slieb.soy.context.JsonDataFactoryContext;
import slieb.soy.converters.json.ClassToMapConverter;
import slieb.soy.converters.json.DynamicConverter;
import slieb.soy.converters.json.JsonMapConverter;
import slieb.soy.meta.MetaClassInformation;
import slieb.soy.meta.MetaMemberInformation;
import slieb.soy.meta.MetaValueConvertableInformation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static slieb.utilities.ConverterUtils.chain;
import static slieb.utilities.ConverterUtils.join;

public class MetaInformationToJsonDataConverterConverter implements Function<MetaClassInformation, Function<Object, ?>> {

    private final JsonDataFactoryContext context;

    private final DynamicConverter dynamicConverter;

    public MetaInformationToJsonDataConverterConverter(JsonDataFactoryContext context) {
        this.context = context;
        this.dynamicConverter = new DynamicConverter(context);
    }

    private Map<String, Function<Object, ?>> getConverterMap(MetaClassInformation metaClassInformation) {
        Map<String, Function<Object, ?>> map = new HashMap<>();
        for (Map.Entry<String, MetaMemberInformation> entry : metaClassInformation.getMembersInformation().entrySet()) {
            map.put(entry.getKey(), getValueConverter(entry.getValue()));
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public Function<Object, ?> getValueConverter(MetaValueConvertableInformation valueConvertableInformation) {
        Class<?> memberType = valueConvertableInformation.getType();
        Function<Object, ?> valueConverter = valueConvertableInformation.getValueConverter();
        Function<Object, ?> soyConverter = context.create(memberType);
        return (Function<Object, ? extends SoyValue>) chain(valueConverter, soyConverter);
    }

    private Function<Object, ?> getClassConverter(MetaClassInformation classInformation) {
        Function<Object, ?> valueConverter = classInformation.getValueConverter();
        if (valueConverter == null) {
            return new ClassToMapConverter(getConverterMap(classInformation));
        } else {
            return join(valueConverter, new JsonMapConverter(dynamicConverter));
        }
    }

    @Override
    public Function<Object, ?> apply(MetaClassInformation from) {
        return getClassConverter(from);
    }
}
