package com.github.ajalt.autobundle;

@BundleArguments
public class TestBundleArguments {
    public static final String INT_ARG_KEY = "INT_ARG_KEY";

    private final String stringArg;

    @BundleArgument(key = INT_ARG_KEY)
    private final int intArg;

    public TestBundleArguments() {
        stringArg = null;
        intArg = 0;
    }

    public TestBundleArguments(String stringArg, int intArg) {
        this.stringArg = stringArg;
        this.intArg = intArg;
    }

    public String getStringArg() {
        return stringArg;
    }

    public int getIntArg() {
        return intArg;
    }
}
