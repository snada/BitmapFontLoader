# Bitmap Font Loader

[![Build Status](https://travis-ci.org/snada/BitmapFontLoader.svg?branch=master)](https://travis-ci.org/snada/BitmapFontLoader) [![Issue Count](https://codeclimate.com/github/snada/BitmapFontLoader/badges/issue_count.svg)](https://codeclimate.com/github/snada/BitmapFontLoader)  [ ![Download](https://api.bintray.com/packages/snada/maven/bitmapfontloader/images/download.svg) ](https://bintray.com/snada/maven/bitmapfontloader/_latestVersion)

Java classes to load bitmap fonts generated by [AngelCode Bitmap Font Generator](http://www.angelcode.com/products/bmfont/) with a simple and minimal API.

Also included are classes to handle 3D rendering in an Android OpenGL app, as well as an example app.

## Install

Go to you app's gradle file and edit your dependencies:

```
dependencies {
    ...
    compile 'it.snada:bitmapfontloader:1.0.0'
}
```

Simply do a gradle sync and you're ready to code.

## Modules

This code contains 2 modules:

- app
- bitmapfontloader

**app** contains a simple OpenGL app to see the code running. It compiles to an Android app rendering a sample 3D text rotating.

Module **bitmapfontloader** includes 2 different packages:

- it.snada.bitmap3dstring
- it.snada bitmapfontloader

## Packages

### bitmapfontloader

This package contains the actual font loader with classes that abstract fonts, chars, and settings.

To load a font, create a font instance, then pass it to the loader together with an InputStream to the font file (not the bitmap texture):

```java
font = new BitmapFont();
AngelCodeXmlLoader.load(font, getResources().openRawResource(R.raw.arial));
```

The previous example works with Android resources: if you are outside the Android world, simply pass an InputStream.

### bitmap3dstring

This package contains a list of support class to easily perform 3D rendering of 3D strings using textures provided by the program and just 2 triangles.

If you need geometry, get that with the singleton class Bitmap3DGeometry:

```java
geometry = Bitmap3DGeometry.getInstance();

//This object contains geometry, normals and indices information to use in render
geometry.getNormalBuffer();
geometry.getVertexBuffer();
geometry.getIndexBuffer();
```

Also, if you need vertices color, there's an helper for that too:

```java
//This will create color information (RGBA, within 0 and 1) equal for every vertex on the quad
//There's another constructor to specify information on every single vertex
Bitmap3DColor color = new Bitmap3DColor(1.0f, 0.5f, 0.3f, 1.0f);
```

Create an instance of a 3D string by passing the BitmapFont and the actual string you want to render.

```java
Bitmap3DString string = new Bitmap3DString(font, "Hello!");
```

Apply every transformation you desire with provided methods:

```java
// Use this methods to set position, scale and rotation
string.setPositionX(1.0f);

//If you wish to set scale according to given width/height, helper methods are there too
string.setXScaleByPreferredWidth(1.0f);

//...and remember to update other dimensions as well...!
string.setScaleY(string.getScaleX());
```

If you need, you can cycle to the list of chars inside this 3D string and apply specific transforms.

Now you have everything you need to start rendering:

```java
for (Bitmap3DChar chr : string.get3dChars()) {
  //Every char contains a buffer with UV texture coordinates
  chr.getUvBuffer();

  //Every char contains a model matrix with every transformtaion, also the ones applied at string level
  chr.getModelMatrix();

  //Use this as vertex buffer
  geometry.getVertexBuffer();

  //Use this as index buffer
  geometry.getIndexBuffer();

  //Use this as color buffer
  color.getColorBuffer()
}

```

Everything will be taken care for you, spacing and kernings included.

Take a look at the Android sample app for more detail.

## Contributing

Contributions are more than welcome.

This projects uses [git-flow](https://danielkummer.github.io/git-flow-cheatsheet/) branching model, using Github issue ids as feature names.
