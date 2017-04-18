package com.geniussports.soy.configuration.rendering;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

import javax.annotation.Nullable;
import java.util.Set;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

@SuppressWarnings("WeakerAccess")
public class DelegateTemplates extends AbstractModule {

    public static final String DELEGATE_PACKAGES = "DelegatePackages";

    public final Set<String> delegatePackages;

    public DelegateTemplates(@Nullable Set<String> delegatePackages) {
        this.delegatePackages = delegatePackages;
    }

    @Override
    protected void configure() {
        Multibinder<String> binder = newSetBinder(binder(), String.class, Names.named(DELEGATE_PACKAGES));
        if (delegatePackages != null) {
            for (String string : delegatePackages) {
                binder.addBinding().toInstance(string);
            }
        }
    }
}
