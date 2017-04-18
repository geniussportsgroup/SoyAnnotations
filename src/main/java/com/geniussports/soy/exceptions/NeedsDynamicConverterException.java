package com.geniussports.soy.exceptions;


import java.lang.reflect.Member;

import static java.lang.String.format;

public class NeedsDynamicConverterException extends RuntimeException {

    private static final String CLASS_NEEDS_DYNAMIC_FORMAT =
            "%s needs to be marked as dynamic";

    private static final String MEMBER_NEEDS_DYNAMIC_FORMAT =
            "%s#%s needs to be marked as dynamic";

    public NeedsDynamicConverterException(Class<?> classObject, Throwable throwable) {
        super(format(CLASS_NEEDS_DYNAMIC_FORMAT, classObject.getName()), throwable);
    }

    public NeedsDynamicConverterException(Member member, Throwable throwable) {
        super(format(MEMBER_NEEDS_DYNAMIC_FORMAT, member.getDeclaringClass().getName(), member.getName()), throwable);
    }

    public NeedsDynamicConverterException(String message) {
        super(message);
    }

    public NeedsDynamicConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeedsDynamicConverterException(Throwable cause) {
        super(cause);
    }

    public NeedsDynamicConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
