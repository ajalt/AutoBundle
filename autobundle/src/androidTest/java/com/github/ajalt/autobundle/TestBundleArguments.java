package com.github.ajalt.autobundle;

@BundleArguments
public class TestBundleArguments {
    public static final String INT_ARG_KEY = "INT_ARG_KEY";

    public String stringArg;

    @BundleArgument(key = INT_ARG_KEY)
    public int intArg;

    public TestBundleArguments() {}

    public TestBundleArguments(String stringArg, int intArg) {
        this.stringArg = stringArg;
        this.intArg = intArg;
    }
}
