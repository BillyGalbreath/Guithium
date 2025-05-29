---
title: Image
parent: Elements
nav_order: 5
has_toc: false
---

# Image

Image is an element to display a texture.

----

## Texture

This is the texture to draw. It can be a static PNG image or animated gif loaded from a namespace pointing to a minecraft (including modded) resource, or a url from the internet.

{: .note }
See the [Texture](../texture.html) documentation to learn more.

----

## Vertex Color

This controls how much of a texture's colors show through on the screen. See Mojang's [position texture color fragment shader](https://mcasset.cloud/1.21.5/assets/minecraft/shaders/core/position_tex_color.fsh) for details.

E.g., a modifier of `0xFF00FFFF` will remove all red from the texture.

{: .important }
Omitting the vertex color will default to `0xFFFFFFFF` (opaque white).

----

## TileModifier

This is used to divide the UV into tiled segments on each axis (repeat).

E.g., a value of 32 is used on vanilla option screens with the dirt texture in order to get the tiled effect. This does <em>not</em> mean it is tiled 32 times.

{: .important }
Omitting the tile modifier will default to `1.0` (no tile/repeat).

{: .note }
Examples:<br>
`1.0` makes the texture fit `1` times inside the allotted size, showing only the single image.<br>
`2.0` makes the texture fit `2` times inside the allotted size, showing 4 images tiled in a 2x2.<br>
`0.5` makes the texture fit `0.5` times inside the allotted size, showing the top left 25% of the image.

----

# Examples

## Basic Image

An example of a basic image

```java
Image image = Image.builder("my_plugin:github_logo")
    .setPos(0, 20)
    .setSize(50, 50)
    .setTexture(
       "my_plugin:github_logo_texture",
       "https://cdn-icons-png.flaticon.com/512/25/25231.png"
    )
    .build();
```

## Tiled Image

An example of a tiled image.

{: .note }
This Image is provided to you at `Image.TILED_DIRT` since it is a common Image (used in options screens).

```java
public static final Image TILED_DIRT = Image.builder("guithium:tiled_dirt")
        .setTexture(new Texture("minecraft:dirt", "textures/gui/options_background.png"))
        .setVertexColor(0xFF404040)
        .setTileModifier(32.0F)
        .build();
```
