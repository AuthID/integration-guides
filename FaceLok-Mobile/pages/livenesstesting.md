![Ipsidy](../../images/authid.png)
# FaceLok Mobile - Liveness Testing

<!-- TOC -->
- [FaceLok Mobile - Liveness Testing](#facelok-mobile---liveness-testing)
  - [Introduction to Liveness Testing](#introduction-to-liveness-testing)
    - [Default](#default)
    - [Challenge-Response](#challenge-response)
      - [After Detection Complete](#after-detection-complete)
    - [Custom](#custom)
  - [Using These Methods](#using-these-methods)
    - [Default](#default-1)
    - [Custom](#custom-1)
    - [Challenge-Response](#challenge-response-1)
  - [Conclusion](#conclusion)
<!-- /TOC -->

## Introduction to Liveness Testing

There are multiple supported ways to do liveness testing in FaceLok at this time.  They are:

- Default
- Challenge Response
- Custom

| Note |
| :--- |
| If you are using the IDComplete backend for submitting the final photo, you will want to make sure your requested resolution for the call to `start()` is at least 720p (1280x720).  This is the minimum recommended resolution. |

### Default

The default method is the same as previous versions (<1.2) of FaceLok SDK.  This basically requires
a blink and a smile in any order at any time from the user.

### Challenge-Response

The challenge-response system is made to be an advanced way of liveness testing.  This should be used
when the most security and proof of liveness is needed.  To use this method you will pass in [liveness_event](./interfaces.md#liveness-event) objects that you want processed.  Any deviation from the active event will cause a liveness failure.

When a test is complete a callback `livenessEventComplete(failure)` will be called.  If failure is true
then the event failed.  At this point you should reset your liveness testing (re-randomize it if you're doing
random orders) and then set a test to be started again.

There is a lot you can do with failures using this method.  For instance, if you were securing something 
of large importance you could only allow a few failures before needing an extra method of verification.

**NOTE**: if you do this you must differentiate between liveness event failures and failures because
of an invalid face angle or a lost face.  Both of those events will cause a failure.  You will receive a
`faceLost` callback when the face is lost.  You will also receive a `validFaceAngle` callback when the face
angle changes from valid to invalid and vice-versa.

When you are done passing events for testing you will pass a NONE event in order to signal that you are 
done and require no more liveness tests.  This will cause the SDK to report liveness as complete and
provide your app with the `detectionComplete` callback.

#### After Detection Complete

If you intend to use the liveness system after dection has been completed you will need to call reset
as before on the interface.  This will reset the liveness system to the last state it was in.  If this
is CHALLEGENRESPONSE then there will be no tests.  You will need to push a test again after detectionComplete
in order for it to work after that.

### Custom

The custom liveness style is used to provide a list of [events](./interfaces.md#liveness-event) and an [order](./interfaces.md#liveness-order). The SDK will then use the events and order to do the liveness.

For instance you could provide a blink, a smile, and a blink and set the order to ANYORDER.  This would require
2 blinks and a smile in any order.

You could also specify INORDER in which case it would require a blink, then a smile, then another blink.  If
any events appear out of order it will fail.

There is no limit to the number of events you can use for a custom style.  You should pass all your events
before starting liveness testing.

## Using These Methods

### Default

To use the default method you don't have to do anything.  No special settings are required.  If you have set a
different liveness style you can go back to default by calling `useDefaultLiveness()`.

The `pushLivenessEvent` function is not compatible with the default style and is ignored.

### Custom

TO use the custom method you will setup all your liveness tests by calling `pushLivenessEvent` with a 
[liveness_event](./interfaces.md#liveness-event).  You can call this as many times as you'd like to create your list of liveness tests.

Once that is complete you can set the SDK to use this style with a call to `useCustomLiveness`.  You can tell it
what [liveness_order](./interfaces.md#liveness-order) to use for your custom tests at that time.

Example code (java):

```java
    mInterface.useCustomLiveness(LivenessOrder.INORDER);
    mInterface.pushLivenessEvent(LivenessEvent.SMILE);
    mInterface.pushLivenessEvent(LivenessEvent.BLINK);
    mInterface.pushLivenessEvent(LivenessEvent.SMILE);
    mInterface.pushLivenessEvent(LivenessEvent.BLINK);
```

You will not receive `livenessEventComplete` notificatons for custom test events.  This function is for
CHALLEGENRESPONSE only.  If a test fails on a custom style it will just reset and run all the tests again.
When tests are successful you will get a `detectionComplete` callback.

### Challenge-Response

The challenge-response method is used by passing events one at a time to the system.  To set this up you
will need a list of events that you want and you will need to manage the order in which you pass them to the 
system.  This can be randomized by you to keep liveness as secure as possible.  Random order should be reset
any time the liveness fails and is reset.

After creating your list of events you will set this method with a call to `useChallengeResponseLiveness()`.
After you call that you should push your first test with `pushLivenessEvent`.

Here is an example of this (java):

```java
    mEventIndex = 0;
    mEvents = new ArrayList<>();
    mEvents.add(LivenessEvent.BLINK);
    mEvents.add(LivenessEvent.SMILE);
    mEvents.add(LivenessEvent.NONE);

    mInterface.useChallengeResponseLiveness();
    mInterface.pushLivenessEvent(mEvents.get(mEventIndex++));
```

When a test is completed either with a success or a failure you will get a callback `livenessEventComplete(bool failure)`.
If you have a failure here you should reset your test list (and randomize again if you're doing that) and pass 
a test to start on next.
If successful, you should pass the next test in your list.
When you are on your last test that is successful, you need to pass a NONE event to let the SDK know that you
are done sending tests and that it should finish the liveness.  At this point the SDK should send a 
`detectionComplete` callback.

livenessEventComplete example (java):

```java
    mInterface.log(LoggerLevel.INFO, "Liveness test complete, failed? " + failure);
    if (failure) {
        // reset to the first test
        mInterface.log(LoggerLevel.INFO, "Resetting to first liveness test due to failure");
        mEventIndex = 0;
    }

    // pass next test
    mInterface.pushLivenessEvent(mEvents.get(mEventIndex++));

```

Note that if you get a failure of a test it could be because of an invalid face angle or face lost event.  If
either of these happen you will of course need to reset your list and start over.  You will get a notification
that your test has failed via the `livenessEventComplete` callback.

## Conclusion

You can find a full example of this on the [Using FaceLok Interface](./usingfacelokint.md) page.

#

[Back to Main Page](../README.md)