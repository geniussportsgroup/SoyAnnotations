package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.DynamicConverter;
import com.geniussports.soy.converters.soydata.NullSafeConverter;
import com.geniussports.soy.converters.soydata.SoyListDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.template.soy.data.SoyData;

import javax.annotation.Nonnull;
import java.util.function.Function;

public class SoyListDataConverterFactory implements SoyConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Iterable.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ? extends SoyData> create(@Nonnull Class<?> classObject,
                                                      @Nonnull SoyValueFactoryContext context) {
        return new SoyListDataConverter(NullSafeConverter.wrapConverterWithNullSafe(new DynamicConverter(context)));
    }
}
