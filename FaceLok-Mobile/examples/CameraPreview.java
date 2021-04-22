package com.ipsidy.faceloktest;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "faceloktest";
    private Camera mCamera = null;
    private SurfaceHolder mHolder;
    private FacelokImpl mFacelok;

    public CameraPreview(Context context, Camera cam, FacelokImpl facelok) {
        super(context);

        mFacelok = facelok;

        mCamera = cam;
        Log.d(TAG, "CameraPreview: Created ... camera is: " + cam);

        mHolder = getHolder();
        mHolder.addCallback(this);

        // used for android versions < 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        startPreview();
    }

    private void startPreview() {
        stopPreview();
        try {
            Log.i(TAG, "Attaching camera and starting preview");
            mCamera.setPreviewDisplay(mHolder);
            mFacelok.resetCallback(); // NOTE: (FLMSDK-91) this is temporary undocumented function that is needed to fix a camera timing issue.  It will be fixed and not be needed in the future.
            mCamera.startPreview();
        } catch (IOException e) {
            Log.e(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    private void stopPreview() {
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignored, tried to stop a non-existent preview
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mHolder.getSurface() == null) {
            return;
        }

        stopPreview();

        // TODO: set size, resize, rotate or reformatting changes here

        startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
