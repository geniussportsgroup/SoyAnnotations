package com.geniussports.soy.converters.meta;

import com.geniussports.soy.helpers.FactoryHelper;
import com.geniussports.soy.meta.MemberFieldValueConverter;
import com.geniussports.soy.meta.MemberMethodValueConverter;
import com.geniussports.soy.meta.MetaClassInformation;
import com.geniussports.soy.meta.MetaMemberInformation;
import com.google.common.collect.ImmutableMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Function;

public class MetaClassConverter implements Function<Class<?>, MetaClassInformation> {

    private final FactoryHelper factoryHelper;

    public MetaClassConverter(FactoryHelper factoryHelper) {
        this.factoryHelper = factoryHelper;
    }

    public Map<String, MetaMemberInformation> getMemberMetaInformation(Class<?> classObject,
                                                                       Boolean dyn) {
        ImmutableMap.Builder<String, MetaMemberInformation> builder = new ImmutableMap.Builder<>();
        for (Field field : classObject.getFields()) {
            if (factoryHelper.isFactoryField(field)) {
                String key = factoryHelper.getFieldKey(field);
                MetaMemberInformation container = getMemberInformation(field, dyn);
                builder.put(key, container);
            }
        }
        for (Method method : classObject.getMethods()) {
            if (factoryHelper.isFactoryMethod(method)) {
                String key = factoryHelper.getMethodKey(method);
                MetaMemberInformation container = getMemberInformation(method, dyn);
                builder.put(key, container);
            }
        }
        return builder.build();
    }

    public MetaClassInformation apply(Class<?> from) {
        Boolean dyn = factoryHelper.isDynamicFactoryClass(from);
        Boolean useOriginalToString = factoryHelper.useOriginalToString(from);
        return new MetaClassInformation(dyn,
                                        from,
                                        null,
                                        getMemberMetaInformation(from, dyn),
                                        useOriginalToString);
    }

    private MetaMemberInformation getMemberInformation(Field field,
                                                       Boolean dyn) {
        return new MetaMemberInformation(dyn || factoryHelper.isDynamicFactoryField(field), field.getType(), new MemberFieldValueConverter(field), field);
    }

    private MetaMemberInformation getMemberInformation(Method method,
                                                       Boolean dyn) {
        return new MetaMemberInformation(dyn || factoryHelper.isDynamicFactoryMethod(method), method.getReturnType(), new MemberMethodValueConverter(method),
                                         method);
    }
}
