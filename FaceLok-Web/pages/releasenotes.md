![Ipsidy](../../images/authid.png)
# Proof/Verified Web - Release Notes

<!-- TOC -->
- [Proof/Verified Web - Release Notes](#proofverified-web---release-notes)
  - [Version 1.10.0](#version-1100)
  - [Version 1.9.1](#version-191)
  - [Version 1.9.0](#version-190)
  - [Version 1.8.0](#version-180)
  - [Version 1.7.2](#version-172)
  - [Version 1.7.1](#version-171)
  - [Version 1.7.0](#version-170)
  - [Version 1.6.0](#version-160)
  - [Version 1.5.0](#version-150)
  - [Version 1.4.1](#version-141)
  - [Version 1.4.0](#version-140)
  - [Version 1.3.1](#version-131)
  - [Version 1.3.0](#version-130)
  - [Version 1.2.1](#version-121)
  - [Version 1.2.0](#version-120)
  - [Version 1.1.0](#version-110)
  - [Version 1.0.5](#version-105)
  - [Version 1.0.3](#version-103)
  - [Version 1.0.2](#version-102)
  - [Version 1.0.0](#version-100)
<!-- /TOC -->


## Version 1.10.0

03/Dec/20

Task

    [BW-269] - Web - Implement Client Authentication
    [BW-421] - Add diagnostic output data for tests
    [BW-512] - Do transaction validity check before showing consent page
    [BW-524] - ePassport flow
    [BW-525] - Deep Link Page
    [BW-541] - Use BioCredentialCheck2 for document crop quality check - CredentialSource = 1
    [BW-545] - Design API for iFrame integration
    [BW-550] - FLWA - Implement Client Authentication

Bug

    [BW-546] - BioWebApp requires "IntroductionText" variable in all Verified transactions
    [BW-548] - BWApp - Verify - Show custom "Verify Final Fail" page

Sub-task

    [BW-523] - Jaegar error
    [BW-549] - Migrate to Tensorflow 2.x
    [BW-552] - Add model and integrate into FLWA

## Version 1.9.1

05/Nov/20

Bug

    [BW-530] - BWApp - Scale document image if already scanned by WASM

## Version 1.9.0

03/Nov/20

Task

    [BW-38] - Setup versioning
    [BW-140] - Standardize api on biometricswebapi
    [BW-221] - Remove hardcoded version from biometricswebapp
    [BW-322] - BWApp & BWApi - Clean up versioning
    [BW-502] - BWApp - Chrome on Samsung devices crashes
    [BW-506] - BWApp - Make landscape required configurable per document side

Sub-task

    [BW-519] - Fix FLWA healthcheck not always reporting all active running nodes
    [BW-520] - Extend FLWA logging regarding key frame Redis updates
    [BW-521] - Fix Redis key update conflict causing stuck "processing"

## Version 1.8.0

08/Oct/20

Task

    [BW-486] - Add API terms link to releases
    [BW-489] - BWApp - Add QR code page for laptops where document scan failed
    [BW-493] - BWApp - Changes to UX for "Camera Resolution Too Low QRCode" page

Bug

    [BW-482] - On a Laptop, with an HP TrueVision HD Camera, I cannot complete the proof process
    [BW-491] - BWApp - MB Wasm timeout doesn't allow for a retry

## Version 1.7.2

30/Sep/20

Task

    [BW-485] - BWApp - Add QR code to minimum resolution error page

Story

    [BW-476] - Error after taking proof picture "needs" landscape mode

## Version 1.7.1

28/Sep/20

Task

    [BW-472] - BWApp - Remove the camera request on page load

Bug

    [BW-470] - Document scanning configuration seems to be ignored

## Version 1.7.0

26/Sep/20

Task

    [BW-193] - Reinvestigate caching issues with BWApp and PDS
    [BW-208] - Call IDComplete.checkBioCredential in FLWA (was: Sync IDLive min score values between FLWA and IDComplete)
    [BW-381] - Verify must allow "reject" flow
    [BW-431] - BWApp - Make fetchTimeout configurable per request
    [BW-468] - BWApp - Document image center bracket gif is misleading
    [BW-471] - BWApp - Integrate Passport support

## Version 1.6.0

18/Sep/20

Task

    [BW-438] - BWApp - Disable MicroBlink Wasm loading for Verify transactions
    [BW-454] - BWApp - What to do HealthCheck.php?
    [BW-462] - BWApp - Implement simple Google Analytics integration

Bug

    [BW-394] - BWApp - Document scans done in landscape mode

Sub-task

    [BW-419] - Benchmark running WebAssembly HotSpot/Glare algorithm in wasmtime/wasmer on the backend side
    [BW-420] - Recode algorithm in C/C++ using emscripten

Story

    [BW-366] - Leverage Tracker "attributes" mode to detect glasses/beards etc
    [BW-418] - Implement HotSpot/glare algorithm as WebAssembly module usable on front end (browser)
    [BW-439] - Change to image upload from postdata for WAF support
    [BW-458] - Enable OpenTelemetry and Jaeger tracing backend support

## Version 1.5.0

28/Aug/20

Task

    [BW-367] - BWApp - Add Liveness Test & Processing Timeout
    [BW-368] - BWApp - Add "Please Remove Glasses" message
    [BW-397] - Directory loader should verify jpeg files
    [BW-407] - BWApp - Integrate MicroBlink Wasm
    [BW-408] - BWApp - Performance - Web Server - Add "Content-Type: application/wasm"
    [BW-424] - Add face too close to border support
    [BW-426] - BWApp - Improve Verify support
    [BW-433] - Need to add user agent header for WAF
    [BW-442] - BWApp, BWApi - Convert image uploads to multipart so WAF likes it
    [BW-451] - BWApp - Investigate FullScreen mode

Bug

    [BW-278] - Information bubble changes too rapidly to see
    [BW-392] - Error with 2 FLWA instances on very fast network
    [BW-428] - BWApp - facelok.js - _imageSequenceKeyFrames[9] undefined
    [BW-432] - Need to be able to set content type in transaction
    [BW-461] - BWApp - Camera request doesn't show popup dialog while in fullscreen on Samsung Browser

Sub-task

    [BW-405] - BWApp - Add Liveness Message "FaceCloseToBorder"
    [BW-446] - Update Kestral server to feed BWApp files and also allow access to BWApi

Story

    [BW-443] - Remove default "null" values from FLWA JSON responses

## Version 1.4.1

24/Jul/20

Task

    [BW-390] - FLWA - KeyFrameRequest method doesn't seem to work anymore

Bug

    [BW-382] - BWApp - Network Quality Error page not showing after 410

## Version 1.4.0

23/Jul/20

Task

    [BW-103] - Add support for processing the back of documents and uploading to IDComplete
    [BW-270] - Add "Wait" on the top bar while waiting for "Smile" ptompt
    [BW-305] - BWApp - Add timeout to fetch calls
    [BW-310] - BWApp - Add debug output representation of keep[] and send[] images
    [BW-327] - BWApp - Add scores to debug KeyFrame image output
    [BW-328] - BWApp - Need customer friendly error messages
    [BW-332] - BWApp - StopVerification gets called twice on exception
    [BW-334] - SampleApp - Update Network and timeout errors in sdk app
    [BW-357] - BWApp - Integrate FLWA TooBright and TooDark messages
    [BW-362] - BWApp - facelok.js - Disable camera at Processing state

Bug

    [BW-311] - Firefox UI cut off for confirmation checkbox
    [BW-314] - Error taking selfie

Story

    [BW-323] - FLWA selfie image scoring improvement cycle part 1
    [BW-333] - Tracker recognition engine session affinity support
    [BW-351] - Detection of light blobs in the image to adjust selfie score and warn user on lighting conditions
    [BW-365] - Delta/baseline smile threshold for Tracker engine to improve smile recognition sensitivity

## Version 1.3.1

25/Jun/20

Task

    [BW-316] - Add scoring information to JSON

Story

    [BW-249] - Add liveness phase delays
    [BW-318] - Use tracker face angle in the image scoring
    [BW-321] - Optimize FLWA docker image size

## Version 1.3.0

23/Jun/20

Task

    [BW-215] - Make more user-friendly error page in place of the Debug Error Page
    [BW-277] - HealthCheck - BWApp, BWApi, PDS - Add 500 status code when error present
    [BW-287] - BWApp - Site redesign
    [BW-295] - BWApp - Remove WebRTC from facelok.js

Bug

    [BW-241] - "Take Document Photo" button in center of the video
    [BW-243] - Clicking "Take Document Photo" too quickly causes an empty document image screen
    [BW-245] - Integrate FLWA image sequence number with BWApp
    [BW-304] - FLWA - FaceLokJS interaction hangs at completed

Story

    [BW-274] - Image scoring system
    [BW-289] - Improve recognition quality

## Version 1.2.1

06/Jun/20

Bug

    [BW-243] - Clicking "Take Document Photo" too quickly causes an empty document image screen
    [BW-282] - Memory leak in facelokwebapp

## Version 1.2.0

04/Jun/20

Task

    [BW-91] - Use the OperationID url param when deciding which start page to show.
    [BW-136] - Make status check interval configurable
    [BW-276] - Support for Verify Lite transaction
    [BW-280] - Make sure all supported lite docTypes work for BWApi

Bug

    [BW-176] - Framerate drops very low when connection is throttled to Slow 3G
    [BW-279] - BWApp - Error out if network performance is unacceptable

## Version 1.1.0

26/May/20

Task

    [BW-252] - Add support for mulitple recognizer types for same image
    [BW-268] - stopVerification called twice
    [BW-275] - Add load balancer for FLWA 

Sub-task

    [BW-260] - /HealthCheck API in Bio WebApp

## Version 1.0.5

14/May/20

Task

    [BW-93] - Switch blink recognizer type based on Proof document request.
    [BW-251] - Selfie image submitted does not match one IDComplete received
    [BW-264] - Add Healthcheck API to BWApp, BWApi, and PDS

Bug

    [BW-240] - US DL document type hardcoded in BWApi

Sub-task

    [BW-258] - /HealthCheck API in FLWA

Story

    [BW-250] - Introduce "Processing" phase/screen at the end of liveness

## Version 1.0.3

07/May/20

Bug

    [BW-244] - FLWA freezes


## Version 1.0.2

06/May/20

Task

    [BW-219] - Concurrently send LowRes images and HighRes image

Bug

    [BW-213] - When backend services are unavailable facelokweb does not work
    [BW-238] - Some IOS+Safari devices don't show the SmileBar

## Version 1.0.0

01/May/20

    Initial public release


#

[Back to Main Page](../README.md)