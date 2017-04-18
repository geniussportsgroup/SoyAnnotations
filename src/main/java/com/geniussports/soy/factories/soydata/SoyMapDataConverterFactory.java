package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.DynamicConverter;
import com.geniussports.soy.converters.soydata.SoyMapDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyMapData;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Function;

import static com.geniussports.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

@Singleton
public class SoyMapDataConverterFactory implements SoyConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Map.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, SoyMapData> create(@Nonnull Class<?> classObject,
                                               @Nonnull SoyValueFactoryContext context) {
        return new SoyMapDataConverter(wrapConverterWithNullSafe(new DynamicConverter(context)));
    }
}
