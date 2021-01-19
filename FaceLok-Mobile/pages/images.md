![Ipsidy](../../images/ipsidy.png)
# FaceLok Mobile - Images

<!-- TOC -->
- [FaceLok Mobile - Images](#facelok-mobile---images)
  - [Image Conversions](#image-conversions)
    - [Android](#android)
    - [iOS](#ios)
  - [Image Rotation](#image-rotation)
    - [Android](#android-1)
    - [iOS](#ios-1)
<!-- /TOC -->

## Image Conversions

This section contains examples on converting the raw bytes in the binary image to a usable
format.  The [image](./interfaces.md#image) object describes the fields needed to rebuild the images.

There are platform specific images for each platform.  The conversions are only needed in
case you want to use the binary array and the data in [image](./interfaces.md#image) to convert to a usable image.
On android you will get a `Bitmap` and on iOS you will get a `UIImage` so the chances of needing
to convert these binary images is pretty low.  This section is included for completeness and to 
help understand the [image](./interfaces.md#image) and the conversions between native and C++ layers.

### Android

The android format is NV21 which is a type of YUV format.

This function will convert the raw bytes to a Bitmap.

```java
    public Bitmap convertRawImageToBitmap(byte[] nv21Image, int width, int height) {
        YuvImage yuvImage = new YuvImage(nv21Image, ImageFormat.NV21, width, height, null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, os);
        byte[] jpegByteArray = os.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(jpegByteArray, 0, jpegByteArray.length);

        return bitmap;
    }
```

### iOS

THe iOS format is kCIFormatBGRA8 or BGRA.

```objc
    CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
    
    CIImage *ciImage = [CIImage imageWithBitmapData: image.data 
        bytesPerRow: image.bytesPerRow 
        size: CGSizeMake(image.width, image.height) 
        format: image.format 
        colorSpace: colorSpace
        ];

    UIImage *uiImage = [[UIImage alloc] initWithCIImage: ciImage];
    [self detectionCompleteWithUIImage: uiImage];
    
    CGColorSpaceRelease(colorSpace);
    ciImage = nil;
```

## Image Rotation

As of FaceLok 1.1 images delivered via the FacelokCallback, see [callbacks](./callbacks.md), will be properly oriented.  They will no longer be in camera orientation and instead should be properly oriented for you.

### Android

For **android** the rotation value is passed in an Image record is calculated by dividing the
current angle by 90.  The final equation for rotation is:

`rotation = angle / 90`

Here are some definitions needed to understand the calculations:

| Name | Description |
| ---- | ----------- |
| degrees | Rotation of the window/device.  This will be 0, 90, 180, 270. |
| orientation | This is the CameraInfo orientation from the Camera. |
| angle | The calculation for this is based on camera.<br>Front facing: (orientation + degrees) % 360. <br>Rear facing: (orientation - degrees + 360) % 360. |

Note: % = Modulus.

The code for rotation calculations can be seen in the android [docs](https://developer.android.com/reference/android/hardware/Camera.html#setDisplayOrientation(int)).

Here is an example for rotating an image using the rotation value in `image`.

```java
    public Bitmap rotateImage(Bitmap bmp, int rotation) {
        int degree = 0;
        switch (rotation) {
            case Frame.ROTATION_0:
                degree = 0;
                break;

            case Frame.ROTATION_90:
                degree = 90;
                break;

            case Frame.ROTATION_180:
                degree = 180;
                break;

            case Frame.ROTATION_270:
                degree = 270;
                break;
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        return rotatedImg;
    }
```

### iOS

For **iOS** the rotation value in [image](./interfaces.md#image) is based off the EXIF values.  See the Apple documentation for [CGIImagePropertyOrientation](https://developer.apple.com/documentation/imageio/cgimagepropertyorientation?language=objc).

When you create a UIImage from [image](./interfaces.md#image) you can make sure that it gets the proper orientation by setting it like so:

```objc
-(UIImage *)convertImage:(nonull FLImage *)image {
    CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
    
    CIImage *ciImage = [CIImage imageWithBitmapData: image.data 
        bytesPerRow: image.bytesPerRow 
        size: CGSizeMake(image.width, image.height) 
        format: image.format 
        colorSpace: colorSpace
        ];

    UIImage *uiImage = [[UIImage alloc] initWithCIImage: ciImage
        scale: 1.0
        orientation: UIImageOrientationForCGImagePropertyOrientation(image.rotation)
        ];
    
    CGColorSpaceRelease(colorSpace);
    ciImage = nil;

    return uiImage;
}
```

Note that line with `orientation` in the `initWithCIImage`, that is the one that will allow the image to be displayed properly and work properly with other image processing systems.  Make sure you set that when going from FLImage to UIImage.

With the image created by that `convertImage` example function it will still display in it's incorrect rotation unless you fix it's rotation. 

To do this here is an example function (this came from a swift based test app, you will need to convert to objective-c if you use it in an obj-c based app):

```objc
   func fixImageOrientation(_ image: UIImage)->UIImage {       
        UIGraphicsBeginImageContext(image.size)
        image.draw(at: .zero)
        let newImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()                                 
        return newImage ?? image                                                                                                         
    }                       
```

Then when you set the UIImage in a UIView or whereever you plan on using it you would just call the `fixImageOrientation`.

Some of the callbacks will deliver native image formats, like UIImage on iOS.  These will always be delivered in kCGImagePropertyOrientationUp.

They should work in any view without having to be run through any rotation like the above function.  For example `detectionCompleteWithUIImage` will give a pre-rotated image in portrait up mode.

#

[Back to Main Page](../README.md)