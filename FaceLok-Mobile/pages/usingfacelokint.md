![Ipsidy](../../images/ipsidy.png)
# FaceLok Mobile - Using the FaceLok Interface

This section covers using FacelokInterface in your project.  This is the main entry point
into the SDK.  It controls all the functionality and the states of the statemachine.  It
needs a [Logger](./interfaces.md#logger), [FaceDetect](./interfaces.md#face-detect), and an [EventCallback](./interfaces.md#event-callback) object to be initialized.

You should have your project configured and building already.  If not please see the [project setup](./projectsetup.md) page.

## Android

There are a couple ways to do liveness now.  In this example we use the default.  You can uncomment
either the *Custom liveness block* or the *challenge response liveness block* to use either of those methods.
You can only use 1 or the other though.  If you uncomment both it will use whichever was set last.

If you use CHALLENGERESPONSE liveness, you can modify the `createEvents()` function to change which
events the app is looking for and in what order.

More information on liveness style can be found on the [liveness page](./livenesstesting.md) as well as the [interfaces](./interfaces.md) page.

Example code:

@include MainActivity.java
[Example Java Code](../examples/MainActivity.java).

## iOS

[Example Objective-C Code](../examples/ViewController.m).

For callback example see [callbacks](./callbacks.md).

#

[Back to Main Page](../README.md)