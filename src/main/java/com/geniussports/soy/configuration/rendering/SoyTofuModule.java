package com.geniussports.soy.configuration.rendering;

import com.google.inject.AbstractModule;
import com.google.template.soy.tofu.SoyTofu;

import javax.annotation.Nonnull;

/**
 * A guice injection module that inserts a piece of SoyTofu.
 * <p>
 * <h3>usage:</h3>
 * <pre>
 * {@code
 *   SoyTofu soytofu = ...;
 *   Guice.createInjector(new SoyTofuModule(soytofu));
 * }
 * </pre>
 */
public class SoyTofuModule extends AbstractModule {

    private final SoyTofu soyTofu;

    /**
     * @param soyTofu The piece of SoyTofu that should be injected.
     */
    public SoyTofuModule(@Nonnull SoyTofu soyTofu) {
        this.soyTofu = soyTofu;
    }

    @Override
    protected void configure() {
        bind(SoyTofu.class).toInstance(soyTofu);
    }
}
