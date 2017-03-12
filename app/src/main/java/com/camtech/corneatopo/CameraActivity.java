package com.camtech.corneatopo;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileOutputStream;

public class CameraActivity extends AppCompatActivity {

    private Camera mCamera = null;
    private CameraView mCameraView = null;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        button = (Button) findViewById(R.id.btCapture);

        try{
            mCamera = Camera.open();//you can use open(int) to use different cameras
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(parameters);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCamera.takePicture(null, null, new Camera.PictureCallback() {
                        @Override
                        public void onPictureTaken(byte[] bytes, Camera camera) {
                            Intent intent = new Intent(CameraActivity.this, ProcessActivity.class);
                            File photo=new File(Environment.getExternalStorageDirectory(), "photo.jpg");

                            if (photo.exists()) {
                                photo.delete();
                            }

                            try {
                                FileOutputStream fos=new FileOutputStream(photo.getPath());

                                fos.write(bytes);
                                fos.close();
                            }
                            catch (java.io.IOException e) {
                                Log.e("PictureDemo", "Exception in photoCallback", e);
                            }
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });

        } catch (Exception e){
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if(mCamera != null) {
            mCameraView = new CameraView(this, mCamera);//create a SurfaceView to show camera data
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);//add the SurfaceView to the layout
        }
    }
}
