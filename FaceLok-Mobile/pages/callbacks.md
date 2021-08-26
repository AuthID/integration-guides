![Ipsidy](../../images/authid.png)
# FaceLok Mobile - Callbacks

The SDK will call callback function during events.  The current callbacks are described in [event_callbacks](./interfaces.md#event-callbacks).

## Implementing Callbacks in Android

[Example Java Code](../examples/FacelokCallback.java).

## Implementing Callbacks in iOS

On iOS you can implement a separate class like below, or add the FacelokCallback as part of your
overrides on your main/view class.

[Example Objective-C Callback Header](../examples/FacelokCallback.h).

[Example Objective-C Callback Implementation](../examples/FacelokCallback.mm).

#

[Back to Main Page](../README.md)