package com.camtech.corneatopo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import static org.opencv.core.Core.NORM_MINMAX;
import static org.opencv.core.Core.extractChannel;
import static org.opencv.core.Core.normalize;
import static org.opencv.imgproc.Imgproc.COLORMAP_JET;
import static org.opencv.imgproc.Imgproc.applyColorMap;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView1, imageViewHist1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("opencv_java3");


        imageView1 = (ImageView) findViewById(R.id.iv1);
        imageViewHist1 = (ImageView) findViewById(R.id.ivhist1);

       Button photoButton = (Button) this.findViewById(R.id.button);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


    }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

                Bitmap photo = (Bitmap)data.getExtras().get("data");
                imageView1.setImageBitmap(photo);
                genHeatMap(photo);

            }
        }


        protected void genHeatMap(Bitmap image){

            Mat img = new Mat(image.getHeight(),image.getWidth(),CvType.CV_32FC4);
            Utils.bitmapToMat(image, img);
            img.convertTo(img, CvType.CV_8UC1);
            Mat imgChR = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
            extractChannel(img, imgChR, 0);
            normalize(imgChR, imgChR, 0, 255, NORM_MINMAX);
            Mat heatOut = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC4);
            applyColorMap(imgChR, heatOut, COLORMAP_JET);
            Bitmap pimage = Bitmap.createBitmap(heatOut.cols(),heatOut.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(heatOut,pimage);
            imageViewHist1.setImageBitmap(pimage);

        }

}

