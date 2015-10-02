package com.github.ajalt.autobundle;

public class TestArguments {
    public static final String INT_ARG_KEY = "INT_ARG_KEY";
    @BundleArgument
    public String stringArg;

    @BundleArgument(key = INT_ARG_KEY)
    public int intArg;

    public TestArguments() {}

    public TestArguments(String stringArg, int intArg) {
        this.stringArg = stringArg;
        this.intArg = intArg;
    }
}
