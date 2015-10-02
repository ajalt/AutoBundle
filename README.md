# AutoBundle

Automated packing and unpacking of Android Bundles.

## Overview

Android's Bundle system works, but packing and unpacking the argument for
Bundles is tedious and repetitive. This library takes care of the boilerplate.

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
`BundleArgument` annotation. Using it is this easy:

```java

// Annotate the fields of a class of any type with @BundleArgument
class Arguments {
    @BundleArgument
    public String stringArg;

    @BundleArgument
    public int intArg;

    // Constructors, etc...
}

// Create an instance of the class, and pass it to one of the AutoBundle pack methods
public static Intent createIntent(Context context, Arguments args) {
    Intent intent = new Intent(context, SomeActivity.class);
    AutoBundle.packIntent(args, intent);
    return intent;
}
```

Then in the onCreate of the called Activity, unpack the intent:

```java
protected void onCreate(Bundle savedInstanceState) {
    // other set up...
    Arguments args = new Arguments();
    AutoBundle.unpackIntent(getIntent(), args);
}
```

That's it. The annotated fields in the `Arguments` instance will be populated
with the values sent in the Intent.

### Bundles outside of intents.

You can also use AutoBundle for Bundles that are not associated with an
Intent. For example, with the `saveInstanceState` methods:

```java
public class MyActivity extends Activity {
    @BundleArgument
    String savedField;

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

## License

    Copyright (c) 2015 AJ Alt


    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.