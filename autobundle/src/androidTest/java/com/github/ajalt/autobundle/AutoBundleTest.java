package com.github.ajalt.autobundle;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void testPackBundleArguments() {
        final String arg = "Test string value";
        final int arg2 = 31415;
        TestBundleArguments input = new TestBundleArguments(arg, arg2);

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
    public void testUnpackBundleArguments() throws Exception {
        final String arg = "Test string value";
        final int arg2 = 31415;
        TestBundleArguments input = new TestBundleArguments(arg, arg2);

        Bundle bundle = AutoBundle.createBundle(input);

        TestBundleArguments output = new TestBundleArguments();
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

        TestArguments output = new TestArguments();
        AutoBundle.unpackIntent(intent, output);

        assertThat(output.stringArg, is(arg));
        assertThat(output.intArg, is(arg2));
    }

    @Test
    public void allArgumentTypesAreSupported() throws Exception {
        byte byteArg = 12;
        char charArg = (char) -36;
        short shortArg = 1234;
        float floatArg = 1.5f;
        CharSequence charSequenceArg = "charsequence";
        Parcelable parcelableArg = new Point(5, 10);
        Parcelable[] parcelableArrayArg = new Parcelable[]{new PointF(1f, 2f), new Point(3, 4)};
        List<Parcelable> parcelableListArg = Arrays.asList((Parcelable) new Point(5, 6), new Point(7, 8));
        ArrayList<Integer> integerArrayListArg = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<String> stringArrayListArg = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Serializable serializableArg = "foo";
        byte[] byteArrayArg = new byte[]{(byte) 0xbe, (byte) 0xef};
        short[] shortArrayArg = new short[]{100, 200};
        char[] charArrayArg = new char[]{'c', 'h', 'a', 'r'};
        float[] floatArrayArg = new float[]{2f, 3f, 4f};
        CharSequence[] charSequenceArrayArg = new CharSequence[]{"spam", "eggs"};

        LargeTestArguments input = new LargeTestArguments(
                byteArg, charArg,
                shortArg, floatArg,
                charSequenceArg, parcelableArg,
                parcelableArrayArg, parcelableListArg,
                integerArrayListArg, stringArrayListArg,
                serializableArg, byteArrayArg,
                shortArrayArg, charArrayArg,
                floatArrayArg, charSequenceArrayArg);

        Intent intent = new Intent();
        AutoBundle.packIntent(input, intent);

        LargeTestArguments output = new LargeTestArguments();
        AutoBundle.unpackIntent(intent, output);
        assertThat(output.byteArg, is(byteArg));
        assertThat(output.charArg, is(charArg));
        assertThat(output.shortArg, is(shortArg));
        assertThat(output.floatArg, is(floatArg));
        assertThat(output.charSequenceArg, is(charSequenceArg));
        assertThat(output.parcelableArg, is(parcelableArg));
        assertThat(output.parcelableArrayArg, is(parcelableArrayArg));
        assertThat(output.parcelableListArg, is(parcelableListArg));
        assertThat(output.integerArrayListArg, is(integerArrayListArg));
        assertThat(output.stringArrayListArg, is(stringArrayListArg));
        assertThat(output.serializableArg, is(serializableArg));
        assertThat(output.byteArrayArg, is(byteArrayArg));
        assertThat(output.shortArrayArg, is(shortArrayArg));
        assertThat(output.charArrayArg, is(charArrayArg));
        assertThat(output.floatArrayArg, is(floatArrayArg));
        assertThat(output.charSequenceArrayArg, is(charSequenceArrayArg));
    }
}
