![Ipsidy](../../images/authid.png)
# Proof/Verified Web - Release Notes

<!-- TOC -->
- [Proof/Verified Web - Release Notes](#proofverified-web---release-notes)
  - [Version 2.0.0](#version-200)
  - [Version 1.18.2](#version-1182)
  - [Version 1.18.1](#version-1181)
  - [Version 1.18.0](#version-1180)
  - [Version 1.17.0](#version-1170)
  - [Version 1.16.0](#version-1160)
  - [Version 1.15.0](#version-1150)
  - [Version 1.14.0](#version-1140)
  - [Version 1.13.0](#version-1130)
  - [Version 1.11.1](#version-1111)
  - [Version 1.12.0](#version-1120)
  - [Version 1.11.0](#version-1110)
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

## Version 2.0.0

Task

    [BW-945] - Make liveness events more steady
    [BW-971] - DocScan naming for doc images issue.
    [BW-997] - Revise "contact AuthID" message

Bug

    [BW-1002] - Docscan can pass document validation in MrzFocused mode when there is no actual barcode detected

Story

    [BW-942] - Transactions are hitting timeouts frequently in DEV
    [BW-944] - Implement k8s style health probes
    [BW-970] - add overlay for horizontal scanning front / back showing that document MUST be fully visible with edges not cut off


## Version 1.18.2

19/Aug/21

Task

    [BW-973] - Classify Tablets as Mobile devices from UX standpoint.

Bug

    [BW-972] - The ePassport QR code page does not support customization parameters - logo, colors


## Version 1.18.1

14/Aug/21

Bug

    [BW-965] - Certain sequence of 2-sided document process allows to complete Proof with front side only.

Story

    [BW-968] - URGENT: Orphaned Redis connections are causing issues


## Version 1.18.0

09/Aug/21

Task

    [BW-902] - Update the message in unsupported browser screen.
    [BW-922] - Configure TPN to match Docscan South African ID attributes
    [BW-926] - Regular dependency maintenance

Bug

    [BW-923] - Docscan API VS DocScan via web flow.
    [BW-927] - End of Verify/Proof is missing redirection support via custom form
    [BW-936] - ID Entry workflow (South African ID) not functional.

Story

    [BW-372] - Face too close or far away
    [BW-463] - Proof Web Flow Screen Changes -ALL proof types
    [BW-542] - Undecided: Homepage should be more user friendly
    [BW-838] - authID branding updates
    [BW-867] - Enhance our logging and troubleshooting capabilities in BioWebApp
    [BW-875] - "Exceptions config" capabilities for DocScan
    [BW-891] - Once QR code is shown, monitor mobile progress through sessions and show the same end screen
    [BW-928] - Add "nc" parameter to disable use of camera for document scanning on Desktop


## Version 1.17.0

19/Jul/21

Task

    [BW-869] - Add several new derived attributes to the docscan output.
    [BW-893] - Change "Scan your Image" for "Take Your Selfie"
    [BW-894] - Consent to the processor collecting and transmitting the information
    [BW-895] - Auto-rotate is enabled
    [BW-896] - Allow additional customization for Proof and Verified
    [BW-898] - Adjust the "Take Document Photo" button location parameters according to the size of the screen.
    [BW-900] - Redirect to a customer’s application

Bug

    [BW-733] - If present, ghost image from back of the document overwrites ghost image from front.
    [BW-844] - NG: the first camera permission prompt exit fullscreen
    [BW-862] - Docscan South African ID attributes missing
    [BW-870] - NG: missing waiting screen after selfie in Proof and Verify
    [BW-871] - NG Inteface - final "Submit" does not have a wait indicator.
    [BW-874] - Altra CU - Minnesota DL Issues

Story

    [BW-767] - Stateful Operation processing - continue from the last checkpoint
    [BW-864] - Support IDLive Meta tag for better spoofing detection error rates.
    [BW-865] - Build Kubernetes solution with Helm & Scaffold
    [BW-890] - Re-use previously uploaded front side image with back image (also affects stateful sessions)


## Version 1.16.0

25/Jun/21

Task

    [BW-484] - BWApp - Show more info about camera on Camera Resolution Error page
    [BW-500] - Verify Document request updated in backend
    [BW-572] - Screen rotation lock confuses customers
    [BW-781] - Hard code versions in build of container
    [BW-782] - Obfuscation of bioweb components inside container images
    [BW-810] - Change dependency logic in HealthCheck API
    [BW-814] - Prepare on premises containers
    [BW-817] - Enable SeriLog AppInsights integration

Bug

    [BW-320] - Connection Too Slow reported when FLWA is down
    [BW-571] - Once the URL transaction is delivered by SMS, the Browser does not recognize the camera and the transaction can not be performed
    [BW-809] - When trying to scan the Passport from the Desktop, an invalid or expired ID error is displayed (This is intermittent)
    [BW-843] - NG: Fullscreen handling leads to exception with older WebKit API
    [BW-861] - Unknown document types are not processed correctly in NG UI.

Sub-task

    [BW-765] - Add docscansvc json to IdComplete proof transaction RawData field
    [BW-811] - Change HealthCheck dependency logic in FLWA
    [BW-813] - Change HealthCheck dependency logic in BioWebApp
    [BW-859] - Migrate BioWebApp to the new version of FrontEnd API and pass selfie source data.

Story

    [BW-764] - Enable FullAuth mode in docscansvc and return advanced patten matching image information
    [BW-808] - Create new validation mode InfoExtraction in DocScanSvc
    [BW-816] - Docscansvc web API stress tests and optimizations
    [BW-837] - Restore ID Number docType 14 support


## Version 1.15.0

28/May/21

Task

    [BW-780] - Move containers to redhat ubi-minimal

Bug

    [BW-785] - DocScanSvc - driver license should not pass in MrzFocused mode when barcode on the back is empty

Story

    [BW-653] - Research React.js implementation of BWApp
    [BW-786] - Update Grafana Loki runtime dependencies


## Version 1.14.0

14/May/21

Bug

    [BW-641] - US DL scan flow fails on attempt to collect selfie on Android Note 8
    [BW-766] - DocScanSvc healthcheck not checking REST dependencies


## Version 1.13.0

12/May/21

Task

    [BW-748] - Override for BiometricsConfig.json using transaction details

Sub-task

    [BW-736] - Capture camera duration metrics in diagnostic portal

Story

    [BW-749] - Make JPG compression/quality level configurable and review default setting
    [BW-750] - Make unsmile/calm state of facelokweb configurable


## Version 1.11.1

19/Mar/21

Task

    [BW-598] - Implement ExecForm processing for Proof / Verified.

Bug

    [BW-593] - Incorrect CID parameter should revert to default Ipsidy logo


## Version 1.12.0

05/Mar/21

Task

    [BW-490] - BWApp - WASM - Green rectangle from drawQuad doesn't scale right sometimes
    [BW-531] - Update WASM module to 5.10.1
    [BW-535] - Check and update dependencies in npm
    [BW-536] - Check dependencies in biowebapp
    [BW-537] - Check and update dependencies in .NET
    [BW-595] - BWApp - FACELOK_VERSION string replace needs to be in facelok.js
    [BW-600] - Secure Test APIs
    [BW-616] - BWApp - mobile.html version is not working after webpack
    [BW-624] - Use MB WASM for all documents type to enhance UX for document capturing.
    [BW-655] - Add BPSmart logo to next release.
    [BW-702] - Update web flow to provide explicit user feedback on expired document
    [BW-734] - Only rescan front side of the document under certain conditions.

Bug

    [BW-592] - BioWeb landing screen - "restart" should not be an option for missing transaction keys
    [BW-601] - FaceLokWebApp can lock in processing stage due to network error
    [BW-670] - Proof transaction cannot be performed on Iphone8 IOS13.3.1 and Iphone11 IOS13
    [BW-687] - Facelokwebapp /api/healthcheck JSON nodes field grows over time
    [BW-732] - The customer logo (CID URL parameter) is not processed correctly on "camera not found" error.
    [BW-745] - Change to use counter metrics instead of gauges

Sub-task

    [BW-566] - facelok.js - Refactor - Add ESLint check to build
    [BW-589] - Expose prometheus metrics in docscansvc
    [BW-596] - Synchronize Redis stream implementation between DocScan and FaceLok
    [BW-606] - BWApi, BWApp updates to support Regula validation
    [BW-615] - Check docscansvc regonition status and detected document name in biowebapp
    [BW-621] - Add Loki__Url logs pushing for biowebapp
    [BW-622] - Fix biowebapp writing frontend/JS logs to console instead of configured logger
    [BW-623] - Fix biowebapp using duplicated Dockerfile (DEV vs regular)
    [BW-625] - Add Loki__Url for facelokwebapp
    [BW-640] - Switch to OTLP Jaeger
    [BW-642] - Create prompts to indicate if front or back of the document is expected.
    [BW-643] - WASM Passport Scan - Android Note 8 UI
    [BW-645] - If document not defined in BiometricsConfig, fail the web flow immediately.
    [BW-646] - Provide cropped images of documents and additional images returned by Regula to IDComplete
    [BW-649] - Add "Test One Side"/"Test Two Side" document types into Portal/Biowebapp for testing
    [BW-703] - Fix IssuingStateOrOgranization typo in scanned document JSON
    [BW-714] - Docscansvc not verifying document type due to object initialization order
    [BW-729] - Relax Regula ImageQA:invalid document status
    [BW-737] - Capture camera duration metrics in Prometheus
    [BW-738] - Move Fields stream column to separate key
    [BW-739] - Allow configurable MRZ to Visual text field distance to reduce scan failure rates
    [BW-740] - Fix New York/enhanced driver license was not accepter as driver license
    [BW-744] - Add new biowebconfig.json document parameter making full document validation optional

Story

    [BW-557] - facelok.js - Refactor Code
    [BW-585] - Modernize frontends with use of Webpack
    [BW-588] - Docscansvc service & Regula document validation
    [BW-594] - Extend FLWA prometheus endpoint with liveness/session specific counters
    [BW-620] - Grafana/Loki logging finalization for Azure env
    [BW-742] - WASM scanning is missing full-screen on mobile
    [BW-743] - Implement legacy split-mode scanning for US DL


## Version 1.11.0

11/Feb/21

Task

    [BW-567] - Implement Support for Verified NeedBiometry=false
    [BW-568] - Add documentation to FaceLok.js (JSDoc)
    [BW-573] - BWApp - Add Logging library log4javascript, forward logs to BWApi
    [BW-579] - Display QR code instead of or in addition to videoDeviceNotFound error page.
    [BW-583] - BWApp - Only allow Safari on IOS and Chrome on Android

Sub-task

    [BW-558] - facelok.js - Refactor - Remove technique code
    [BW-564] - facelok.js - Refactor - Fix ESLint issues

Story

    [BW-554] - Switch to Redis for FLWA signaling from Kafka streams


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