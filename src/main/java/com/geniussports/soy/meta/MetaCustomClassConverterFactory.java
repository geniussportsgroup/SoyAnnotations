package com.geniussports.soy.meta;

import com.geniussports.soy.annotations.CustomConverter;
import com.geniussports.soy.factories.MetaConverterFactory;
import com.geniussports.soy.helpers.FactoryHelper;
import com.google.inject.Inject;
import org.slieb.throwables.FunctionWithThrowable;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
public class MetaCustomClassConverterFactory implements FunctionWithThrowable<Class<?>, MetaClassInformation, ReflectiveOperationException>,
        MetaConverterFactory {

    private final FactoryHelper factoryHelper;

    @Inject
    public MetaCustomClassConverterFactory(FactoryHelper factoryHelper) {
        this.factoryHelper = factoryHelper;
    }

    @Nonnull
    public MetaConverter getConverterInstance(Class<? extends MetaConverter> converterClass) throws IllegalAccessException, InstantiationException {
        MetaConverter converter = converterClass.newInstance();
        if (converter instanceof MetaFactoryHelperAware) {
            ((MetaFactoryHelperAware) converter).setFactoryHelper(factoryHelper);
        }
        return converter;
    }

    @Nonnull
    public Class<? extends MetaConverter> getConverterClass(Class<?> classObject) {
        return classObject.getAnnotation(CustomConverter.class).value();
    }

    public MetaConverter getConverter(Class<?> classObject) throws InstantiationException, IllegalAccessException {
        return getConverterInstance(getConverterClass(classObject));
    }

    @Override
    public MetaClassInformation applyWithThrowable(Class<?> from) throws IllegalAccessException, InstantiationException {
        return new MetaClassInformation(Boolean.TRUE, from, getConverter(from), null, false);
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return classObject.isAnnotationPresent(CustomConverter.class);
    }

    @Nonnull
    @Override
    public MetaCustomClassConverterFactory create(@Nonnull Class<?> classObject) {
        return this;
    }
}
