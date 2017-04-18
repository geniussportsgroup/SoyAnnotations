package com.geniussports.soy.model;

import com.google.template.soy.data.SoyMapData;

/**
 * Extends SoyMapData to override the toString method.
 * <p>
 * <h3>example:</h3>
 * <pre>
 * {@code
 *   new SoyMapDataWithToStringProvider(new String("Boo")).toString() // "Boo";
 * }
 * </pre>
 */
public class SoyMapDataWithToStringProvider extends SoyMapData {

    private final Object toStringProvider;

    /**
     * @param toStringProvider an Object who provides a toString method that
     *                         will be used to override the map's toString.
     */
    public SoyMapDataWithToStringProvider(Object toStringProvider) {
        this.toStringProvider = toStringProvider;
    }

    @Override
    public String toString() {
        return String.valueOf(toStringProvider);
    }
}
