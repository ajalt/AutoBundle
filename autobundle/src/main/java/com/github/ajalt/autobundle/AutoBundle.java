package com.github.ajalt.autobundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * A class for automatically packing and unpacking class fields into {@link Bundle}s.
 * <p/>
 * To use, annotate primitive or {@link android.os.Parcelable} fields of an object of any type with
 * {@link BundleArgument}, and pass that object and any {@link Bundle} to {@link #packBundle(Object,
 * Bundle)}. The values of the annotated fields of the object will be added to the Bundle.
 * <p/>
 * To unpack the data, call {@link #unpackBundle(Bundle, Object)} with a bundle containing data from
 * a call to packBundle, and an instance of the same class that was passed to the packBundle call.
 * The annotated field of the class instance will be set with the unpacked values.
 */
public class AutoBundle {
    public static final String PACK_INTENT_ARGUMENT_KEY = "AUTO_BUNDLE_PACK_INTENT_ARGUMENT_KEY";

    /**
     * Unpack the extras bundle from an intent that was previously packed with {@link
     * #packBundle(Object, Bundle)} or {@link #packIntent(Object, Intent)}.
     *
     * @param source The intent to unpack extras from.
     * @param target The object to unpack into. This method has no effect if the target does not
     *               have any fields annotated with {@link BundleArgument}
     */
    public static void unpackIntent(Intent source, Object target) {
        unpackBundle(source.getExtras(), target);
    }

    /**
     * Unpack the a bundle that was previously packed with {@link #packBundle(Object, Bundle)} or
     * {@link #packIntent(Object, Intent)}.
     *
     * @param source The intent to unpack extras from.
     * @param target The object to unpack into. This method has no effect if the target does not
     *               have any fields annotated with {@link BundleArgument}
     */
    public static void unpackBundle(Bundle source, Object target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("AutoBundle arguments cannot be null");
        }

        // Check if this is coming from a packIntent call
        Bundle intentBundle = source.getBundle(PACK_INTENT_ARGUMENT_KEY);
        if (intentBundle != null) {
            source = intentBundle;
        }

        for (Field field : target.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(BundleArgument.class)) continue;
            Object o = source.get(getKey(field));
            field.setAccessible(true);
            try {
                field.set(target, o);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Pack the extras of an intent.
     *
     * @param source An object with fields annotated with {@link BundleArgument}.
     * @param target The intent to pack extras into.
     */
    public static void packIntent(Object source, Intent target) {
        target.putExtra(PACK_INTENT_ARGUMENT_KEY, createBundle(source));
    }

    /**
     * Create a new, packed, {@link Bundle}.
     *
     * @param source An object with fields annotated with {@link BundleArgument}.
     * @return A new {@link Bundle} with values from all annotated source fields added.
     */
    public static Bundle createBundle(Object source) {
        Bundle bundle = new Bundle();
        packBundle(source, bundle);
        return bundle;
    }

    /**
     * Pack annotated fields into a {@link Bundle}.
     *
     * @param source An object with fields annotated with {@link BundleArgument}.
     * @param target A {@link Bundle} into which values from all annotated source fields will be
     *               added.
     */
    public static void packBundle(Object source, Bundle target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("AutoBundle arguments cannot be null");
        }

        Map<String, Object> bundleMap = null;
        for (Class<?> cls = target.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field field = cls.getDeclaredField("mMap");
                field.setAccessible(true);
                bundleMap = (Map<String, Object>) field.get(target);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException ignored) {
            }
        }

        if (bundleMap == null) {
            throw new RuntimeException("Could not access internal bundle map");
        }

        for (Field field : source.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(BundleArgument.class)) continue;
            field.setAccessible(true);
            try {
                Object o = field.get(source);
                bundleMap.put(getKey(field), o);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        Log.d("AutoBundle", "map: " + bundleMap);
    }

    /**
     * Return the key for a field.
     * <p/>
     * It may be defined in the field, or it will be generated based on the name of the field.
     */
    private static String getKey(Field field) {
        BundleArgument annotation = field.getAnnotation(BundleArgument.class);
        return TextUtils.isEmpty(annotation.key()) ?
                "BUNDLE-ARG" + field.getName()
                : annotation.key();
    }
}
