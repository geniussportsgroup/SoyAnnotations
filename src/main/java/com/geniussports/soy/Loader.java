package com.geniussports.soy;

import com.geniussports.soy.configuration.DefaultFactoryHelperModule;
import com.geniussports.soy.configuration.SingletonModule;
import com.geniussports.soy.configuration.jsondata.BasicJsonDataConvertersFactoryModule;
import com.geniussports.soy.configuration.jsondata.MetaJsonDataConvertersFactoryModule;
import com.geniussports.soy.configuration.meta.MetaFactoriesModule;
import com.geniussports.soy.configuration.rendering.DelegateTemplates;
import com.geniussports.soy.configuration.rendering.RenderingFactoriesModule;
import com.geniussports.soy.configuration.rendering.SoyTofuModule;
import com.geniussports.soy.configuration.soydata.BasicSoyDataConvertersFactoryModule;
import com.geniussports.soy.configuration.soydata.LazyClassSoyFactoriesModule;
import com.geniussports.soy.configuration.soydata.MetaClassBindingsModule;
import com.geniussports.soy.context.JsonDataFactoryContext;
import com.geniussports.soy.context.SoyValueFactoryContext;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.template.soy.tofu.SoyTofu;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

import static com.google.inject.Guice.createInjector;

@SuppressWarnings("WeakerAccess")
public class Loader {

    private static Injector basicInjector, fullInjector, lazyInjector;

    /**
     * @return A injector that enables basic types to be
     * converter to soy and json.
     */
    @Nonnull
    public static Injector getBasicInjector() {
        if (basicInjector == null) {
            basicInjector = createInjector(getBasicModules());
        }
        return basicInjector;
    }

    @Nonnull
    public static Module[] getBasicModules() {
        return new Module[]{new DefaultFactoryHelperModule(),
                new SingletonModule(SoyValueFactoryContext.class),
                new SingletonModule(JsonDataFactoryContext.class),
                new BasicSoyDataConvertersFactoryModule(),
                new BasicJsonDataConvertersFactoryModule()};
    }

    /**
     * @return A injector that enables basic types to be
     * converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getFullInjector() {
        if (fullInjector == null) {
            fullInjector = createInjector(getFullModules());
        }
        return fullInjector;
    }

    @Nonnull
    public static Module[] getFullModules() {
        return new Module[]{new DefaultFactoryHelperModule(),
                new BasicSoyDataConvertersFactoryModule(),
                new BasicJsonDataConvertersFactoryModule(),
                new MetaFactoriesModule(),
                new MetaClassBindingsModule(),
                new MetaJsonDataConvertersFactoryModule(),
                new SingletonModule(SoyValueFactoryContext.class),
                new SingletonModule(JsonDataFactoryContext.class)};
    }

    /**
     * @param soyTofu          The tofu object.
     * @param delegatePackages A set of delegate packages.
     * @return A injector that enables basic types to be
     * converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getFullInjector(@Nonnull SoyTofu soyTofu,
                                           @Nullable Set<String> delegatePackages) {
        return getFullInjector()
                .createChildInjector(getPackagesModules(soyTofu, delegatePackages));
    }

    public static Module[] getPackagesModules(final @Nonnull SoyTofu soyTofu,
                                              final @Nullable Set<String> delegatePackages) {
        return new Module[]{
                new RenderingFactoriesModule(),
                new DelegateTemplates(delegatePackages),
                new SoyTofuModule(soyTofu)};
    }

    /**
     * @return A injector that enables basic types to be
     * converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getLazyInjector() {
        if (lazyInjector == null) {
            lazyInjector = createInjector(
                    getLazyModules());
        }
        return lazyInjector;
    }

    public static Module[] getLazyModules() {
        return new Module[]{new DefaultFactoryHelperModule(),
                new BasicSoyDataConvertersFactoryModule(),
                new BasicJsonDataConvertersFactoryModule(),
                new MetaFactoriesModule(),
                new MetaClassBindingsModule(),
                new LazyClassSoyFactoriesModule(),
                new SingletonModule(SoyValueFactoryContext.class),
                new SingletonModule(JsonDataFactoryContext.class)};
    }

    /**
     * @param soyTofu          The tofu object.
     * @param delegatePackages set of delegate packages.
     * @return A injector that enables basic types to be
     * converter to soy and json. As well as type annotations.
     */
    @Nonnull
    public static Injector getLazyInjector(@Nonnull SoyTofu soyTofu,
                                           @Nullable Set<String> delegatePackages) {
        return getLazyInjector()
                .createChildInjector(
                        new RenderingFactoriesModule(),
                        new DelegateTemplates(delegatePackages),
                        new SoyTofuModule(soyTofu));
    }
}
