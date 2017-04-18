package com.geniussports.soy.factories.rendering;

import javax.annotation.Nullable;

public interface Renderer<A> {

    String render(@Nullable A instanceObject);
}
