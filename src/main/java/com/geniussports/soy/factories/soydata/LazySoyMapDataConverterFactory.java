package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.DynamicConverter;
import com.geniussports.soy.converters.soydata.LazyMapDataConverter;
import com.geniussports.soy.converters.soydata.NullSafeConverter;
import com.geniussports.soy.converters.soydata.SoyMapDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Function;

@Singleton
public class LazySoyMapDataConverterFactory implements SoyConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Map.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, SoyData> create(@Nonnull Class<?> classObject, @Nonnull SoyValueFactoryContext context) {
        return new LazyMapDataConverter(new SoyMapDataConverter(NullSafeConverter.wrapConverterWithNullSafe(new DynamicConverter(context))));
    }
}
