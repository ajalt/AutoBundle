package com.github.ajalt.autobundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class AutoBundleTest {

    @Test
    public void testPackBundle() {
        final String arg = "Test string value";
        final int arg2 = 31415;
        TestArguments input = new TestArguments(arg, arg2);

        Bundle bundle = new Bundle();
        AutoBundle.packBundle(input, bundle);

        assertThat(bundle.getString("BUNDLE-ARG-stringArg"), is(arg));
        assertThat(bundle.getInt(TestArguments.INT_ARG_KEY), is(arg2));
    }

    @Test
    public void testCreateBundle() throws Exception {
        final String arg = "Test string value";
        final int arg2 = 31415;
        TestArguments input = new TestArguments(arg, arg2);

        Bundle bundle = AutoBundle.createBundle(input);

        assertThat(bundle.getString("BUNDLE-ARG-stringArg"), is(arg));
        assertThat(bundle.getInt(TestArguments.INT_ARG_KEY), is(arg2));
    }

    @Test
    public void testUnpackBundle() throws Exception {
        final String arg = "Test string value";
        final int arg2 = 31415;
        TestArguments input = new TestArguments(arg, arg2);

        Bundle bundle = AutoBundle.createBundle(input);

        TestArguments output = new TestArguments();
        AutoBundle.unpackBundle(bundle, output);

        assertThat(output.stringArg, is(arg));
        assertThat(output.intArg, is(arg2));
    }

    @Test
    public void testPackIntent() throws Exception {
        final String arg = "Test string value";
        final int arg2 = 31415;
        TestArguments input = new TestArguments(arg, arg2);

        Intent intent = new Intent();
        AutoBundle.packIntent(input, intent);

        Bundle bundle = intent.getExtras().getBundle(AutoBundle.PACK_INTENT_ARGUMENT_KEY);

        assertNotNull(bundle);
        assertThat(bundle.getString("BUNDLE-ARG-stringArg"), is(arg));
        assertThat(bundle.getInt(TestArguments.INT_ARG_KEY), is(arg2));
    }

    @Test
    public void testUnpackIntent() throws Exception {
        final String arg = "Test string value";
        final int arg2 = 31415;
        TestArguments input = new TestArguments(arg, arg2);

        Intent intent = new Intent();
        AutoBundle.packIntent(input, intent);

        TestArguments output = new TestArguments(arg, arg2);
        AutoBundle.unpackIntent(intent, output);

        assertThat(output.stringArg, is(arg));
        assertThat(output.intArg, is(arg2));
    }
}
