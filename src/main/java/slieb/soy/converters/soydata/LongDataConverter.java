package slieb.soy.converters.soydata;


import slieb.soy.internal.Converter;
import com.google.inject.Singleton;
import slieb.soy.model.LongData;

@Singleton
public class LongDataConverter implements Converter<Object, LongData> {

    @Override
    public LongData apply(Object from) {
        if (from instanceof Long) {
            return new LongData((Long) from);
        } else {
            return null;
        }
    }
}
