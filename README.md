# AutoBundle [![jitpack](https://img.shields.io/github/release/ajalt/AutoBundle.svg?label=JitPack)](https://jitpack.io/#ajalt/AutoBundle/1.0)

Automated packing and unpacking of Android Bundles.

## Overview

Android's Bundle system works, but packing and unpacking the argument for
Bundles is tedious and repetitive. This library takes care of the boilerplate,
removing the need to manage `putExtra`, `getExtra`, and key values yourself.

## Installation

AutoBundle uses [jitpack](https://jitpack.io/) to host its builds. Add the
following to your gradle file to use AutoBundle:

```groovy
repositories {
       maven { url "https://jitpack.io" }
}

dependencies {
   compile 'com.github.ajalt:AutoBundle:1.0'
}
``` 

## Usage

AutoBundle is set up by annotating the fields of a class with the
`BundleArgument` annotation. You can also annotate a class with
`BundleArguments` to pack all fields in the class. Using it is this easy:

```java
// Annotate the fields of a class of any type with @BundleArgument
@BundleArguments
class Arguments {
    public String stringArg;
    public int intArg;

    // Constructors, etc...
}
```

Set the extras of an intent by passing the intent and an instance of the
arguments class to `AutoBundle.packIntent`:

```java
// Create an instance of the class, and pass it to one of the AutoBundle pack methods
public static Intent createIntent(Context context, Arguments args) {
    Intent intent = new Intent(context, SomeActivity.class);
    AutoBundle.packIntent(args, intent);
    return intent;
}
```

Then in the onCreate of the called Activity, unpack the intent with
`AutoBundle.unpackIntent`:

```java
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Arguments args = new Arguments();
    AutoBundle.unpackIntent(getIntent(), args);
	// do something with `args`...
}
```

That's it. The annotated fields in the `Arguments` instance will be populated
with the values sent in the Intent.

### Bundles outside of intents.

You can also use AutoBundle for Bundles that are not associated with an Intent
by calling `AutoBundle.packBundle` and `AutoBundle.unpackBundle`. For example,
with the `saveInstanceState` methods:

```java
public class MyActivity extends Activity {
    @BundleArgument
    private String savedField;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            AutoBundle.unpackBundle(savedInstanceState, this);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        AutoBundle.packBundle(this, outState);
    }
}
```

### Manually specifying keys

By default, the keys for Bundle arguments are generated automatically. This
works well if you have control of both packing and unpacking a Bundle. If you
want to send or receive a Bundle from other sources, you can can specify a
`key` parameter to `BundleArgument` to use specific key. Here's a translation
of [the standard email Intent](https://developer.android.com/guide/components/intents-common.html#Email) 
using AutoBundle:

```java
@BundleArgument(key=Intent.EXTRA_EMAIL)
private String[] addresses;

@BundleArgument(key=Intent.EXTRA_SUBJECT)
private String subject;

@BundleArgument(key=Intent.EXTRA_SUBJECT)
private Uri attachment;

public void composeEmail() {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("*/*");
    AutoBundle.packIntent(this, intent);
    startActivity(intent);
}
```

### Javadoc

[The full javadocs can be found here](https://jitpack.io/com/github/ajalt/AutoBundle/1.0/javadoc).

## License

    Copyright (c) 2015 AJ Alt


    Permission is hereby granted, free of charge, to any person obtaining
    a copy of this software and associated documentation files (the
    "Software"), to deal in the Software without restriction, including
    without limitation the rights to use, copy, modify, merge, publish,
    distribute, sublicense, and/or sell copies of the Software, and to
    permit persons to whom the Software is furnished to do so, subject to
    the following conditions:
    
    The above copyright notice and this permission notice shall be included
    in all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
    EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
    MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
    IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
    CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
    TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
    SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.