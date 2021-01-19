package com.ipsidy.faceloktest;

import com.ipsidy.faceloksdk.*;

import android.graphics.Bitmap;
import android.util.Log;

private class FacelokCallback extends com.ipsidy.faceloksdk.FacelokCallback {
    @Override
    public void detectionComplete(Bitmap photo, String imageInfo) {
        mInterface.log(LoggerLevel.INFO,
                "Got a detection complete event with bmp size of " + (photo.getWidth() * photo.getHeight()));

        // we call reset to start over and start detecting faces again
        mEventIndex = 0;
        mInterface.reset();
    }

    @Override
    public void imageTooDark() {

    }

    @Override
    public void imageTooLight() {

    }

    @Override
    public void imageProcessed(FaceState face) {

    }

    @Override
    public void validFaceAngle(boolean valid, int angle) {
        if (valid) {
            mInterface.log(LoggerLevel.DEBUG, "face angle change to VALID, angle: " + angle);
        } else {
            mInterface.log(LoggerLevel.DEBUG, "face angle change to INVALID, angle: " + angle);
        }
    }

    @Override
    public void faceDetected() {
        mInterface.log(LoggerLevel.DEBUG, "Face Detected!");
    }

    @Override
    public void faceLost() {
        mInterface.log(LoggerLevel.DEBUG, "Face Lost!");
        mEventIndex = 0;
    }

    @Override
    public void livenessEvent(LivenessEvent event) {
        mInterface.log(LoggerLevel.DEBUG, "Liveness event signaled: " + event);
    }

    @Override
    public void status(boolean failure, String message) {
        mInterface.log(failure ? LoggerLevel.ERROR : LoggerLevel.INFO, message);

    }

    @Override
    public void cameraReady() {
        mInterface.log(LoggerLevel.INFO, "Camera ready");

        // Adding preview needs to be done after the camera is started
        mCameraView = new CameraPreview(getApplicationContext(), mInterface.getCamera(), mInterface);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        layout.addView(mCameraView);
    }

    @Override
    public void livenessEventComplete(boolean failure, FailureReason reason) {
        mInterface.log(LoggerLevel.INFO, "Liveness test complete, failed? " + failure);
        if (failure) {
            // reset to the first test
            mInterface.log(LoggerLevel.INFO, "Resetting to first liveness test due to failure");
            mEventIndex = 0;
        }

        // pass next test
        mInterface.pushLivenessEvent(mEvents.get(mEventIndex++));
    }

    @Override
    public void faceTooSmall() {
        mInterface.log(LoggerLevel.DEBUG, "Face lost because it was too small");
    }

    @Override
    public void imageGlare(boolean detected, GlareInfo info) {

    }

    @Override
    public void livenessFailure(FailureReason reason) {

    }
}
