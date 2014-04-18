package slieb.soy.factories.soydata;

import ch.lambdaj.function.convert.Converter;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.restricted.FloatData;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.FloatDataConverter;
import slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;

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
    public Converter<Object, ? extends SoyData> create(@Nonnull Class<?> classObject, @Nonnull SoyDataFactoryContext context) {
        checkArgument(canCreate(classObject));
        return floatDataConverter;
    }



}