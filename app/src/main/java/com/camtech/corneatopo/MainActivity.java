package com.camtech.corneatopo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1, imageViewHist1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("opencv_java3");


        imageView1 = (ImageView) findViewById(R.id.iv1);
        imageViewHist1 = (ImageView) findViewById(R.id.ivhist1);
        String path1 = Environment.getExternalStorageDirectory() + "/camtech/camtech1.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(path1);
        imageView1.setImageBitmap(bitmap);


    }
}
