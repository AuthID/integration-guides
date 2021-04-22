package com.ipsidy.faceloktest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

import com.ipsidy.faceloksdk.*;


public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 200;
    private CameraPreview mCameraView;
    private FacelokCallback mCallback;
    private FacelokImpl mInterface;
    ArrayList<LivenessEvent> mEvents;
    private int mEventIndex;

    private static String TAG = "faceloktest";

    static {
        System.loadLibrary("facelok");
    }

    private void createEvents() {
        mEventIndex = 0;
        mEvents = new ArrayList<>();
        mEvents.add(LivenessEvent.BLINK);
        mEvents.add(LivenessEvent.SMILE);
        mEvents.add(LivenessEvent.NONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create our callback
        mCallback = new FacelokCallback();

        // setup facelok
        mInterface = new FacelokImpl();
        mInterface.setActivity(this);
        mInterface.initialize();
        mInterface.setCallback(mCallback);

        // Custom liveness block
        //mInterface.useCustomLiveness(LivenessOrder.INORDER);
        //mInterface.pushLivenessEvent(LivenessEvent.BLINK);
        //mInterface.pushLivenessEvent(LivenessEvent.SMILE);
        //mInterface.pushLivenessEvent(LivenessEvent.BLINK);
        // end custom liveness

        // challenge-response liveness block
        //createEvents();
        //mInterface.useChallengeResponseLiveness();
        //mInterface.pushLivenessEvent(mEvents.get(mEventIndex++));
        // end challenge-response liveness block

        // make sure we have camera permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            mInterface.log(LoggerLevel.INFO, "Asking for camera permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CameraParameters params = new CameraParameters(CameraFacingEnum.FRONT, 30, 1024, 768);
        mInterface.start(params);
    }

    @Override
    public void onPause() {
        mInterface.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "DESTROYING!");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "STOPPED!");
        mInterface.stop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mInterface.log(LoggerLevel.INFO, "Got camera permission, starting camera");
                    recreate();
                }
                else {
                    mInterface.log(LoggerLevel.ERROR, "Could not get permission for camera");
                }
                return;
            }
        }
    }

    private class FacelokCallback extends com.ipsidy.faceloksdk.FacelokCallback {
        @Override
        public void detectionComplete(Bitmap photo, String imageInfo) {
            mInterface.log(LoggerLevel.INFO, "Got a detection complete event with bmp size of " + (photo.getWidth() * photo.getHeight()));

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
        public void validFaceAngle(boolean valid, int angle) {
            if (valid) {
                mInterface.log(LoggerLevel.DEBUG, "face angle change to VALID, angle: " + angle);
            }
            else {
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
    }
}
