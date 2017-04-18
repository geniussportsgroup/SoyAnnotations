package com.geniussports.soy.factories.soydata;

import com.geniussports.soy.context.SoyValueFactoryContext;
import com.geniussports.soy.converters.soydata.FloatDataConverter;
import com.geniussports.soy.factories.SoyConverterFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;

import javax.annotation.Nonnull;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class FloatDataConverterFactory implements SoyConverterFactory {

    private final FloatDataConverter floatDataConverter;

    @Inject
    public FloatDataConverterFactory(FloatDataConverter floatDataConverter) {
        this.floatDataConverter = floatDataConverter;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Float.class.equals(classObject) || Double.class.equals(classObject) ||
                classObject.equals(double.class) || classObject.equals(float.class);
    }

    @Nonnull
    @Override
    public Function<Object, ? extends SoyData> create(@Nonnull Class<?> classObject,
                                                      @Nonnull SoyValueFactoryContext context) {
        checkArgument(canCreate(classObject));
        return floatDataConverter;
    }
}
