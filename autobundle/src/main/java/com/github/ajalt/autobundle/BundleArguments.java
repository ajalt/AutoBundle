package com.github.ajalt.autobundle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Applying this annotation to a class is equivalent to defining {@link
 * BundleArgument} for all fields in the class.
 * <p/>
 * If you want to specify keys for individual arguments, you can use {@link
 * BundleArgument#key()} on individual fields of the same class that this
 * annotation is applied to.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BundleArguments {
}
