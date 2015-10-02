package com.github.ajalt.autobundle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that marks a field as an argument for {@link AutoBundle}
 *
 * @see AutoBundle
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BundleArgument {
    /**
     * Optionally specify the Bundle key for this field.
     * <p/>
     * If not given, a key will be generated automatically.
     */
    String key() default "";
}
