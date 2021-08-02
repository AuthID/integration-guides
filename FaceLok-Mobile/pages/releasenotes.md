![Ipsidy](../../images/ipsidy.png)
# FaceLok Mobile - Release Notes

<!-- TOC -->
- [FaceLok Mobile - Release Notes](#facelok-mobile---release-notes)
  - [Version 1.8.0](#version-180)
  - [Version 1.7.0](#version-170)
  - [Version 1.6.0](#version-160)
  - [Version 1.5.3](#version-153)
  - [Version 1.5.2](#version-152)
  - [Version 1.5.1](#version-151)
  - [Version 1.5.0](#version-150)
  - [Version 1.4.3](#version-143)
  - [Version 1.4.2](#version-142)
  - [Version 1.4.1](#version-141)
  - [Version 1.4.0](#version-140)
  - [Version 1.3.1](#version-131)
  - [Version 1.3.0](#version-130)
  - [Version 1.2.3](#version-123)
  - [Version 1.2.2](#version-122)
  - [Version 1.2.1](#version-121)
  - [Version 1.2.0](#version-120)
  - [Version 1.1](#version-11)
  - [Version 1.0](#version-10)
<!-- /TOC -->

## Version 1.8.0

XX/Aug/21

| Note |
| :=== |
| This release contains some potentially breaking changes for compiliation of your existing <= 1.7 code.  These will be marked below in the changelog with a **(B)**. |

Changes:

    (B) Raised required Android SDK level to 21+.
    (B) Changed android callback class.  All callbacks are now abstract and are required to be defined in your derived callback class.
    (B) Callback class requires an Activity passed to it's constructor to handle threading.
    Added warning if requested resolution is too low for a good liveness test.  Recommended size is now 1280x720.
    Added logic to flip request resolution if it is given in portrait sizes instead of the default camera sizes (landscape).
    Added 2 new interface calls for getting additional selfies from FaceLok.
        scoredImage() and scoredImageCount() are used to get other lower scoring selfie images from FaceLok.
        The image with index 0 will be the best selfie that was delivered at the end of the liveness process.
        At this time FaceLok only stores 5 selfie images total, all can be obtained via scoredImage().
    Added 2 new interface calls for configuring FaceLok
        getConfiguration() and setConfiguration()
    Removed Doxygen style documentation.  All docs are now on github in our Integration Guides project.
    Updated iOS example in documentation as it was out of date.

**Required Android SDK Level Change**

In anticipation of moving from Camera to Camera X support for android we have raised the minimum SDK level from android-15 to android-21.  The new camera support will be coming in a future version.

**Deprecation Warning:**

The following calls relating to checking image quality with IDComplete backend will be deprecated in the next release.  They will continue to work in 1.8.0 but will be removed to stub functions in 1.9.0.  They will still be callable to not break existing code, however they will return without running the check and report a failure to run the check in the resulting JSON.

    checkingImageQuality callback will be stubbed out in the next version
    checkPhotoQuality interface will be stubbed out in the next version

Please update your code to not use these functions and instead go directly through your IDComplete interface to check image quality.

By removing these calls we will also be removing the dependency on Poco and OpenSSL which will significantly decrease the framework size, speed up compilation times, and reduce complexity of adding the framework to your apps.


## Version 1.7.0

18/Dec/20

Changes:

    Removed OpenCV as a dependency. This shrinks the library by a very significant amount.

Deprecated Calls:

The following calls are related to checking image quality with the IDComplete back end. This functionality is no longer required since it should be done at the application level using a transaction id with the IDC system. These functions will be removed in a near future version.

    checkingImageQuality callback will be removed in a future version
    checkPhotoQuality interface will be removed in a future version

We no longer use OpenCV so the following calls have been removed. Please stop using them they will be removed in a future version.

    opencvVersion()
    opencvBuildInfo()

## Version 1.6.0

28/Oct/20

Changes:

    Made 250 pixel face the minimum acceptable size. This is the size of the face in the image and not the overall image. Anything smaller than that will be reported via the faceTooSmall() callback and the liveness process reset.
    WARNING: If the face is too small right from the start it will never send the faceTooSmall message because it will consider the face as non-detected in the first place. This limitation will be addressed in a future version but is worthy of a note here.
    Added FaceTooSmall and FaceTooCloseToBorder to the FaceState object. These are devlivered via the imageProcessed() callback. Currently this is Android only.
    Clarified some sections in the documentation, including noting version on newly introduced items.


## Version 1.5.3

02/Oct/20

Changes:

    Added terms and conditions link in the documentation as well as a root level README
    Updated examples in the documentation, removed some deprecated callback references, etc

Bugs:

    Fixed OpenCV-less version so it runs properly without OpenCV on iOS and Android
    Fixed a bug where quality check was reporting as being run and failing when using FaceLok without opencv


## Version 1.5.2

12/May/20

Features:

    Added support for picking image size in iOS (NOTE: if you choose an invalid resolution the camera will fail to start. At this time there is no per-device checking of compatible resolutions. This must be done application side. The next release will have checks for each device in FaceLok itself).
    Fixes image size value in parameters passed to FaceLok's start. This image size is honored now and a resolution closest to the requested size will be chosen.


## Version 1.5.1

30/Nov/19

Bugs:

    Fixed a bug where image was not being delivered properly to detectionComplete.


## Version 1.5.0

22/Nov/19

Features:

    Added FPS and other metrics for ios and android
    Refactored some internal code
    Added image quality checks and a new interface for doing those checks (see interface changes)
        The best selfie at the end of the process will be sent for quality checks
        There is also an interface function that can be called manually
    Added a livenessFailure callback to notify the user when a liveness test fails
    Decreased size of opencv for android

Interface Changes:

    Added checkPhotoQuality interface call for checking an image for good quality for the back end.
    Added livenessFailure callback to report on any event that fails liveness. This can be used to detect multiple faces, lost face, shaking camera, etc. See Failure Reason.
    Changed framesProcessed function to no longer take a boolean reset variable. Reset is automatically tracked now.
    Changed detectionComplete, now gives image and a json string with further details on the quality of the image.
    Added checkingImageQuality callback that is called to let the app know when the liveness is complete and FaceLok is testing the image quality with the back end. During this call it would be good to show a "verifying" message to the user. When this is complete or times out detectionComplete is called.


## Version 1.4.3

18/Sep/19

Bugs:

    Fixed a bug where livenessEvent was not being fired in some cases if using default mode or ANYORDER in custom mode.


## Version 1.4.2

11/Sep/19

Bugs:

    Fixed a bug where a delay in challenge-response liveness event (NONE) caused the callback for detectionComplete to not be called


## Version 1.4.1

09/Sep/19

Bugs:

    Fixed a bug with LG based phones showing darker than expected images from the camera
    Tightened up android glare detection values so there should be less false positives


## Version 1.4.0

8/Sep/19

Features:

    Added glare detection (beta) for detecting glare in images

Interface Changes:

    Added imageGlare callback to report on glare detection


## Version 1.3.1

30/Aug/19

Bugs:

    Added movement detection to test for camera shaking
    Decreased minimum ratio for face size to help prevent face being lost for being too small on some devices

Interface Changes:

    Added face_velocity to LandmarkType (see Debugging)
    Added CameraShaking to Failure Reason


## Version 1.3.0

27/Aug/19

Features:

    Added vertical nod Liveness Event
    Added horizontal shake Liveness Event
    Added head tilt Liveness Event
    Added liveness event callback which signals when an event is complete in all liveness modes
    Added OpenCV for image processing

Bugs:

    Partial faces are no longer processed and will result in a face lost and liveness reset

Interface Changes:

    Removed enableDebugCallbacks() from Facelok
    Removed DebugInfo
    Removed setImageCallbacks from Event Callbacks
    Added getLastImage to Facelok
    Added landmarks to Face State
    Removed debugImage from Event Callbacks
    Added imageProcessed to Event Callbacks
    Removed liveness_state record
    Removed livenessStateChanged callback
    Added livenessEvent callback, see Event Callbacks


## Version 1.2.3

15/Aug/19

Interface Changes:

    Added faceTooSmall to Event Callbacks
    Updated example in Callbacks

Bugs:

    Fixed a bug with liveness when the detected face was small compared to the overall image size

## Version 1.2.2

13/Aug/19

Features:

    Selfie processing system:
        Captures higher quality selfies
        Makes sure face angle is low
        Scores eyes wider open higher
        Scores mouth "less smiling" as higher

Interface Changes:

    Added CameraSwitched to FailureReason, see Failure Reason

Bugs:

    Fixed a bug where switching the camera from front to back would not reset liveness check properly

## Version 1.2.1

29/Jul/19

Features:

    More than 1 face in an image is now blocked. If more than one face is in the image liveness is reset and it is reported via callback.

Interface Changes:

    New enum Failure Reason has been added
    livenessEventComplete callback has been modified to include Failure Reason

## Version 1.2.0

24/Jul/19

Features:

    Overhaul and upgrade to the liveness detection system
    Liveness now supports 3 styles, default, challenge-response, and custom.
        Default uses the previous method of a blink and a smile in any order.
        Custom can be used to set up a list of events and an order of processing for those events.
        Challenge-Response can be used to ask for a specific liveness event and when that is complete a new event can be provided. Events that are not the current one will cause a failure.
    Documentation updates

Deprecated:

    livenessstate is being deprecated in this version
    The livenessStateChanged() callback is being remove in this version since it doesn't fit with the new liveness system

Interface Changes:

    Due to liveness system changes, livenessStateChanged callback does not report liveness changes any more and will be removed
    Added livenessEventComplete callback for challenge-response style liveness tests
    Added Liveness Order
    Added Liveness Style
    Added Liveness Event
    Added interface functions
        useChallengeResponseLiveness (used to start a challenge-response liveness test)
        useDefaultLiveness (used to reset to the default liveness testing)
        useCustomLiveness (used to initiate a custom liveness test)
        pushLivenessEvent (used in conjunction with challenge-response and custom liveness to add liveness tests)

Bug Fixes:

    Fix for older samsung devices camera not opening properly

## Version 1.1

13/Jul/19

Features:

    Document image conversion
    Liveness enhancements
    Add callback for face angle
    Add new callback interface
    Dummy callback messages
    Reset liveness when angle is invalid
    Update debuginfo interface
    Add cameraready event for attaching preview window
    Document cameraview
    Describe rotation field in image
    Code examples in documentation
    Removed opencv as a dependency (for now)
    Updated native images provided via FacelokCallback to be in the proper orientation for display with not extra work required

Bug Fixes:

    LED ceiling light causing liveness problem


## Version 1.0

13/Jun/19

    Initial Release

#

[Back to Main Page](../README.md)