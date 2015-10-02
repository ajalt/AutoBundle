package com.github.ajalt.autobundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Map;

public class AutoBundle {
    public static final String PACK_INTENT_ARGUMENT_KEY = "AUTO_BUNDLE_PACK_INTENT_ARGUMENT_KEY";

    public static void unpackIntent(Intent source, Object target) {
        unpackBundle(source.getExtras(), target);
    }

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

    public static void packIntent(Object source, Intent target) {
        target.putExtra(PACK_INTENT_ARGUMENT_KEY, createBundle(source));
    }

    public static Bundle createBundle(Object source) {
        Bundle bundle = new Bundle();
        packBundle(source, bundle);
        return bundle;
    }

    public static void packBundle(Object source, Bundle target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("AutoBundle arguments cannot be null");
        }

        Map<String,Object> bundleMap = null;
        for (Class<?> cls = target.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field field = cls.getDeclaredField("mMap");
                field.setAccessible(true);
                bundleMap = (Map<String, Object>) field.get(target);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException ignored) {}
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

    private static String getKey(Field field) {
        BundleArgument annotation = field.getAnnotation(BundleArgument.class);
        return TextUtils.isEmpty(annotation.key()) ?
                "BUNDLE-ARG" + field.getName()
                : annotation.key();
    }
}
