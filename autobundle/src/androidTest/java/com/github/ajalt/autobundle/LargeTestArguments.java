package com.github.ajalt.autobundle;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LargeTestArguments {
    @BundleArgument
    public byte byteArg;
    @BundleArgument
    public char charArg;
    @BundleArgument
    public short shortArg;
    @BundleArgument
    public float floatArg;
    @BundleArgument
    public CharSequence charSequenceArg;
    @BundleArgument
    public Parcelable parcelableArg;
    @BundleArgument
    public Parcelable[] parcelableArrayArg;
    @BundleArgument
    public List<Parcelable> parcelableListArg;
    @BundleArgument
    public ArrayList<Integer> integerArrayListArg;
    @BundleArgument
    public ArrayList<String> stringArrayListArg;
    @BundleArgument
    public Serializable serializableArg;
    @BundleArgument
    public byte[] byteArrayArg;
    @BundleArgument
    public short[] shortArrayArg;
    @BundleArgument
    public char[] charArrayArg;
    @BundleArgument
    public float[] floatArrayArg;
    @BundleArgument
    public CharSequence[] charSequenceArrayArg;

    public LargeTestArguments() {}

    public LargeTestArguments(byte byteArg,
                              char charArg,
                              short shortArg,
                              float floatArg,
                              CharSequence charSequenceArg,
                              Parcelable parcelableArg,
                              Parcelable[] parcelableArrayArg,
                              List<Parcelable> parcelableListArg,
                              ArrayList<Integer> integerArrayListArg,
                              ArrayList<String> stringArrayListArg,
                              Serializable serializableArg,
                              byte[] byteArrayArg,
                              short[] shortArrayArg,
                              char[] charArrayArg,
                              float[] floatArrayArg,
                              CharSequence[] charSequenceArrayArg) {
        this.byteArg = byteArg;
        this.charArg = charArg;
        this.shortArg = shortArg;
        this.floatArg = floatArg;
        this.charSequenceArg = charSequenceArg;
        this.parcelableArg = parcelableArg;
        this.parcelableArrayArg = parcelableArrayArg;
        this.parcelableListArg = parcelableListArg;
        this.integerArrayListArg = integerArrayListArg;
        this.stringArrayListArg = stringArrayListArg;
        this.serializableArg = serializableArg;
        this.byteArrayArg = byteArrayArg;
        this.shortArrayArg = shortArrayArg;
        this.charArrayArg = charArrayArg;
        this.floatArrayArg = floatArrayArg;
        this.charSequenceArrayArg = charSequenceArrayArg;
    }
}
