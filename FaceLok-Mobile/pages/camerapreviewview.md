![Ipsidy](../../images/authid.png)
# FaceLok Mobile - Camera Preview View

If you want to display preview images from the camera, you will need to connect a view
that is capable of showing the images.  In iOS this is easy, but in android it is a bit
more complicated.

### iOS

To show a preview view in iOS all you have to do is connect a UIView.  An example of this
is seen in [Using the Interface](./usingfacelokint.md).

Here is an excerpt from that:

```objc
mInterface = [FLFacelokImpl alloc];
[mInterface initialize];

// this is the line you need to connect your UIView (mPreviewView) to the interface
[mInterface setPreviewView: mPreviewView];
```

### Android

To show a preview in android, you will need to create a custom preview view.  Then you
will need to register that view with the interface.

One thing to note here is that you will have to set the preview window **after** you
start the camera.  If you set it before you start the camera it will be overwritten
with a dummy buffer and you will need to set it again.

[Here](../examples/CameraPreview.java) we have a CameraPreview view implementation.

Then in our [MainActivity](../examples/MainActivity.java) we set the preview view.

#

[Back to Main Page](../README.md)
