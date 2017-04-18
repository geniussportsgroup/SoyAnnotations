package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.DynamicConverter;
import com.geniussports.soy.converters.soydata.LazySoyListDataConverter;
import com.geniussports.soy.converters.soydata.NullSafeConverter;
import com.geniussports.soy.converters.soydata.SoyListDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.geniussports.soy.helpers.FactoryHelper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;

import javax.annotation.Nonnull;
import java.util.function.Function;

@Singleton
public class LazySoyListDataConverterFactory implements SoyConverterFactory {

    private final FactoryHelper factoryHelper;

    @Inject
    public LazySoyListDataConverterFactory(FactoryHelper factoryHelper) {
        this.factoryHelper = factoryHelper;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Iterable.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ? extends SoyData> create(@Nonnull Class<?> classObject,
                                                      @Nonnull SoyValueFactoryContext context) {
        Boolean useOriginalToString = factoryHelper.useOriginalToString(classObject);
        final DynamicConverter dynamicConverter = new DynamicConverter(context);
        final NullSafeConverter itemConverter = NullSafeConverter.wrapConverterWithNullSafe(dynamicConverter);
        final SoyListDataConverter soyListDataConverter = new SoyListDataConverter(itemConverter);
        return new LazySoyListDataConverter(soyListDataConverter, useOriginalToString);
    }
}
