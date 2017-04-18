package com.geniussports.soy.meta;


import javax.annotation.concurrent.Immutable;

@Immutable
public class MetaInformation {
    private final Boolean isDynamic;

    private final Class<?> type;


    public MetaInformation(Boolean dynamic, Class<?> type) {
        isDynamic = dynamic;
        this.type = type;
    }

    public final Boolean getDynamic() {
        return isDynamic;
    }

    public final Class<?> getType() {
        return type;
    }
}
