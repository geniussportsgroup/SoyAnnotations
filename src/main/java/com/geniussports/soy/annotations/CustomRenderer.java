package com.geniussports.soy.annotations;

import com.geniussports.soy.factories.rendering.Renderer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CustomRenderer {
    Class<? extends Renderer<Object>> value();
}
