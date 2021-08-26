![Ipsidy](../../images/authid.png)
# Proof/Verified Web - Bioweb Application I-Frame Integration

<!-- TOC -->
- [Proof/Verified Web - Bioweb Application I-Frame Integration](#proofverified-web---bioweb-application-i-frame-integration)
  - [Introduction](#introduction)
  - [BIOWEB_IFRAME_MESSAGE_OBJECT](#bioweb_iframe_message_object)
  - [Example](#example)
<!-- /TOC -->

## Introduction

Facelok.js can function independently of the html-based Bioweb App provided by Ipsidy. But, many integrators will opt to include the Bioweb App because it simplifies their integration needs greatly. The Bioweb app has full support for Ipsidy Proof and Verify transactions.

Integrators that choose this route may decide to integrate the Bioweb App as an IFrame. This section is an outline of the requirements for this type of integration.

The Bioweb app will notify the parent frame when it reaches what is called an “end_page”. Using the parent.postMessage call it will pass simple messages back to the parent frame. The message will have a value set for the “success” variable and a value for the “pageName” variable. The iframeTest.html gives a very simple example.

## BIOWEB_IFRAME_MESSAGE_OBJECT

| Value | Description |
| ----- | ----------- |
| success | true or false.<br> A true value indicates that the Bioweb Proof or Verify transaction finished successfully. Note - The pageName should always be “verifiedPage”.<br> A false value indicates that the Proof or Verify transaction either isn’t finished yet, or finished unsuccessfully. |
| pageName | This is the page name that the app has currently reached. Much of the time the page name is quite descriptive enough to ascertain the reason for the message. Note that this doesn’t mean that the transaction is finished yet.<br> Here’s a list of pages that signal that the Bioweb App is finished. Note – The only page that will result in “success” being true will be the “verifiedPage”.<br> defaultFailedPage - (final page) - A failure occurred that is not currently handled for.<br> docScanResolutionTooLowPage – Proof only - (final page) - The camera resolution is too low to complete the Proof transaction.<br> docScanWasmTimeoutPage – Proof only - (not a final page) - The barcode scan timed out.<br> documentFailedNonMobilePage – Proof only – (not a final page) - The document scan failed and the user is not on a mobile device.<br> documentFailedPage – Proof only – (not a final page) - The document scan failed for some reason.<br> livenessErrorPage – (not a final page) - An error occurred during the Liveness Test.<br> networkErrorPage – (not a final page) - Network throughput was insufficient for the Liveness Test to function properly.<br> QRCodePage - (final page) - The user has opted to move this transaction to their phone. Note - This QR Code link will be outside of the IFrame, so may have to be handled as the QR Code will not include the IFrame url.<br> requestTimeoutPage - (not a final page) - A network request took longer then acceptable.<br> standardErrorPage - (final page) - A misc error occurred. While in debug mode this page will show instead of the other more specific pages in this list.<br> transactionNotValidPage - (final page) - The transaction is no longer valid. Most commonly the transaction has already been finished.<br> transactionMaxAttemptsPage - (final page) - The max attempts count for the transaction has been reached.                                <br> verifiedMatchFailPage – Verify only - (final page) - The Verify attempt failed to match the user to their stored Ipsidy biometric image.<br> verifiedPage - (final page) - The transaction has finished successfully.<br> verifyDeclinedPage – Verify only - (final page) - The customer declined the Verify transaction.<br> videoDeviceNotFoundPage - (final page) - No camera was detected.


## Example

Here is an example of the Bioweb iframe in action.

`iframetest.html:`
```html
<html>
<head>
<meta name="viewport" content="initial-scale=1,user-scalable=no">
<style>
    #theFrame{
        position: absolute;
        top: 0px;
        left: 0px;
        width: 100%;
        height: 100%;
    }

    #thePage{
        text-align: center;
    }

    #theMessage, #theHeader{
        text-align: center;
    }

    .hidden{
        display: none;
    }
</style>
</head>
<body>
<div id="thePage" class="hidden">
<div id="theHeader"></div>
<div id="theMessage"></div>
</div>
<iframe id="theFrame" allow="fullscreen *;camera *;"></iframe>
<script>
(function() {
    window.addEventListener("message", (event) => {
        console.log(JSON.stringify(event.data))

        if(event.data.success)
            document.querySelector("#theHeader").innerText = "Passed"
        else{
            switch(event.data.pageName){
                case "documentFailedPage":
                case "documentFailedNonMobilePage":
                case "networkErrorPage":
                case "livenessErrorPage":
                case "docScanWasmTimeoutPage":
                case "requestTimeoutPage":
                //We don't want to stop the BWApp for these pages
                    return
                case "transactionNotValidPage":
                case "transactionMaxAttemptsPage":
                case "QRCodePage":
                case "verifiedMatchFailPage":
                case "verifyDeclinedPage":
                case "docScanResolutionTooLowPage":
                case "videoDeviceNotFoundPage":
                case "standardErrorPage":
                case "defaultFailedPage":
                    break
            }
            document.querySelector("#theHeader").innerText = "Failed"
        }
    document.querySelector("#theMessage").innerText = event.data.pageName

    document.querySelector("#theFrame").style.display = "none"
    document.querySelector("#thePage").style.display = "block"
}, false);


document.getElementById("theFrame").setAttribute("src","index.html" + window.location.search)
})();
</script>
</body>
</html>
```

#

[Back to Main Page](../README.md)