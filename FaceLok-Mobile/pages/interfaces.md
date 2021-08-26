![Ipsidy](../../images/authid.png)
# FaceLok Mobile - Interfaces

<!-- TOC -->
- [FaceLok Mobile - Interfaces](#facelok-mobile---interfaces)
  - [Version](#version)
  - [Logger Level](#logger-level)
  - [Camera Parameters](#camera-parameters)
  - [Facelok](#facelok)
  - [Face State](#face-state)
  - [Image](#image)
  - [Event Callbacks](#event-callbacks)
  - [Failure Reason](#failure-reason)
  - [Liveness Order](#liveness-order)
  - [Liveness Event](#liveness-event)
  - [Liveness Style](#liveness-style)
  - [Debugging](#debugging)
    - [Point](#point)
    - [Rect](#rect)
    - [LandmarkType](#landmarktype)
  - [Glare Info](#glare-info)
  - [OS Type](#os-type)
<!-- /TOC -->

## Version

The Version interface is an object that gives version information about the SDK and the opencv
that is being used.

It is C++ so it has a static create method to be constructed.

| Function | Return | Notes |
| -------- | ------ | ----- |
| create() | Version object | Static method that will create a version object |
| versionString() | string | Version of the SDK i.e. 1.0.0 |
| major() | int32 | major version i.e. 1 |
| minor() | int32 | minor version i.e. 0 |
| patch() | int32 | patch or maintenance version i.e. 0 |
| opencvBuildInfo() | string | **DEPRECATED in 1.7.0** Opencv information about how it was built.  Large string. |
| opencvVersion() | string | **DEPRECATED in 1.7.0** Opencv version info |

**Usage:**

Java:
```java
    Version v = Version.create();
    Log.i(TAG, "FacelokSDK v" + v.versionString());
```

Objective-C:
```objc
    Version *v = [FLVersion create];
    NSLog(@"%@", [NSString stringWithFormat:@"FacelokSDK v%@", [v versionString]]);
```

## Logger Level

This is an enum that holds the values for logging.  It is used with the Facelock.log() method.

Note, the logging is iOS is done via NSLog and not with an actual logger at this time.  This
means that it will not be easily filtered.  So DEBUG messages for instance will always log if
they are used.

| Android Level | iOS Level | Notes |
| ------------- | --------- | ----- |
| DEBUG | FLLoggerLevelDebug | Debug level |
| INFO | FLLoggerLevelInfo | Information level |
| WARN | FLLoggerLevelWarn | Warning level |
| ERROR | FLLoggerLevelError | Error level |

See Facelok interface for usage examples.

## Camera Parameters

This is a record that is used to hold the parameters that are sent to the Facelok system to
initialize the camera.  The camera_facing_enum is a enum that has camera settings.

camera_facing_enum:
| Enum Android | Enum iOS | Notes |
| ------------ | -------- | ----- |
| FRONT | FLCameraFacingEnumFront | Front facing camera |
| BACK | FLCameraFacingEnumBack | Back facing camera |

camera_parameters:
| Parameter | Type | Notes |
| --------- | ---- | ----- |
| facing | camera_facing_enum | Camera facing that is desired, i.e. front, back.  Check enum for values |
| preferredFPS | int32 | The preferred fps for the camera.  This will be limited/calculated based on what the camera supports and the resolution.  <br>This is why it's labeled preferred. |
| previewWidth | int32 | Width of the preview image, i.e. 1024.  This will be adjusted by the Facelok system as necessary. |
| previewHeight | int32 | Height of the preview image, i.e. 768.  This will be adjusted by the Facelok system as necessary. |

See Facelok interface for usage examples.

## Facelok

This is the main interface into the SDK.  Most of what you will need to use the SDK will be in this
object.

You must call `initialize()` before any other functions.  With the exception of on android where you
will need a call to `setActivity()` before `initialized()`.   When done with the system calling
`shutdown()` will release all memory and stop all processing and the camera.

| Function | Return | Platform | Notes |
| -------- | ------ | -------- | ----- |
| setActivity(act: Activity) | void | android | Call this to set your activity to be used for context by Facelok. |
| setPreviewView(view: UIView *) | void | iOS | Call this to set the view that will receive the preview camera frames. |
| initialize() | void | all | Call this to initialize Facelok.  Calling other functions before this will throw exceptions. |
| shutdown() | void | all | Call this to stop everything and shutdown the Facelok system.  <br>initialize() will need to be called again if you want to restart it. |
| setCallback(callback: event_callback) | void | all | This function allows you to set an event callback.  <br>This is an object you create derived from EventCallback or FLEventCallback. |
| start(params: camera_parameters) | void | all | Start the Facelok system with the supplied camera parameters. <br>You will need access to the camera for this.  Recommended minimum resolution to request in params is 720p (1280x720).  Going below this may have negative impact on the backend liveness testing with IDComplete. |
| framesProcessed() | int32 | all | Gives the number of frames processed in the last second. <br>This can be used with a timer to get FPS processed. |
| log(level: logger_level, message: string) | void | all | Logs a message at a particular `logger_level` using the Facelok logger.  <br>Messages logged this way will show up with the tag "facelok (ext)". <br>The internal logged message will show as "facelok". |
| stop() | void | all | Stop the camera and the preview.  Does nothing if `start()` was not called first. |
| reset() | void | all | Once a face has been detected and liveness has passed the system will enter a state where it is no longer processing images.  <br>To reset it to continue processing a new image call this function. |
| useChallengeResponseLiveness() | void | all | This will set your [livness style](#liveness-style) as CHALLENGERESPONSE |
| useCustomLiveness(order: liveness_order) | void | all | This will set your [liveness style](#liveness-style) as CUSTOM. <br>You will need to add your liveness tests with `pushLivenessEvent()`. |
| useDefaultLiveness() | void | all | This sets your [liveness style](#liveness-style) back to DEFAULT. |
| pushLivenessEvent(event: liveness_event) | void | all | Pushes a [liveness event](#liveness-event) onto the liveness stack to be tested based on the [liveness order](#liveness-order).  <br>This is only used in CUSTOM and CHALLENGERESPONSE liveness styles. |
| getLastImage() | [Image](#image) | all | Used for getting the last image sent to the system for debugging purposes. |
| setOS() | void | all | Sets [OS type](#os-type) so the interface knows what platform it is running on. |
| scoredImageCount() | int32 | all | **Added in 1.8.0** Returns the number of scored images that FaceLok has.<br>This can should be used with the scoredImage() call to tell it which image you want. |
| scoredImage(imageIndex: int32) | [Image](#image) | all | **Added in 1.8.0** Call this to get the desired Image from FaceLok.<br>Get the number of images store with the scoredImageCount() function call.  These images will be in highest to lowest scored order.<br>Image with index 0 will be the one that was delivered as the best selfie at the end of the liveness process.<br>Image at index 1 will be the 2nd best scored selfie, and so on.<br>If you call this function while liveness is running the images can and will change depending on the scores they get.  Keep this in mind when using this call before liveness has completed. |
| checkPhotoQuality(image: image) | bool | all | **DEPRECATED in 1.7.0** Tests an image quality to see if the backend can identify the face and process it properly.<br>This tests for a good image to send to the backend but does not check whether the biometrics will pass or not. <br>Returns true if good quality, false if not. |

**Note:** Any change in liveness style will reset the liveness system.

You can find examples of using the interface on the [Using FaceLok Interface](./usingfacelokint.md) page.

## Face State

This record is used to supply the status of a face and it's facial features.  It is used in [debugging](#debugging), and will be in future callbacks.

| Parameter | Type | Notes |
| --------- | ---- | ----- |
| faceDetected | bool | True if a face is detected, false if not.  If no face, the rest of the data is considered invalid. |
| rightEyeClosed | bool | True if the right eye is closed, false if open. |
| leftEyeClosed | bool | True if the left eye is closed, false if open. |
| smiling | bool | True if the mouth is smiling, false if not. |
| faceAngle | int | The angle of the detected face. |
| landmarks | map<landmark_type, point> | This map holds all the landmark data.  See debugging section for landmark_types. |
| faceTooSmall | bool | Introduced in v1.6.  True if the face is too small for processing.  Note this is only sent on a single frame and after that the face is considered lost and faceLost callback is sent. |
| faceTooCloseToBorder | bool | Introduced in v1.6. True if the face is obstructed by the border.  This will also be true if the face is too close to the border of the image for proper processing. |

## Image

This record will hold binary image data and the information needed to build the image into a native format.

| Parameter | Type | Notes |
| --------- | ---- | ----- |
| data | binary array | This contains the raw bytes for the image. |
| width | int32 | The width of the image. |
| height | int32 | The height of the image. |
| bytesPerRow | int32 | The number of bytes per row.  Used to reconstruct the image in iOS, ignored in android. |
| format | int32 | The format the image data is in.  <br><b>iOS:</b> CoreImage format (CIFormat) <br><b>Android:</b> Currently should only ever be ImageFormat.NV21. |
| rotation | int32 | The orientation or rotation of the image. See image_rotation on [images](./images.md) page. |
| mirrored | bool | Whether the image is mirrored or not.  True if mirrored. |

Example of reconstructing image can be see on the [images](./images.md) page.

## Event Callbacks

This is an abstract interface that will need to be implemented by the user in order to receive callbacks from the sdk
to their application.

| Function | Return | Notes |
| -------- | ------ | ----- |
| imageTooDark() | void | Called when the image is too dark. <br><b>Not currently implemented in this version.</b> |
| imageTooLight() | void | Called when the image is too light. <br><b>Not currently implemented in this version.</b> |
| faceDetected() | void | Called when a face is detected.  Does not get called again unless the face is lost. |
| faceLost() | void | Called when the face is lost.  Does not get called again until a face is found and then lost. |
| livenessEvent(event: liveness_event) | void | Called when a liveness event occurs.  Event parameter will contain which event was completed successfully. <br>This is called in all liveness modes. |
| detectionComplete(image: image, imageInfo: string) | void | Called when the system has completed detection, liveness testing, and image quality check.  <br>The image parameter will contain the image to be used for back end processing.  <br>This image will be a UIImage on ios and a Bitmap on android. <br>The imageInfo parameter will contain a json object with the following fields:<br> "qualityPassed": boolean - true if passed, false if not<br> "qualityCheckRun": boolean - true if check was successfully completed |
| checkingImageQuality(previewImage: image, imageInfo: string) | void | **DEPRECATED in 1.7.0** Called when the system is done with liveness testing and moving on to checking the quality of the selfie.<br>When receiving this callback it would be good to show the user some feedback like a verifying image quality screen or spinner.<br>previewImage is the image that is being checked.<br>imageinfo is a json string containing the following:<br>"timeout": int - timeout on the back end image check in seconds |
| status(failure: bool, message: string) | void | Called with any miscellanous status. <br>failure: true is the status is an error/failure. <br>message: string containing the status message. |
| validFaceAngle(valid: bool) | void | Called when a detected face has an angle change from valid to invalid. <br>This is only called if there is a detected face and the angle goes in or out of a valid threshold. 
| cameraReady() | void | Called when the camera is started and ready to provide frames.  <br>This is mostly used in Android to attach a preview view which needs to wait on camera start before attaching. |
| livenessEventComplete(failure: bool, reason: failure_reason) | void | Called when a liveness test is complete.  Failure is true if the test failed. <br>If the test was a failure and you are using CHALLENGERESPONSE mode you should reset your tests and start over.<br>This function is called with a non-failure during CHALLENGERESPONSE tests to signify a completion of a test and tell you to pass your next test. <br> **NOTE**: this callback is only called if using CHALLENGERESPONSE [liveness style](#liveness-style). <br>`reason` will be a [failure reason](#failure-reason) indicating why a failure happened. |
| imageProcessed(face: face_state) | void | Called after every image is processed and gives a [face state](#face-state). |
| faceTooSmall | void | Called when the face was detected but it was too small for good processing.  <br>This makes it easier to do coach marks or inform the user to move the camera closer. |
| imageGlare(detected: bool, info: glare_info) | void | Called when glare is detected (or lost) from the images.<br>If detected is true, there is significant glare in the image.  False means there is no significant glare.<br>Extra information is available in the [glare info](#glare-info). |
| livenessFailure(reason: failure_reason) | void | Called when a failure is detected during a liveness check. <br>This is called in all modes but is redundant in CHALLENGERESPONSE mode since you will get a livenessEventComplete() with the same info. <br>You will never see a SUCCESS or INCORRECTLIVENESSEVENT since those are specific to CHALLENGERESPONSE mode. |

Example of creating a callback object, see [callbacks](./callbacks.md).

## Failure Reason

This enum holds information on failures for liveness events.  It is used in the `livenessEventComplete` callback.

| Enum Android | Enum iOS | Notes |
| ------------ | -------- | ----- |
| SUCCESS | FLFailureReasonSuccess | Indicates no failure, test was successful |
| UNSPECIFIEDFAILURE | FLFailureReasonUnspecifiedFailure | Failure reason was generic or unknown. |
| LOSTFACE | FLFailureReasonLostFace | The face was no longer detected, this resets liveness testing. |
| BADFACEANGLE | FLFailureReasonBadFaceAngle | The face is at a bad angle and not facing the camera straight on. <br>This causes a liveness reset. |
| MULTIPLEFACESDETECTED | FLFailureReasonMultipleFacesDetected | More than 1 face was detected in the image.  <br>Multiple faces are not allowed, this is a failure and resets liveness. |
| INCORRECTLIVENESSEVENT | FLFailureReasonIncorrectLivenessEvent | The failure was caused by an out of order event. <br>For example if a blinked was tracked while waiting for a smile.  <br>Only applies to INORDER processing. |
| FORCEDRESET | FLFailureReasonForcedReset | The system was reset by a request from the user (or the SDK resetting itself). |
| CAMERASWITCHED | FLFailureReasonCameraSwitched | The camera system was stopped and the camera was changed.  <br>This causes a reset to liveness and selfie processing. |
| CAMERASHAKING | FLFailureReasonCameraShaking | The camera was moving too much.  This will reset liveness and lose the face. |

## Liveness Order

This enum is used to determine the order that liveness events should be checked.  This applies to the CUSTOM
liveness style.  DEFAULT style uses ANYORDER and CHALLENGERESPONSE style only has 1 item at a time, but essentially
uses INORDER as it will fail a test if it gets something other than the expected test.

| Enum Android | Enum iOS | Notes |
| ------------ | -------- | ----- |
| INORDER | FLLivenessOrderInOrder | Denotes that the testing should be done in order.  Any event received out of order will cause a failure.<br>For example if you set a blink and a smile, and you receive a smile first it will fail. |
| RANDOM | FLLivenessOrderRandom | **Not Impletemented**: This order is not currently supported and will be implemented in a future version.<br>This order will pick a [liveness event](#liveness-event) at random from the list as the current.<br>Any event other than the active one will cause a failure. |
| ANYORDER | FLLivenessOrderAnyOrder | This order will allow any [liveness event](#liveness-event) in the list to be done in any order.<br>For example, if you have a blink and smile event, either one can be performed at any time. |

Examples of liveness styles can be seen on the [liveness](./livenesstesting.md) page.

## Liveness Event

This enum is used to define an event for the `pushLivenessEvent()` function.  It is used with CUSTOM and CHALLENGERESPONSE
[liveness style](#liveness-style).

| Enum Android | Enum iOS | Notes |
| ------------ | -------- | ----- |
| NONE | FLLivenessEventNone | This event shold be passed only when using CHALLENGERESPONSE and only when you are done sending tests.<br>This tells the liveness system you are done testing liveness and for it to send you a `detectionComplete` event. |
| BLINK | FLLivenessEventBlink | This event tells the liveness system you want to get the face to blink. <br>It accepts a blink from either eye.<br>A full blink open/close/open is required to register as a blink. |
| SMILE | FLLivenessEventSmile | This event tells the liveness system you want to get a smile from the face. <br>A smile consists of a non-smile->smile->non-smile. |
| VERTICALNOD | FLLivenessEventVerticalNod | This event tells the liveness system you want the user to nod their head like saying yes. |
| HORIZONTALSHAKE | FLLivenessEventHorizontalShake | This event tells the liveness system you want the user to shake their head like saying no. |
| ZTILT | FLLivenessEventZTilt | This event tells the livenese system you to get a head tilt from shoulder to shoulder. <br>Laying your head to your shoulder is considered a z tilt. |

Examples of liveness styles can be seen on the [liveness](./livenesstesting.md) page.

## Liveness Style

This enum is used to select the style of liveness testing you want to use.  Only one style can be used at a time.
This is used internally in the C++ interface.  As the user you can use the *use* functions from the mobile
interface to set your liveness style.  See [facelok interface](#facelok) for the functions that set liveness style.

| Enum Android | Enum iOS | Notes |
| ------------ | -------- | ----- |
| DEFAULT | FLLivenessStyleDefault | Uses the default liveness testing method which is a blink and a smile in any order. |
| CHALLENGERESPONSE | FLLivenessStyleChallengeResponse | This liveness testing style expects you to pass whatever [liveness event](#liveness-event) you want each step.<br>When you set this you should use the `pushLivenessEvent()` function to push your first test.<br>When the callback `livenessEventComplete()` is called you should pass your next test if it didn't fail, or reset and pass your first test again if it did fail.<br>This is the most secure test style because you can mix it up and send as many tests as you'd like.<br>Any [liveness event](#liveness-event) other than the expected test will result in a failure. |
| CUSTOM | FLLivenessStyleCustom | Lets you pass a group of events and set a [liveness order](#liveness-order) for your liveness verification.<br>See [liveness order](#liveness-order) for information on how order works as it pertains mostly to this style. <br>You need at least 1 test, but can have as many total as you want. <br>For example, you could do blink-smile-blink-smile-blink-blink.  And have that done INORDER which will cause a failure if it isn't in order. |

Examples of liveness styles can be seen on the [liveness](./livenesstesting.md) page.

## Debugging

The following records are used for the debug callback.  In the future `point` and `rect` may be used for other things.

### Point

An x, y point pair.

| Parameter | Type | Notes |
| --------- | ---- | ----- |
| x | int | X position |
| y | int | Y position |


### Rect

This struct represents a rectangle.

| Parameter | Type | Notes |
| --------- | ---- | ----- |
| x | int | X position |
| y | int | Y position |
| width | int | Width of the rectangle |
| height | int | Height of the rectangle |

### LandmarkType

This is an enum that tells what type a landmark is.  Landmarks are in [face state](#face-state) which is delivered via the *imageProcessed* callback.

**NOTE:** Currently, all landmarks are empty on iOS.  This will be addressed in a future version.

| Enum Android | Enum iOS | Notes |
| ------------ | -------- | ----- |
| FACE_ORIGIN | FLLandmarkTypeFaceOrigin | **Android only** - Top left corner of the detected face. |
| FACE_SIZE | FLLandmarkTypeFaceSize | **Android only** - The width and height of the face. |
| FACE_TRACKING | FLLandmarkTypeFaceTracking | **Android only** - Integer id of the face from the tracking system.  This is set in the X value. |
| LEFT_EYE | FLLandmarkTypeLeftEye | **Android only** - The position of the left eye. |
| RIGHT_EYE | FLLandmarkTypeRightEye | **Android only** -  The position of the right eye. |
| MOUTH | FLLandmarkTypeMouth | **Android only** -  The position of the mouth. |
| ANGLE_Y | FLLandmarkTypeAngleY | **Android only** - Face angle on Y axis. | 
| ANGLE_X | FLLandmarkTypeAngleX | **Android only** - Face angle on X axis. |
| IMAGE_SIZE | FLLandmarkTypeImageSize | **Android only** - The size of the entire image. |
| IMAGE_MIRRORED | FLLandmarkTypeImageMirrored | **Android only** - Whether or not the image is mirrored.  This typically occurs on front facing cameras. x value in the [Point](#point) will be 1 for mirrored 0 for not. |
| IMAGE_ROTATION | FLLandmarkTypeImageRotation | **Android only** - Rotation/orientation of the image.  The possible values here are described in image_rotation on the [images](./images.md) page. |
| IMAGE_MS | FLLandmarkTypeImageMs | **Android only** - Timestamp in milliseconds when this face was captured.  This is used internally for tracking velocity of face movement.  It is set in the X of the [Point](#point). |
| FACE_VELOCITY | FLLandmarkTypeFaceVelocity | **Android only** -  Speed in which camera/face are moving |

## Glare Info

This record holds information on a glare event that is signalled via the imageGlare() callback.  See [callbacks](./callbacks.md).

**NOTE**: This struct will be empty in the 1.4 release and will be implemented later.  It is here so the interface doesn't change later.

| Parameter | Type | Notes |
| --------- | ---- | ----- |
| location | Rect | **NOT AVAILABLE YET** The bounding rect for the most prominent detected glare. |

## OS Type

This enum is used internally to determine what OS the system is running on.

| Enum Android | Enum iOS | Notes |
| ------------ | -------- | ----- |
| OS_DESKTOP | FLOsTypeOSDESKTOP | Desktop is the default os type.  The interfaces for ios and android should set their respective platform. |
| OS_IOS | FLOsTypeOSIOS | Set when running on iOS |
| OS_ANDROID | FLOsTypeOSANDROID | Set when running on android |

#

[Back to Main Page](../README.md)