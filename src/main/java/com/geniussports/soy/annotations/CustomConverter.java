package com.geniussports.soy.annotations;

import com.geniussports.soy.meta.MetaConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CustomConverter {
    Class<? extends MetaConverter> value();
}
