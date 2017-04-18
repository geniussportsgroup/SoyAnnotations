package com.geniussports.soy.model;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class DefaultLazyResultWithOriginalToString<A, B>
        extends DefaultLazyResult<A, B> {

    public DefaultLazyResultWithOriginalToString(A object,
                                                 Function<A, B> converter) {
        super(object, converter);
    }

    @Override
    public String toString() {
        return object.toString();
    }
}
