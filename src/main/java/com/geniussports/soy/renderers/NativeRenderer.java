package com.geniussports.soy.renderers;

import com.geniussports.soy.factories.rendering.Renderer;
import com.google.inject.Singleton;

import javax.annotation.Nullable;

@Singleton
public class NativeRenderer implements Renderer<Object> {

    @Nullable
    @Override
    public String render(@Nullable Object instanceObject) {
        return String.valueOf(instanceObject);
    }
}
